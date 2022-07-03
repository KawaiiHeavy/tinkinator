import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';

import { AppComponent } from './app.component';
import { MainComponent } from './components/main/main.component';
import { AdminpageComponent } from './components/pages/adminpage/adminpage.component';
import { UserpageComponent } from './components/pages/userpage/userpage.component';
import { HomeComponent } from './components/home/home.component';
import { CreateAnswerComponent } from './components/create/create-answer/create-answer.component';
import { CreateQuestionComponent } from './components/create/create-question/create-question.component';
import { CreateSolutionComponent } from './components/create/create-solution/create-solution.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    AdminpageComponent,
    UserpageComponent,
    HomeComponent,
    CreateAnswerComponent,
    CreateQuestionComponent,
    CreateSolutionComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
