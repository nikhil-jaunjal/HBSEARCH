package com.nikhil.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import lombok.Data;

@Data
@Entity
@Indexed
@Table(name = "Employee")
public class EmployeeEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empId")
	private Integer emp_id;
	
	@FullTextField
	@Column(name = "firstName")
	private String firstName;
	
	@FullTextField
	@Column(name = "lastName")
	private String lastName;
	
	@FullTextField(analyzer = "customAnalyzer")
	@Column(name = "address")
	private String address;
	
	@GenericField
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "manager_id")
	private Integer manager_id;
	
	@Embedded
	@GenericField 
	private CoordinatesEntity location;
	
}
