import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
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

  addQuestion(): void {
    this.questionService.addQuestion(this.currentQuestion).subscribe();
    this.showAvailableQuestions();
  }

  showAvailableQuestions(): void {
    this.questionService.getAllQuestions()
    .subscribe(questions => {
      this.availableQuestions = questions}
    );
  }

  openDetailInfoDialog(question: Question): void {

    console.log(question);

    this.dialog.open( DetailInfoQuestionDialog, 
      { 
        width: '250px', 
        data: question
      });
  }

  deleteQuestion(question: Question): void {
    this.questionService.deleteQuestion(question.id).subscribe();
    this.showAvailableQuestions();
  }
}

@Component({
  selector: 'edit-question-dialog',
  templateUrl: 'detailInfoDialog.html',
})
export class DetailInfoQuestionDialog {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Question) {}
}
