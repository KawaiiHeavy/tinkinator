import { Component, Inject, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Subscription } from 'rxjs';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { Solution } from 'src/app/models/solution.model';
import { AnswerService } from 'src/app/services/answer.service';
import { DataExchangingService } from 'src/app/services/data-exchanging.service';
import { QuestionService } from 'src/app/services/question.service';
import { SolutionService } from 'src/app/services/solution.service';
import { PaginatorUtils } from 'src/utils/paginator.utils';

@Component({
  selector: 'app-answer-section',
  templateUrl: './answer-section.component.html',
  styleUrls: ['./answer-section.component.scss']
})
export class AnswerSectionComponent implements OnInit, OnDestroy {

  @ViewChild('paginator')
  paginator: MatPaginator;

  answers: Answer[];
  length: number = 0;

  message:string;
  subscription: Subscription;

  constructor(private answerService: AnswerService, 
    private dataExchangingService: DataExchangingService,
    private paginatorUtils: PaginatorUtils,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.answerService.getAllAnswersPaging(0, 5)
      .subscribe(pageable => {
        this.answers = pageable.content;
        this.length = pageable.totalElements;
      })
    this.subscription = this.dataExchangingService.currentMessage.subscribe(message => {
      let answers: Answer[] = JSON.parse(message);
      answers.forEach(answer => {
        if (this.answers.length !== this.paginator.pageSize) {
          this.answers.push(answer)
        }
        this.length++;
      });
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  nextPage(event: PageEvent): void {
    this.answerService.getAllAnswersPaging(event.pageIndex, event.pageSize)
    .subscribe(pageable => {
      this.answers = pageable.content;
      this.length = pageable.totalElements;
    })
  }

  deleteAnswer(answer: Answer, index: number): void {
    this.answerService.deleteAnswer(answer.id).subscribe(ans => {
      this.answers.splice(index, 1);
      if (this.answers.length == 0) {
        this.paginator.previousPage();
      }
      else {
        let newIndex: number = this.paginatorUtils.getIndexOfPagedObject(
          this.paginator.pageIndex,
          this.paginator.pageSize);

        this.answerService.getAllAnswersPaging(newIndex, 1).subscribe(pageable => {
          if (pageable.content.length != 0) {
            this.answers.push(pageable.content[0]);
          }});
      }
      this.length--;
    });
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
  constructor(@Inject(MAT_DIALOG_DATA) public data: Answer, 
  private answerService: AnswerService) {}

  ngOnInit(): void {
    this.answerService.getQuestionByAnswerId(this.data.id).subscribe(question => this.data.question = question);
  }
}


@Component({
  selector: 'edit-answer-dialog',
  templateUrl: 'editAnswerDialog.html',
})
export class EditAnswerDialog implements OnInit {
  constructor(public dialogRef: MatDialogRef<EditAnswerDialog>, 
    @Inject(MAT_DIALOG_DATA) public data: Answer, 
  private questionService: QuestionService,
  private solutionService: SolutionService,
  private answerService: AnswerService) {}

  questions: Question[];
  solutions: Solution[];

  ngOnInit(): void {
    this.answerService.getQuestionByAnswerId(this.data.id).subscribe(question => this.data.question = question);
  }

  showQuestionAddMenu(): void {
    this.questionService.getAllQuestions().subscribe(questions => this.questions = questions);
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
    if (this.data.question != null) {
      console.log(this.data.question.id);
      this.answerService.attachQuestion(this.data.question.id, this.data.id).subscribe();

    }
    this.answerService.updateAnswer(this.data).subscribe();
    this.dialogRef.close();
  }

}
