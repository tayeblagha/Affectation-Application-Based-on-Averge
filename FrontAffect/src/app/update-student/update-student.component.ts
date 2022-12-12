import { Component, OnInit } from '@angular/core';
import { StudentService } from '../student.service';
import { Student } from '../student';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {
  id!: number;
  Student: Student = new Student();
  constructor(private StudentService: StudentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.StudentService.getStudentById(this.id).subscribe(data => {
      this.Student = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.StudentService.updateStudent(this.id, this.Student).subscribe( data =>{
      this.goToStudentList();
    }
    , error => console.log(error));
  }

  goToStudentList(){
    this.router.navigate(['/students']);
  }

}