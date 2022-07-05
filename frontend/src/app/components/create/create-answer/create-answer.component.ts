import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { Answer } from 'src/app/models/answer.model';
import { Solution } from 'src/app/models/solution.model';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.scss']
})
export class CreateAnswerComponent implements OnInit {

  @Input()
  answer: Answer;
  @Output()
  newSolutionEvent = new EventEmitter();

  availableSolutions: Solution[];

  solutionsControl = new FormControl([]);

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.showAvailableSolutions();
  }

  showAvailableSolutions(): void {
    this.solutionService.getAllSolutions()
    .subscribe(solutions => this.availableSolutions = solutions);
  }
  
  onSolutionRemoved(solutionText: string) {
    const solutionTexts = this.solutionsControl.value as never[];
    this.removeFirst(solutionTexts, solutionText);
    this.solutionsControl.setValue(solutionTexts); // To trigger change detection
  }

  addNewSolution(value: string) {
    console.log(this.solutionsControl.value);
    this.newSolutionEvent.emit(this.solutionsControl.value);
  }

  private removeFirst<T>(array: T[], toRemove: T): void {

    const index = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

}
