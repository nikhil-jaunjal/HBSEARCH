package com.nikhil.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.config.CustomDozerHelper;
import com.nikhil.model.ManagerEntity;
import com.nikhil.repositories.ManagerRepository;
import com.nikhil.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService{
	
	@Autowired
	private ManagerRepository managerRepository;
	private Mapper mapper = new DozerBeanMapper();

	public Object getAllManagers(){	
		Map<String, Object> data = new HashMap<>();		
		try {			
			List<ManagerEntity> managerList =  (List<ManagerEntity>) managerRepository.findAll();
			data.put("managerList",CustomDozerHelper.map(mapper, managerList, ManagerEntity.class));
		} catch (Exception e) {	
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
	
	public Object getManagerById(Integer id) {
		Map<String, Object> data = new HashMap<>();
		try {
			Object managerDetails = managerRepository.findById(id);
			data.put("managerDetails", managerDetails);
		} catch(Exception e) {
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
	
	public Object saveManagerDetails(ManagerEntity managerEntity) {
		Map<String, Object> data = new HashMap<>();
		try {			
			data.put("Success", managerRepository.save(managerEntity));
		} catch (Exception e) {
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
		
	public Object deleteManager(Integer id) { 
		 Map<String, Object> data = new HashMap<>();
			try {				
				managerRepository.deleteById(id);
				data.put("success", "manager data of id "+ id + " deleted");
			}catch (Exception e) {
				e.printStackTrace();
				data.put("error", e.getMessage());
			}
			return data;
	}
	
}
