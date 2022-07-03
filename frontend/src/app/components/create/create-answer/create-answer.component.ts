import { Component, Input, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';

@Component({
  selector: 'app-create-answer',
  templateUrl: './create-answer.component.html',
  styleUrls: ['./create-answer.component.scss']
})
export class CreateAnswerComponent implements OnInit {

  @Input()
  answer: Answer;

  constructor() { }

  ngOnInit(): void {
  }

}
