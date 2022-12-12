package com.example.demo.model;

public class Affectation {
	public static int counter=0;
private int id;
private String studentName ;
private String projectName  ;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getStudentName() {
	return studentName;
}
@Override
public String toString() {
	return "Affectation [id=" + id + ", studentName=" + studentName + ", projectName=" + projectName + "]";
}
public void setStudentName(String studentName) {
	this.studentName = studentName;
}
public String getProjectName() {
	return projectName;
}
public void setProjectName(String projectName) {
	this.projectName = projectName;
}
public Affectation(String studentName, String projectName) {
	super();
	id=counter;
			counter ++;
	this.studentName = studentName;
	this.projectName = projectName;
}

}
