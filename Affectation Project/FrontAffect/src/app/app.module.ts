import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { UpdateProjectComponent } from './update-project/update-project.component';
import { CreateProjectComponent } from './create-project/create-project.component';
import { FormsModule } from '@angular/forms';
import { StudentListComponent } from './student-list/student-list.component';
import { CreateStudentComponent } from './create-student/create-student.component';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { StudentprojComponent } from './studentproj/studentproj.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AffectaionListComponent } from './affectaion-list/affectaion-list.component';
@NgModule({
  declarations: [
    AppComponent,
    ProjectListComponent,
    UpdateProjectComponent,
    CreateProjectComponent,
    StudentListComponent,
    CreateStudentComponent,
    UpdateStudentComponent,
    StudentprojComponent,
    AffectaionListComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
