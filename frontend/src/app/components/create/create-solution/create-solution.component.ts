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

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
  }

  addSolution(){
    this.solutionService.addSolution(this.solution)
    .subscribe(s => this.solution = s);
  }

}
