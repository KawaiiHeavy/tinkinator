import { Component, Input, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { AnswerService } from 'src/app/services/answer.service';
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
  solutionText: string = "";

  constructor(private answerService: AnswerService, 
    private questionService: QuestionService) { }

  ngOnInit(): void {
    this.questionService.getAllQuestions().subscribe(questions => this.currentQuestion = questions[0]);
  }

  startProcedure() : void {
    this.show = true;

  }

  nextQuestion(answer: Answer): void {
    this.answerService.getQuestionByAnswerId(answer.id).subscribe({ 
      next: (question) => 
      { 
        this.currentQuestion = question;
      },
      error: (e) => {
        this.answerService.getSolutionByAnswerId(answer.id).subscribe({
          next: (solution) => 
          { 
            this.solutionText = solution.solutionText;
          },
          error: (e) => {
            this.solutionText = "Решения данной проблемы еще нет в базе данных";
          }
        });
      }
    });
  }

}
