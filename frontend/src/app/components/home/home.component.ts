import { Component, OnInit } from '@angular/core';
import { ClientRequest } from 'src/app/models/clientRequest.model';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  request: ClientRequest = new ClientRequest();
  resultFromServer: String;

  constructor(private messageService: MessageService) { }

  ngOnInit(): void {
  }

  sendMessage(): void {
    this.messageService.sendRequest(this.request)
    .subscribe(message => {
      this.resultFromServer = message.requestText;
      console.log(message);
      this.request = new ClientRequest();
    });
  }

}
