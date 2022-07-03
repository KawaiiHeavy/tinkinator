import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.scss']
})
export class CreateQuestionComponent implements OnInit {

  question : Question = new Question();
  answers : Answer[] = []; 

  constructor() { }

  ngOnInit(): void {
  }

  addAnswer() : void {
    this.answers.push(new Answer());
  }

}
