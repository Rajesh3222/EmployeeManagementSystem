package com.EmployeeManagement.EmployeeDepartments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagement.EmployeeDepartments.model.AuthenticationRequest;
import com.EmployeeManagement.EmployeeDepartments.model.AuthenticationResponse;
import com.EmployeeManagement.EmployeeDepartments.model.Department;
import com.EmployeeManagement.EmployeeDepartments.model.Employee;
import com.EmployeeManagement.EmployeeDepartments.security.JwtUtils;
import com.EmployeeManagement.EmployeeDepartments.security.MyUserDetailsService;
import com.EmployeeManagement.EmployeeDepartments.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtils jwtTokenUtil;

	// display list of employees

	@GetMapping("/get/employees")
	public List<Employee> getEmployees() {
		return employeeService.getEmployeesList();
	}

	@GetMapping("/get/employees/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeService.getSingleEmployee(id);
	}

	@PostMapping("/employees")
	public void addDepartment(@RequestBody Department department) {
		employeeService.createNewDepartment(department);
	}

	@PutMapping("/employees/{id}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
		employeeService.updateDeatilsOfEmployee(employee, id);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)

	private ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

}
