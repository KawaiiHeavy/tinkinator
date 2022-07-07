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
  answers : Answer[] = []; 
  solutions : string[][] = [];

  constructor(private questionService: QuestionService,
    private solutionService: SolutionService) { }

  ngOnInit(): void {
  }

  addAnswer() : void {
    this.answers.push(new Answer());
  }

  addQuestion(): void {
    this.question.answers = this.answers;
    this.questionService.addQuestion(this.question).subscribe();

    let flatSolutions = this.solutions.flat(2);
    console.log(flatSolutions);
  }

  addSolution(newSolutions: string[], index: number){
    this.solutions[index] = newSolutions;
  }
}
