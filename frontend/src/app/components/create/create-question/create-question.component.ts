import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { Solution } from 'src/app/models/solution.model';
import { QuestionService } from 'src/app/services/question.service';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-create-question',
  templateUrl: './create-question.component.html',
  styleUrls: ['./create-question.component.scss']
})
export class CreateQuestionComponent implements OnInit {

  question : Question = new Question();

  constructor(private questionService: QuestionService,
    private solutionService: SolutionService) { }

  ngOnInit(): void {
    this.question.answers = [];
  }

  addAnswer() : void {
    this.question.answers.push(new Answer());
  }

  addQuestion(): void {
    console.log(this.question);
    this.questionService.addQuestion(this.question).subscribe();

  }
}
