import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestingProcedureComponent } from './testing-procedure.component';

describe('TestingProcedureComponent', () => {
  let component: TestingProcedureComponent;
  let fixture: ComponentFixture<TestingProcedureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TestingProcedureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestingProcedureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
