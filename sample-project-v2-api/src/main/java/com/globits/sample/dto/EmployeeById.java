package com.globits.sample.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class EmployeeById implements Serializable{
		@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
		private Long id;
		private String code;
		private String email;
		private String phone;
		private Integer age;
		private String name;
			
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public EmployeeById() {
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}
		
}
