import { Component, OnInit } from '@angular/core';
import { Solution } from 'src/app/models/solution.model';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-create-solution',
  templateUrl: './create-solution.component.html',
  styleUrls: ['./create-solution.component.scss']
})
export class CreateSolutionComponent implements OnInit {

  solution: Solution = new Solution();
  isFailed: boolean = false;

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
  }

  addSolution(){
    this.solutionService.addSolution(this.solution)
    .subscribe({ 
      next: (s) => 
      { 
        this.solution = new Solution();
        this.isFailed = false;
      },
      error: (e) => {
        this.isFailed = true;
      }
    });
  }

}
