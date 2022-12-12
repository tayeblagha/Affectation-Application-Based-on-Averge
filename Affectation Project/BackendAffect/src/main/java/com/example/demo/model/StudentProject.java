package com.example.demo.model;
//besh ta3ti liste deroulante of proj 
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Student_Project")

public class StudentProject implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "student_id")
    private int student;
	@Column(name = "project_id")


private int project;

public StudentProject() {
	super();
}

public StudentProject(int id, int student, int project) {
	super();
	this.id = id;
	this.student = student;
	this.project = project;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getStudent() {
	return student;
}

public void setStudent(int student) {
	this.student = student;
}

public int getProject() {
	return project;
}

public void setProject(int project) {
	this.project = project;
}

}
