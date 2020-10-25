package com.globits.sample.service.impl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.sample.domain.Animal;
import com.globits.sample.domain.Employee;
import com.globits.sample.repository.AnimalRepository;
import com.globits.sample.repository.EmployeeRepository;
import com.globits.sample.service.AnimalService;
import com.globits.sample.service.EmployeeService;
@Transactional
@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, Long> implements EmployeeService { 
	@Autowired
    EmployeeRepository employeeRepository;
	public List<Employee> listEmployee (){
		return employeeRepository.findAll();	
	}
	public List<Employee> search(String key) {
		return employeeRepository.searchEmployee(key);
	}

	public Page<Employee> listEmployeePage(int pageIndex, int pageSize) {
		return  employeeRepository.findAll(PageRequest.of(pageIndex, pageSize));
	}
}
