import { Component, Input, OnInit } from '@angular/core';
import { Question } from 'src/app/models/question.model';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-testing-procedure',
  templateUrl: './testing-procedure.component.html',
  styleUrls: ['./testing-procedure.component.scss']
})
export class TestingProcedureComponent implements OnInit {

  @Input()
  clientRequest: string;

  show: boolean = false;
  currentQuestion: Question;

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.nextQuestion();
  }

  startProcedure() : void {
    this.show = true;

  }

  nextQuestion(): void {
    this.questionService.getRandomQuestion().subscribe(question => this.currentQuestion = question);
  }

}
