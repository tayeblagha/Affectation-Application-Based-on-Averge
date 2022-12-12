import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Project } from './project';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  private baseURL = "http://localhost:8080/api/v2";

  constructor(private httpClient: HttpClient) { }
  getProjectsList():Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseURL}`+"/projects");
  }


  createProject(Project: Project): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/addProject`, Project);
  }

  getProjectById(id: number): Observable<Project>{
    return this.httpClient.get<Project>(`${this.baseURL}/project/${id}`);
  }

  updateProject(id: number, Project: Project): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/projectUp/${id}`, Project);
  }

  deleteProject(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/projectDel/${id}`);
  }



}
