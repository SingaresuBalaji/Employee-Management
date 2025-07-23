package com.example.balaji.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.balaji.model.Employee;
import com.example.balaji.repo.EmployeeRepo;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepo;

	// Get All Employees
	@GetMapping("employees")
	public List<Employee> getAllEmployees() {

		return employeeRepo.findAll();

	}

	// Find by Emp ID
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

	// Add Employee
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
	}

	// Update Employee
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
	    if (employee.getId() == null || !employeeRepo.existsById(employee.getId())) {
	        return ResponseEntity.notFound().build();
	    }
	    Employee updated = employeeRepo.save(employee);
	    return ResponseEntity.ok(updated);
	}

	// Delete Employee
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
