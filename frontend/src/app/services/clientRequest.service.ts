import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { ClientRequest } from "../models/clientRequest.model";

@Injectable({
    providedIn: 'root'
})
export class ClientRequestService { 

    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public sendRequest(request: ClientRequest): Observable<ClientRequest> {
        return this.http.post<ClientRequest>(`${this.apiServerUrl}/clientRequest/add`, request);
    }

    public getAllRequests(): Observable<ClientRequest[]> {
        return this.http.get<ClientRequest[]>(`${this.apiServerUrl}/clientRequest/all`);
    }
}