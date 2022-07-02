import { Component, OnInit } from '@angular/core';
import { Question } from 'src/app/models/question.model';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  constructor(private questionService: QuestionService) { }

  question: Question;

  ngOnInit(): void {
    
  }

  addQuestion(){
    const question: Question = {
      questionText: "question"
    };
    
    this.questionService.addQuestion(question).subscribe(
      question => this.question = question);
  }

}
