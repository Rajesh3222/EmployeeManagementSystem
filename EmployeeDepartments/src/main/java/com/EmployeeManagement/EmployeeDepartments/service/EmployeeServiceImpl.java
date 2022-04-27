package com.EmployeeManagement.EmployeeDepartments.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.EmployeeDepartments.model.Department;
import com.EmployeeManagement.EmployeeDepartments.model.Employee;
import com.EmployeeManagement.EmployeeDepartments.repository.DepartmentRepository;
import com.EmployeeManagement.EmployeeDepartments.repository.EmployeeRepository;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentReository;
	
	public List<Employee> getEmployeesList() {
		return employeeRepository.findAll();
		
	}
	
	public Employee getSingleEmployee(Long id) {
		
		 Optional<Employee>emp= employeeRepository.findById(id);
		 if(!emp.isPresent())
			 throw new RuntimeException("Employee not found for id"+id);
		 return emp.get();
	
	}

	@Override
	public void createNewDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentReository.save(department);
	}

	@Override
	public void updateDeatilsOfEmployee(Employee employee, Long id) {
		// TODO Auto-generated method stub
	
	 for(Long i=0L;i<=employeeRepository.count();i++) {
		 @SuppressWarnings("deprecation")
         Employee emp= employeeRepository.getById(i);
         if(emp.getEmpId().equals(id)) {
        	 emp.setName(employee.getName());
        	 emp.setAge(employee.getAge());
        	 emp.setJoiningDate(employee.getJoiningDate());
        	 emp.setDepartment(employee.getDepartment());
        	 emp.setQualification(employee.getJoiningDate());
        	 emp.setTotalExperience(employee.getTotalExperience());
        		employeeRepository.save(emp);
         }
	 }
	

	
	
		}
	}	


