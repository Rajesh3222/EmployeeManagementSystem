package com.EmployeeManagement.EmployeeDepartments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagement.EmployeeDepartments.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	
}
