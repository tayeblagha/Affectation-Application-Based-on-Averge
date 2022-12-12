import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AffectaionListComponent } from './affectaion-list/affectaion-list.component';
import { CreateProjectComponent } from './create-project/create-project.component';
import { CreateStudentComponent } from './create-student/create-student.component';
import { ProjectListComponent } from './project-list/project-list.component';
import { StudentListComponent } from './student-list/student-list.component';
import { StudentprojComponent } from './studentproj/studentproj.component';
import { UpdateProjectComponent } from './update-project/update-project.component';
import { UpdateStudentComponent } from './update-student/update-student.component';

const routes: Routes = [
  {path: 'projects', component: ProjectListComponent},
  {path: 'update-Project/:id', component: UpdateProjectComponent},
  {path: 'projects/create-project', component: CreateProjectComponent},

  

  {path: 'students', component: StudentListComponent},
  {path: 'update-Student/:id', component: UpdateStudentComponent},
  {path: 'students/create-student', component: CreateStudentComponent},
  {path: 'students/student_project/:id', component: StudentprojComponent},

  
  {path: 'affectations', component:AffectaionListComponent},
  

 
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
