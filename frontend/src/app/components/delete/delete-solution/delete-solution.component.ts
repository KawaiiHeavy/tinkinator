import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Solution } from 'src/app/models/solution.model';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-delete-solution',
  templateUrl: './delete-solution.component.html',
  styleUrls: ['./delete-solution.component.scss']
})
export class DeleteSolutionComponent implements OnInit {

  solutionIds: any[] = [];
  availableSolutions: Solution[];

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.showAvailableSolutions();
  }

  showAvailableSolutions(): void {
    this.solutionService.getAllSolutions()
    .subscribe(solutions => this.availableSolutions = solutions);
  }

  solutionsControl = new FormControl([]);
  
  onSolutionRemoved(solutionText: string) {

    console.log(this.availableSolutions);

    const solutionTexts = this.solutionsControl.value as never[];
    this.removeFirst(solutionTexts, solutionText);
    this.solutionsControl.setValue(solutionTexts); // To trigger change detection
  }

  private removeFirst<T>(array: T[], toRemove: T): void {

    const index = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

  deleteSolutions(): void {

    const solutionTexts = this.solutionsControl.value as string[];
    this.availableSolutions.forEach(solution => {
      if (solutionTexts.includes(solution.solutionText)){
        console.log("!");
        this.solutionIds.push(solution.id);
      }
    })

    this.solutionIds.forEach(solutionId => 
      this.solutionService.deleteSolution(solutionId).subscribe())

    this.solutionIds = [];

  }
}
