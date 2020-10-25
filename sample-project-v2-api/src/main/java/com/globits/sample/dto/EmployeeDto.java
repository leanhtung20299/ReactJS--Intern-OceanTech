package com.globits.sample.dto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globits.sample.domain.Animal;
import com.globits.sample.domain.Employee;

public class EmployeeDto implements Serializable{
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<Employee> list;
	
//	private Date date;
	
	
	public EmployeeDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Employee> getList() {
		return list;
	}
	public void setList(List<Employee> list) {
		this.list = list;
	}
}
