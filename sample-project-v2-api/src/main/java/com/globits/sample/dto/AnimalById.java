package com.globits.sample.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globits.sample.domain.Animal;

public class AnimalById implements Serializable{
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Long id;
	private String name;
	private String code;
	private String description;
	public AnimalById() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
