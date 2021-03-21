package com.nikhil.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.config.CustomDozerHelper;
import com.nikhil.model.EmployeeEntity;
import com.nikhil.repositories.EmployeeRepository;
import com.nikhil.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private EmployeeRepository employeeRepository;
	private Mapper mapper = new DozerBeanMapper();
	
		
	public Object getAllEmployees(){
		Map<String, Object> data = new HashMap<>();		
		try {			
			List<EmployeeEntity> employeeList =  (List<EmployeeEntity>) employeeRepository.findAll();
			data.put("employeeList",CustomDozerHelper.map(mapper, employeeList, EmployeeEntity.class));
		} catch (Exception e) {	
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
	
	public Object getEmployeeById(Integer id) {
		Map<String, Object> data = new HashMap<>();
		try {
			Object employeeDetails = employeeRepository.findById(id);
			data.put("employeeDetails", employeeDetails);
		} catch(Exception e) {
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
	
	public Object saveEmployeeDetails(EmployeeEntity employeeEntity) {
		
		Map<String, Object> data = new HashMap<>();
		try {			
			data.put("Success", employeeRepository.save(employeeEntity));
		} catch (Exception e) {
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
		
	public Object deleteEmployee(Integer id) {
		Map<String, Object> data = new HashMap<>();
		try {				
			employeeRepository.deleteById(id);
			data.put("success", "employee data of id "+ id + " deleted");
		}catch (Exception e) {
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}
		
	public Object getEmployeesByKeywords(String keyword, Integer age) throws Exception{
		
		SearchSession searchSession = Search.session( entityManager );
		SearchResult<EmployeeEntity> result = null;
		Map<String, Object> data = new HashMap<>();
		
		try {
				if(keyword != null && age == null) {
						result = searchSession.search( EmployeeEntity.class )
								.where( f -> 
										f.match() 
										.fields( "firstName", "lastName", "address" )
										.matching( keyword ) )
//								.sort( f -> f.field( "empId" ).then().field("firstName") )
								.fetchAll();
//								.fetch( 1, 4 ); // fetch(page number, page size)
				}else if(keyword == null && age != null) {
					result = searchSession.search( EmployeeEntity.class )
							.where( f -> 
									f.match() 
									.fields( "age" )
									.matching( age ) )
							.fetchAll();
				}
				if(result != null) {			
					long totalHitCount = result.total().hitCount(); 
					List<EmployeeEntity> hits = result.hits();
					data.put("result", hits);
					data.put("totalRecords", totalHitCount);
				}else {			
					data.put("error", "Please enter some input to search");
				}
		} catch (Exception e) {
			e.printStackTrace();
			data.put("error", e.getMessage());
		}
		return data;
	}	
}
