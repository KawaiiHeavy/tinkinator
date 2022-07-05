import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestingMenuComponent } from './testing-menu.component';

describe('TestingMenuComponent', () => {
  let component: TestingMenuComponent;
  let fixture: ComponentFixture<TestingMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestingMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestingMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
