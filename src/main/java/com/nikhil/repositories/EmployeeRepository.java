package com.nikhil.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nikhil.model.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer>{

}
