package com.EmployeeManagement.EmployeeDepartments.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.EmployeeManagement.EmployeeDepartments.model.Department;
import com.EmployeeManagement.EmployeeDepartments.model.Employee;

@Service
public interface EmployeeService {
List<Employee> getEmployeesList();
Employee getSingleEmployee(Long id);
void createNewDepartment(Department department);
void updateDeatilsOfEmployee(Employee employee, Long id);
}
