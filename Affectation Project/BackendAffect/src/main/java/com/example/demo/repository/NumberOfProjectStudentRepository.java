package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.NumberOfProjectsForEachStudent;
@Repository
public interface NumberOfProjectStudentRepository extends JpaRepository<NumberOfProjectsForEachStudent, Integer> {

}
