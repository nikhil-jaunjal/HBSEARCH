package com.nikhil.service;

import com.nikhil.model.EmployeeEntity;

public interface EmployeeService {
	
	Object getAllEmployees() throws Exception;
	
	Object getEmployeeById(Integer id) throws Exception;
	
	Object getEmployeesByKeywords(String keyword, Integer age) throws Exception; 
	
	Object saveEmployeeDetails(EmployeeEntity employeeEntity) throws Exception;
	
	Object deleteEmployee(Integer id) throws Exception;

}
