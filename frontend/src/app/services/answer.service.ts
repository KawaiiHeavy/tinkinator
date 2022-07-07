import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Answer } from "../models/answer.model";
import { Question } from "../models/question.model";
import { Solution } from "../models/solution.model";

@Injectable({
    providedIn: 'root'
})
export class AnswerService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getAllAnswers(): Observable<Answer[]> {
        return this.http.get<Answer[]>(`${this.apiServerUrl}/answer/all`);
    }

    public addAnswer(answer: Answer): Observable<Answer> {
        return this.http.post<Answer>(`${this.apiServerUrl}/answer/add`, answer)
    } 

    public updateAnswer(answer: Answer): Observable<Answer> {
        return this.http.put<Answer>(`${this.apiServerUrl}/answer/update`, answer)
    }

    public deleteAnswer(answerId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/answer/delete/${answerId}`)
    }

    public getAnswerById(answerId: string): Observable<Answer> {
        return this.http.get<Answer>(`${this.apiServerUrl}/answer/find/${answerId}`);
    }

    public getQuestionByAnswerId(answerId: string): Observable<Question> {
        return this.http.get<Question>(`${this.apiServerUrl}/answer/findQuestionByAnswerId/${answerId}`);
    }

    public getSolutionByAnswerId(answerId: string): Observable<Solution> {
        return this.http.get<Solution>(`${this.apiServerUrl}/answer/findSolutionByAnswerId/${answerId}`);
    }

}