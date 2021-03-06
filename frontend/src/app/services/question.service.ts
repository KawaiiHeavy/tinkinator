import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Pageable } from "../models/pageable";
import { Question } from "../models/question.model";

@Injectable({
    providedIn: 'root'
})
export class QuestionService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getAllQuestions(): Observable<Question[]> {
        return this.http.get<Question[]>(`${this.apiServerUrl}/question/all`);
    }

    public addQuestion(question: Question): Observable<Question> {
        return this.http.post<Question>(`${this.apiServerUrl}/question/add`, question)
    } 

    public updateQuestion(question: Question): Observable<Question> {
        return this.http.put<Question>(`${this.apiServerUrl}/question/update`, question)
    }

    public deleteQuestion(questionId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/question/delete/${questionId}`)
    }

    public getQuestionById(questionId: string): Observable<Question> {
        return this.http.get<Question>(`${this.apiServerUrl}/question/find/${questionId}`);
    }

    public getRandomQuestion(): Observable<Question> {
        return this.http.get<Question>(`${this.apiServerUrl}/question/findRandom`);
    }

    public getAllQuestionsPaging(page: number, size: number): Observable<Pageable<Question>> {
        return this.http.get<Pageable<Question>>(`${this.apiServerUrl}/question/allPageable?page=${page}&size=${size}`);
    }
}