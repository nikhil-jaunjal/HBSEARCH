package com.nikhil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.model.ManagerEntity;
import com.nikhil.services.impl.ManagerServiceImpl;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	private ManagerServiceImpl managerService;
	
	@GetMapping()
	public Object getAllManagers() throws Exception {
		return managerService.getAllManagers();
	}
	
	@GetMapping("/{id}")
	public Object getManagerById(@PathVariable Integer id) throws Exception {
		return managerService.getManagerById(id);
	}
	
	@PostMapping
	public Object saveManagerDetails(@RequestBody ManagerEntity managerEntity) throws Exception {
		return managerService.saveManagerDetails(managerEntity);
	}
	
	@PutMapping()
	public Object updateManagerDetails(@RequestBody ManagerEntity managerEntity) throws Exception {
		return managerService.saveManagerDetails(managerEntity);
	}

	@DeleteMapping("/{id}")
	public Object deleteManager(@PathVariable("id") Integer id) throws Exception{
		return managerService.deleteManager(id);
	}
}
