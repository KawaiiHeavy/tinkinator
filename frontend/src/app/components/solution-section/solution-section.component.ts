import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Solution } from 'src/app/models/solution.model';
import { SolutionService } from 'src/app/services/solution.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { PaginatorUtils } from 'src/utils/paginator.utils';

@Component({
  selector: 'app-solution-section',
  templateUrl: './solution-section.component.html',
  styleUrls: ['./solution-section.component.scss']
})
export class SolutionSectionComponent implements OnInit {

  @ViewChild('paginator')
  paginator: MatPaginator;
  
  solution: Solution;
  length: number = 0;

  isFailed: boolean = false;
  
  hideDropdown: boolean;

  availableSolutions: Solution[];

  constructor(private solutionService: SolutionService, 
              private paginatorUtils: PaginatorUtils) { }

  ngOnInit(): void {
    this.solution = new Solution();
    this.hideDropdown = false;
    this.solutionService.getAllSolutionsPaging(0, 5)
      .subscribe(pageable => {
        this.availableSolutions = pageable.content;
        this.length = pageable.totalElements;
      });
  }

  addSolution(){
    console.log(this.solution);
    this.solutionService.addSolution(this.solution)
    .subscribe({ 
      next: (s) => 
      { 
        if (this.length !== this.paginator.pageSize){
          this.availableSolutions.push(s);
        }
        this.length++;
        this.solution = new Solution();
        this.isFailed = false;
      },
      error: (e) => {
        this.isFailed = true;
      }
    });
  }

  nextPage(event: PageEvent): void {
    this.solutionService.getAllSolutionsPaging(event.pageIndex, event.pageSize)
    .subscribe(pageable => {
      this.availableSolutions = pageable.content;
      this.length = pageable.totalElements;
    });
  }

  deleteSolution(solution: Solution, index: number): void {
    this.solutionService.deleteSolution(solution.id).subscribe(sol => {
      this.availableSolutions.splice(index, 1);
      if (this.availableSolutions.length == 0){
        this.paginator.previousPage();
      }
      else {
        let newIndex: number = this.paginatorUtils.getIndexOfPagedObject(
          this.paginator.pageIndex, 
          this.paginator.pageSize);

        this.solutionService.getAllSolutionsPaging(newIndex, 1).subscribe(pageable => {
          if (pageable.content.length != 0){
            this.availableSolutions.push(pageable.content[0]);
          }});
      }
      this.length--;
    });
  }

}
