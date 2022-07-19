import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { AnswerService } from 'src/app/services/answer.service';
import { DataExchangingService } from 'src/app/services/data-exchanging.service';
import { QuestionService } from 'src/app/services/question.service';
import { SolutionService } from 'src/app/services/solution.service';

@Component({
  selector: 'app-question-section',
  templateUrl: './question-section.component.html',
  styleUrls: ['./question-section.component.scss']
})
export class QuestionSectionComponent implements OnInit {

  currentQuestion : Question = new Question();
  availableQuestions: Question[];

  questionId : string;
  hideDropdown: boolean;

  constructor(private questionService: QuestionService,
    private solutionService: SolutionService,
    private dataExchaningService: DataExchangingService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.hideDropdown = false;
    this.currentQuestion.answers = [];
    this.showAvailableQuestions();
  }

  ngOnChanges(): void {}

  addAnswer() : void {
    this.currentQuestion.answers.push(new Answer());
  }

  deleteAnswer(answerIdx: number): void {
    this.currentQuestion.answers.splice(answerIdx, 1);
  }

  addQuestion(): void {
    this.questionService.addQuestion(this.currentQuestion).subscribe(question => {
      this.availableQuestions.push(question);
      let message: string = JSON.stringify(this.currentQuestion.answers);
      this.dataExchaningService.changeMessage(message);
    });
  }

  showAvailableQuestions(): void {
    this.questionService.getAllQuestions()
    .subscribe(questions => {
      this.availableQuestions = questions}
    );
  }

  openDetailInfoDialog(question: Question): void {
    this.dialog.open( DetailInfoQuestionDialog, 
      { 
        width: '250px', 
        data: question
      });
  }

  openEditDialog(question: Question): void {
    this.dialog.open( EditQuestionDialog,
      {
        width: '250px',
        data: question
      });
  }

  deleteQuestion(question: Question, index: number): void {
    this.questionService.deleteQuestion(question.id).subscribe(question => {
      this.availableQuestions.splice(index, 1);
    });
  }
}

@Component({
  selector: 'detailInfo-question-dialog',
  templateUrl: 'detailInfoQuestionDialog.html',
})
export class DetailInfoQuestionDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Question) {}
}


@Component({
  selector: 'edit-question-dialog',
  templateUrl: 'editQuestionDialog.html',
})
export class EditQuestionDialog {
  constructor(
    public dialogRef: MatDialogRef<EditQuestionDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Question, 
    private questionService: QuestionService) {}

  addAnswer(): void {
    this.data.answers.push(new Answer());
  }

  deleteAnswer(answerIdx: number): void {
    this.data.answers.splice(answerIdx, 1);
  }

  confirm(): void {
     this.questionService.addQuestion(this.data).subscribe(quest => {
       console.log(this.data);
     });
     this.dialogRef.close();
  }

}