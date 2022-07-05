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

import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { AdminpageComponent } from './components/pages/adminpage/adminpage.component';
import { UserpageComponent } from './components/pages/userpage/userpage.component';
import { HomeComponent } from './components/home/home.component';
import { CreateAnswerComponent } from './components/create/create-answer/create-answer.component';
import { CreateQuestionComponent } from './components/create/create-question/create-question.component';
import { CreateSolutionComponent } from './components/create/create-solution/create-solution.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DeleteSolutionComponent } from './components/delete/delete-solution/delete-solution.component';
import { DeleteQuestionComponent } from './components/delete/delete-question/delete-question.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AdminpageComponent,
    UserpageComponent,
    HomeComponent,
    CreateAnswerComponent,
    CreateQuestionComponent,
    CreateSolutionComponent,
    DeleteSolutionComponent,
    DeleteQuestionComponent
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
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
