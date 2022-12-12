import { Component, OnInit } from '@angular/core';
import { ProjectService } from '../project.service';
import { Project } from '../project';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-update-project',
  templateUrl: './update-project.component.html',
  styleUrls: ['./update-project.component.css']
})
export class UpdateProjectComponent implements OnInit {

  id!: number;
  project: Project = new Project();
  constructor(private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.projectService.getProjectById(this.id).subscribe(data => {
      this.project = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.projectService.updateProject(this.id, this.project).subscribe( data =>{
      this.goToProjectList();
    }
    , error => console.log(error));
  }

  goToProjectList(){
    this.router.navigate(['/projects']);
  }
}
