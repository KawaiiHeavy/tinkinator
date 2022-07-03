import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Solution } from "../models/solution.model";

@Injectable({
    providedIn: 'root'
})
export class SolutionService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getSolution(): Observable<Solution[]> {
        return this.http.get<Solution[]>(`${this.apiServerUrl}/solution/all`);
    }

    public addSolution(solution: Solution): Observable<Solution> {
        return this.http.post<Solution>(`${this.apiServerUrl}/solution/add`, solution)
    } 

    public updateSolution(solution: Solution): Observable<Solution> {
        return this.http.put<Solution>(`${this.apiServerUrl}/solution/update`, solution)
    }

    public deleteSolution(solutionId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/solution/delete/${solutionId}`);
    }

}