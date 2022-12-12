import { Component, OnInit } from '@angular/core';
import { Project } from '../project';
import { ProjectService } from '../project.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent implements OnInit {

  project: Project = new Project();
  constructor(private ProjectService: ProjectService,
    private router: Router) { }


  ngOnInit(): void {
  }

  saveProject(){
    this.ProjectService.createProject(this.project).subscribe( data =>{
      console.log(data);
      this.goToProjectList();
    },
    error => console.log(error));
  }

  goToProjectList(){
    this.router.navigate(['/projects']);
  }
  
  onSubmit(){
    console.log(this.project);
    this.saveProject();
  }

}
