package com.globits.sample.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.sample.domain.Employee;

public interface EmployeeService extends GenericService<Employee, Long> {
	List<Employee> listEmployee();
	List<Employee> search(String key);
	Page<Employee> listEmployeePage(int pageIndex,int pageSize);
}