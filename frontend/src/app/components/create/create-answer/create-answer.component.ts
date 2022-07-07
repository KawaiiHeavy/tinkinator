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

  availableSolutions: Solution[];
  hideDropdown: boolean;

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.hideDropdown = false;
    this.showAvailableSolutions();
  }

  showAvailableSolutions(): void {
    this.solutionService.getAllSolutions()
    .subscribe(solutions => this.availableSolutions = solutions);
  }

  onClick(){
    this.hideDropdown = !this.hideDropdown;
  }

}
