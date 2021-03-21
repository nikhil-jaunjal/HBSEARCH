package com.nikhil.service;

import com.nikhil.model.ManagerEntity;

public interface ManagerService {
	
	Object getAllManagers() throws Exception;
	
	Object getManagerById(Integer id) throws Exception;
	
	Object saveManagerDetails(ManagerEntity managerEntity) throws Exception;
	
	Object deleteManager(Integer id) throws Exception;
}