import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { Solution } from 'src/app/models/solution.model';
import { AnswerService } from 'src/app/services/answer.service';
import { QuestionService } from 'src/app/services/question.service';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-answer-section',
  templateUrl: './answer-section.component.html',
  styleUrls: ['./answer-section.component.scss']
})
export class AnswerSectionComponent implements OnInit {

  answers: Answer[];

  constructor(private answerService: AnswerService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.showAvailableAnswers();
  }

  showAvailableAnswers(): void {
    this.answerService.getAllAnswers()
    .subscribe(answers => {
      this.answers = answers}
    );
  }

  deleteAnswer(answer: Answer): void {
    this.answerService.deleteAnswer(answer.id).subscribe();
    this.showAvailableAnswers();
  }

  openDetailInfoDialog(answer: Answer): void {
    this.dialog.open( DetailInfoAnswerDialog, 
      { 
        width: '250px', 
        data: answer
      });
  }

  openEditDialog(answer: Answer): void {
    this.dialog.open( EditAnswerDialog, 
      {
        width: '250px',
        data: answer
      } )
  }
}


@Component({
  selector: 'detailInfo-answer-dialog',
  templateUrl: 'detailInfoAnswerDialog.html',
})
export class DetailInfoAnswerDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Answer) {}
}


@Component({
  selector: 'edit-answer-dialog',
  templateUrl: 'editAnswerDialog.html',
})
export class EditAnswerDialog {
  constructor(public dialogRef: MatDialogRef<EditAnswerDialog>, 
    @Inject(MAT_DIALOG_DATA) public data: Answer, 
  private questionService: QuestionService,
  private solutionService: SolutionService,
  private answerService: AnswerService) {}

  questions: Question[];
  solutions: Solution[];

  showQuestionAddMenu(): void {
    this.questionService.getAllQuestions().subscribe(questions => 
      { 
        this.questions = [];
        questions.forEach(question => {
          for (let answer of question.answers){
            if (answer.id !== this.data.id){
              this.questions.push(question);
              break;
            }
          }
        });
      });
  }

  showSolutionAddMenu(): void {
    this.solutionService.getAllSolutions().subscribe(solutions => this.solutions = solutions);
  }

  addQuestionToAnswer(question: Question): void {
    this.data.question = question;
  }

  removeQuestionFromAnswer(): void {
    this.data.question = null;
  }

  addSolutionToAnswer(solution: Solution): void {
    this.data.solution = solution;
  }

  removeSolutionFromAnswer(): void {
    this.data.solution = null;
  }

  confirm(): void {
    this.answerService.updateAnswer(this.data).subscribe();
    this.dialogRef.close();
  }

}
