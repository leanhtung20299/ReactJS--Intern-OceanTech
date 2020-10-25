package com.globits.sample.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globits.core.domain.Country;
import com.globits.sample.domain.Animal;

public class AnimalDto implements Serializable{
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private List<Animal> list;
//	private Date date;
	public AnimalDto() {
		// TODO Auto-generated constructor stub
	}
	public List<Animal> getList() {
		return list;
	}
	public void setList(List<Animal> list) {
		this.list = list;
	}
	
//	public void setDate(Date date) {
//		this.date = date;
//	}
//	public Date getDate() {
//		return date;
//	} 
}
