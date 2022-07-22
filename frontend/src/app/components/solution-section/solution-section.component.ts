import { Component, OnInit } from '@angular/core';
import { Solution } from 'src/app/models/solution.model';
import { SolutionService } from 'src/app/services/solution.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-solution-section',
  templateUrl: './solution-section.component.html',
  styleUrls: ['./solution-section.component.scss']
})
export class SolutionSectionComponent implements OnInit {

  solution: Solution = new Solution();
  length: number = 0;

  isFailed: boolean = false;
  
  hideDropdown: boolean;

  availableSolutions: Solution[];

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.hideDropdown = false;
    this.solutionService.getAllSolutionsPaging(0, 5)
      .subscribe(pageable => {
        this.availableSolutions = pageable.content;
        console.log(pageable);
        this.length = pageable.totalElements;
      });
  }

  addSolution(){
    this.solutionService.addSolution(this.solution)
    .subscribe({ 
      next: (s) => 
      { 
        this.availableSolutions.push(s);
        this.solution = new Solution();
        this.length++;
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
      console.log(this.availableSolutions);
      this.length = pageable.totalElements;
    });
  }

  deleteSolution(solution: Solution, index: number): void {
    this.solutionService.deleteSolution(solution.id).subscribe(sol => {
      this.availableSolutions.splice(index, 1);
    });
  }

}
