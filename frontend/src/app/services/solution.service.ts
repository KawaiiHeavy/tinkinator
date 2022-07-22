import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Pageable } from "../models/pageable";
import { Solution } from "../models/solution.model";

@Injectable({
    providedIn: 'root'
})
export class SolutionService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getAllSolutions(): Observable<Solution[]> {
        return this.http.get<Solution[]>(`${this.apiServerUrl}/solution/all`);
    }

    public getAllSolutionsPaging(page: number, size: number): Observable<Pageable<Solution>> {
        return this.http.get<Pageable<Solution>>(`${this.apiServerUrl}/solution/allPageable?page=${page}&size=${size}`);
    }

    public addSolution(solution: Solution): Observable<Solution> {
        return this.http.post<Solution>(`${this.apiServerUrl}/solution/add`, solution)
    } 

    public updateSolution(solution: Solution): Observable<Solution> {
        return this.http.put<Solution>(`${this.apiServerUrl}/solution/update`, solution)
    }

    public deleteSolution(solutionId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/solution/delete/${solutionId}`);
    }

    public getSolutionById(solutionId: string): Observable<Solution> {
        return this.http.get<Solution>(`${this.apiServerUrl}/solution/find/${solutionId}`);
    }

    public deleteSolutions(solutionIds: string[]): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/solution/delete/many/${solutionIds}`);
    }

}