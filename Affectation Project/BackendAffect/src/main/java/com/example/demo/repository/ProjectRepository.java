package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.model.Student;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	 

}
