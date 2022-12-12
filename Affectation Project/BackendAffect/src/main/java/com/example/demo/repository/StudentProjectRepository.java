package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.model.Student;
import com.example.demo.model.StudentProject;
@Repository
public interface StudentProjectRepository extends JpaRepository<StudentProject, Integer> {
/*@Query("SELECT u FROM StudentProject u WHERE u.student_id = :id")
List<StudentProject> findAllProjectByStudentId(@Param("id") int id);*/
@Modifying
@Query(value = "delete FROM Student_Project  WHERE student_id = ?1 and project_id = ?2", nativeQuery = true)
public void DeleteProject(int id_student, int id_project);


@Modifying
@Query(value = "delete FROM Student_Project  WHERE  project_id = ?1", nativeQuery = true)
public void DeleteProjectbyId( int id_project);
}
