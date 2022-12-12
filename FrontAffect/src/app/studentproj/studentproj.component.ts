import { Component, OnInit } from '@angular/core';
import { Project } from '../project';
import { StudentService } from '../student.service';
import { Student } from '../student';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentProject } from '../student-project';
import { FormControl,FormGroup,FormBuilder } from '@angular/forms';
@Component({
  selector: 'app-studentproj',
  templateUrl: './studentproj.component.html',
  styleUrls: ['./studentproj.component.css']
})
export class StudentprojComponent implements OnInit {
  id!: number;
  projects!: Project[];
  projectsAvailable!: Project[];
  studentProj:StudentProject = new StudentProject()
  student:Student = new Student();

  oppoSuitsForm = this.fb.group({
    name: ['']
  })

  constructor(public fb: FormBuilder,private StudentService: StudentService,private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.StudentService.getStudentById(this.id).subscribe(data => {
      this.student = data;
    }, error => console.log(error));
    this.getProjectsByStudentId();
    this.RestProject();
  }
  private getProjectsByStudentId(){
    this.StudentService.getStudentsandProjectByID(this.id).subscribe(data=>{
      this.projects=data;
    });
  }
  private RestProject(){
    this.StudentService.getAvailableStudentProjectByID(this.id).subscribe(data=>{
      this.projectsAvailable=data;
    });
  }
  public addProject(){  
    if (this.oppoSuitsForm.get('name')?.value !== undefined  && this.oppoSuitsForm.get('name')?.value !== null  ) {
      this.studentProj.project= Number(this.oppoSuitsForm.get('name')?.value);
    }
    this.studentProj.student=Number(this.id);
    this.StudentService.createStudentProject(this.studentProj).subscribe( data =>{
      if(data==null)
      {
        alert("You Attend Maximum number you must delete project In order to proceed")
      }
      this.goToStudentList();
    },
    error => console.log(error));
    
   // alert(this.studentProj.student +"/  "+ this.studentProj.project) ;
  }

  goToStudentList(){
    this.getProjectsByStudentId();
    this.RestProject();
    this.router.navigate(['/students/student_project/'+this.id]);
  }
  deleteProject(id_project:number){
     this.StudentService.deleteStudentProject(this.id,id_project).subscribe( data => {
      //console.log(data);
      
      this.goToStudentList();   
    })
   
  }

}
