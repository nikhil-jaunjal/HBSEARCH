package com.nikhil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.model.EmployeeEntity;
import com.nikhil.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping()
	public Object getAllEmployees() throws Exception {
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Object getEmployeeById(@PathVariable Integer id) throws Exception {
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping("/search")
	public Object getSearchedEmployees(
			@RequestParam(required = false) String keyword, 
			@RequestParam(required = false) Integer age
	) throws Exception {
		return employeeService.getEmployeesByKeywords(keyword, age);
	}
	
	@PostMapping
	public Object saveEmployeeDetails(@RequestBody EmployeeEntity employeeEntity) throws Exception {
		return employeeService.saveEmployeeDetails(employeeEntity);
	}
	
	@PutMapping()
	public Object updateEmployeeDetails(@RequestBody EmployeeEntity employeeEntity) throws Exception{
		return employeeService.saveEmployeeDetails(employeeEntity);
	}

	@DeleteMapping("/{id}")
	public Object deleteEmployee(@PathVariable("id") Integer id) throws Exception{
		return employeeService.deleteEmployee(id);
	}
}
