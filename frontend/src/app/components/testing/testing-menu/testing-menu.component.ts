import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-testing-menu',
  templateUrl: './testing-menu.component.html',
  styleUrls: ['./testing-menu.component.scss']
})
export class TestingMenuComponent implements OnInit {

  clientRequests: string[] = [
    "Памагите пажалуста",
    "Help me!"
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
