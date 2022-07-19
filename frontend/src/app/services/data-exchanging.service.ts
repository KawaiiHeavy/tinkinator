import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class DataExchangingService {

    private dataSource = new BehaviorSubject('default message');
    currentMessage = this.dataSource.asObservable();
  
    constructor() { }
  
    changeMessage(message: string) {
      this.dataSource.next(message)
    }

}