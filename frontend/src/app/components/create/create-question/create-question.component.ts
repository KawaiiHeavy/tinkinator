import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { Solution } from 'src/app/models/solution.model';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.scss']
})
export class CreateQuestionComponent implements OnInit {

  question : Question = new Question();
  answers : Answer[] = []; 
  solutions: Solution[][] = [];

  constructor() { }

  ngOnInit(): void {
  }

  addAnswer() : void {
    this.answers.push(new Answer());
  }

}
