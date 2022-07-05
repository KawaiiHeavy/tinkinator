import { Component, OnInit } from '@angular/core';
import { Answer } from 'src/app/models/answer.model';
import { Question } from 'src/app/models/question.model';
import { Solution } from 'src/app/models/solution.model';
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

  constructor(private solutionService: SolutionService) { }

  ngOnInit(): void {
  }

  addAnswer() : void {
    this.answers.push(new Answer());
  }

  addQuestion(): void {
    for (let i = 0; i < this.answers.length; i++){
      let answer: Answer = this.answers[i];

      this.solutions[i].forEach(solutionText => {
        answer.question = this.question;
        
        let solution: Solution = {
          "solutionText": solutionText,
          "answer": answer
        }

        console.log(solution);

        this.solutionService.addSolution(solution).subscribe();
      });
    }
  }

  addSolution(newSolutions: string[], index: number){
    this.solutions[index] = newSolutions;
  }
}
