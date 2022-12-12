import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from './project';
import { Student } from './student';
import { StudentProject } from './student-project';
import {Numprst} from './numprst'
@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseURL = "http://localhost:8080/api/v2";

  constructor(private httpClient: HttpClient) { }
  getStudentsList():Observable<Student[]>{
    return this.httpClient.get<Student[]>(`${this.baseURL}`+"/students");
  }
  createStudent(Student: Student): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/addStudent`, Student);
  }

  getStudentById(id: number): Observable<Student>{
    return this.httpClient.get<Student>(`${this.baseURL}/students/${id}`);
  }

  updateStudent(id: number, Student: Student): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/studentUp/${id}`, Student);
  }

  deleteStudent(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/studentDel/${id}`);
  }



  
  getStudentsandProjectByID(id:number):Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseURL}/ProjectsByStudentId/${id}`);
  }
  getAvailableStudentProjectByID(id:number):Observable<Project[]>{
    return this.httpClient.get<Project[]>(`${this.baseURL}/AvailableStudentProject/${id}`);
  }
  createStudentProject(StudentProject: StudentProject): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}/addStudentProject`, StudentProject);
  }
  deleteStudentProject(id:number,id_project:number): Observable<Object>{
    return this.httpClient.get(`${this.baseURL}/DeleteStudentProject?id_student=${id}&id_project=${id_project}`);
  }


  updateNumberStudentPr( Numprst: Numprst): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/NumberOfProjectForEachSt`, Numprst);
  }
  getNumPrSt():Observable<number>{
    return this.httpClient.get<number>(`${this.baseURL}/NumberOfProjectForEachSt`);
  }
}
