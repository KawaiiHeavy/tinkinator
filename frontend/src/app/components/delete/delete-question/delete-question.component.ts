import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Question } from 'src/app/models/question.model';
import { QuestionService } from 'src/app/services/question.service';

@Component({
  selector: 'app-delete-question',
  templateUrl: './delete-question.component.html',
  styleUrls: ['./delete-question.component.scss']
})
export class DeleteQuestionComponent implements OnInit {

  questionIds: any[] = [];
  availableQuestions: Question[];

  constructor(private questionService: QuestionService) { }

  ngOnInit(): void {
    this.showAvailableQuestions();
  }

  showAvailableQuestions(): void {
    this.questionService.getAllQuestions()
    .subscribe(questions => this.availableQuestions = questions);
  }

  questionsControl = new FormControl([]);
  
  onQuestionRemoved(questionText: string) {

    console.log(this.availableQuestions);

    const questionTexts = this.questionsControl.value as never[];
    this.removeFirst(questionTexts, questionText);
    this.questionsControl.setValue(questionTexts); // To trigger change detection
  }

  private removeFirst<T>(array: T[], toRemove: T): void {

    const index = array.indexOf(toRemove);
    if (index !== -1) {
      array.splice(index, 1);
    }
  }

  deleteQuestions(): void {

    const questionTexts = this.questionsControl.value as string[];
    this.availableQuestions.forEach(question => {
      if (questionTexts.includes(question.questionText)){
        console.log("!");
        this.questionIds.push(question.id);
      }
    })

    this.questionIds.forEach(questionId => 
      this.questionService.deleteQuestion(questionId).subscribe())

    this.questionIds = [];
  }
}
