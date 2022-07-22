import { Component, OnInit } from '@angular/core';
import { ClientRequest } from 'src/app/models/clientRequest.model';
import { ClientRequestService } from 'src/app/services/clientRequest.service';

@Component({
  selector: 'app-testing-menu',
  templateUrl: './testing-menu.component.html',
  styleUrls: ['./testing-menu.component.scss']
})
export class TestingMenuComponent implements OnInit {

  clientRequests: ClientRequest[] = [];

  constructor(private clientRequestService: ClientRequestService) { }

  ngOnInit(): void {
    this.getAllClientRequests();
  }

  getAllClientRequests(): void {
    this.clientRequestService.getAllRequests()
    .subscribe(requests => this.clientRequests = requests);
  }

}
