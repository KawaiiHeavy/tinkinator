import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialogModule } from '@angular/material/dialog';


import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { AdminpageComponent } from './components/pages/adminpage/adminpage.component';
import { UserpageComponent } from './components/pages/userpage/userpage.component';
import { HomeComponent } from './components/home/home.component';
import { CreateAnswerComponent } from './components/create/create-answer/create-answer.component';
import { CreateSolutionComponent } from './components/create/create-solution/create-solution.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DeleteSolutionComponent } from './components/delete/delete-solution/delete-solution.component';
import { TestingProcedureComponent } from './components/testing/testing-procedure/testing-procedure.component';
import { TestingMenuComponent } from './components/testing/testing-menu/testing-menu.component';
import { QuestionSectionComponent } from './components/question-section/question-section.component';
import { SolutionSectionComponent } from './components/solution-section/solution-section.component';
import { DetailInfoQuestionDialog } from './components/question-section/question-section.component';
import { EditQuestionDialog } from './components/question-section/question-section.component';
import { AnswerSectionComponent } from './components/answer-section/answer-section.component';
import { DetailInfoAnswerDialog } from './components/answer-section/answer-section.component';
import { EditAnswerDialog } from './components/answer-section/answer-section.component';


@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AdminpageComponent,
    UserpageComponent,
    HomeComponent,
    CreateAnswerComponent,
    CreateSolutionComponent,
    DeleteSolutionComponent,
    TestingProcedureComponent,
    TestingMenuComponent,
    QuestionSectionComponent,
    SolutionSectionComponent,
    DetailInfoQuestionDialog,
    AnswerSectionComponent,
    DetailInfoAnswerDialog,
    EditAnswerDialog,
    EditQuestionDialog
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,
    MatChipsModule,
    MatIconModule,
    ReactiveFormsModule,
    MatExpansionModule,
    MatDividerModule,
    MatDialogModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
