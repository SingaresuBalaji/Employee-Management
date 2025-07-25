package com.example.balaji.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.balaji.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
