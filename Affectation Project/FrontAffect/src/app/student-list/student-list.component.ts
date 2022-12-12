import { Component, OnInit } from '@angular/core';
import { Student } from '../student';
import { StudentService } from '../student.service';
import { Router } from '@angular/router';
import { Project } from '../project';
import { ProjectService } from '../project.service';
import { Numprst } from '../numprst';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  students!:Student[];
  isAvailable!:boolean;
  projects!:Project[];
  numStPr!:number;
  NumprstClass:Numprst = new Numprst() ;
  constructor(private studentservice: StudentService,private projectservice: ProjectService, private router: Router) { }

  ngOnInit(): void {
    this.getStudents();
    this.getProjects();
    this.getNumPrSt();
  }
  
  private getStudents(){
    this.studentservice.getStudentsList().subscribe(data=>{
      this.students=data;
    });
    /* console.log(this.getStudents.length); */
  }
  
    private getProjects(){
      this.projectservice.getProjectsList().subscribe(data2=>{
        this.projects=data2;
      });
    }
  

  verifyStudentAndProject():boolean{
    console.log(this.students?.length);

    //console.log(this.projects?.length);
    if(this.students?.length==this.projects?.length){
      return true;
    }else{
      return false;
    }
  }

  affectStudents(){
    //this.router.navigate(['affectations']);
    window.open('http://localhost:4200/affectations', '_blank');
  }

  

  updateStudent(id: number){
    this.router.navigate(['update-Student', id]);
  }

  deleteStudent(id: number){
    this.studentservice.deleteStudent(id).subscribe( data => {
      console.log(data);
      this.getStudents();
    })
  }
  getNumPrSt(){
    this.studentservice.getNumPrSt().subscribe(data2=>{
      this.numStPr= Number(data2);
      
    })
  }

  updateNumPrSt(){
    this.studentservice.updateNumberStudentPr( this.NumprstClass).subscribe( data =>{
      this.getNumPrSt();
    }
    , error => console.log(error));
    this.NumprstClass= new Numprst();

  }
  

}
