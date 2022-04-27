package com.EmployeeManagement.EmployeeDepartments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeManagement.EmployeeDepartments.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
