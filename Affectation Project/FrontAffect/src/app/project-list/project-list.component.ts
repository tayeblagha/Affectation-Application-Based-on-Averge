import { Component, OnInit } from '@angular/core';
import { Project } from '../project';
import { ProjectService } from '../project.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projects!: Project[];
  constructor(private projectservice: ProjectService, private router: Router) { }

  ngOnInit(): void {
    this.getProjects();
  }
  private getProjects(){
    this.projectservice.getProjectsList().subscribe(data=>{
      this.projects=data;
    });
  }



  updateProject(id: number){
    this.router.navigate(['update-Project', id]);
  }

  deleteProject(id: number){
    this.projectservice.deleteProject(id).subscribe( data => {
      console.log(data);
      this.getProjects();
    })
  }

}
