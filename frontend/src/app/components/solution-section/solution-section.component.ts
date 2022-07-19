import { Component, OnInit } from '@angular/core';
import { Solution } from 'src/app/models/solution.model';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-solution-section',
  templateUrl: './solution-section.component.html',
  styleUrls: ['./solution-section.component.scss']
})
export class SolutionSectionComponent implements OnInit {

  solution: Solution = new Solution();
  isFailed: boolean = false;
  hideDropdown: boolean;

  availableSolutions: Solution[];

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.hideDropdown = false;
    this.showAvailableSolutions();
  }

  addSolution(){
    this.solutionService.addSolution(this.solution)
    .subscribe({ 
      next: (s) => 
      { 
        this.availableSolutions.push(s);
        this.solution = new Solution();
        this.isFailed = false;
      },
      error: (e) => {
        this.isFailed = true;
      }
    });
  }

  showAvailableSolutions(): void {
    this.solutionService.getAllSolutions().subscribe(solutions => {
      this.availableSolutions = solutions;
    });
  }

  deleteSolution(solution: Solution, index: number): void {
    this.solutionService.deleteSolution(solution.id).subscribe(sol => {
      this.availableSolutions.splice(index, 1);
    });
  }

}
