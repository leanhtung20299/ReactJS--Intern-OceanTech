package com.globits.sample.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.core.Constants;
import com.globits.core.domain.Country;
import com.globits.sample.domain.Animal;
import com.globits.sample.domain.Employee;
import com.globits.sample.dto.AnimalById;
import com.globits.sample.dto.AnimalDto;
import com.globits.sample.dto.EmployeeById;
import com.globits.sample.dto.EmployeeDto;
import com.globits.sample.dto.EmployeeListSize;
import com.globits.sample.dto.ExcelExporter;
import com.globits.sample.dto.ExcelExpoterAPI;
import com.globits.sample.service.AnimalService;
import com.globits.sample.service.EmployeeService;


@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value="/{pageIndex}/{pageSize}",method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public  EmployeeDto getList(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		EmployeeDto employeeDto = new EmployeeDto();
		Page<Employee> page =  employeeService.listEmployeePage(pageIndex, pageSize);
		List<Employee> list = page.getContent();
		employeeDto.setList(list);
		return employeeDto;
	}
	@RequestMapping(value = "/lengthlist",method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public EmployeeListSize getListsize() {
		int size = employeeService.listEmployee().size();
		EmployeeListSize list=  new EmployeeListSize();
		list.setLength(size);
		return list;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public EmployeeDto getListValue() {
		EmployeeDto employeeDto = new EmployeeDto();
		List<Employee> list = employeeService.listEmployee();
		employeeDto.setList(list);
		return employeeDto;
	}
	
	@RequestMapping(value = "/getdataaftersave",method = RequestMethod.GET)
	@PreAuthorize("isAuthenticated()")
	public  EmployeeDto getListdata() {
		EmployeeDto employeeDto = new EmployeeDto();
		List<Employee> list = employeeService.listEmployee();
		List<Employee> list2 = new ArrayList<>();
		int i=0;
		list2.add(list.get(list.size()-1));
		for (Employee employee2 : list) {			
				list2.add(employee2);	
			i++;
			if(i==9) break;
 		}
		for (Employee employee2 : list2) {
			System.out.println(employee2.getId());
		}
		employeeDto.setList(list2);
//		animalresponse.setDate(new Date());
		return employeeDto;
	}

	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public  EmployeeDto UpdateEmployee(@RequestBody Employee employee,@PathVariable("id") Long id) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeService.save(employee);
		
		List<Employee> list = new ArrayList<>();
		
		list.add(employee);
		employeeDto.setList(list);
		return employeeDto;
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public  void SaveEmployee(@RequestBody Employee employee) {	
		employeeService.save(employee);
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public EmployeeDto deleteEmployee (@PathVariable("id") Long id) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeService.delete(id);
		List<Employee> list = employeeService.listEmployee();
		employeeDto.setList(list);
		return employeeDto;
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET)
	public EmployeeById getEmployeelById (@PathVariable("id") Long id) {
		EmployeeById employeeId = new EmployeeById();
		Employee employee = employeeService.findById(id);
		employeeId.setId(employee.getId());
		employeeId.setPhone(employee.getPhone());
		employeeId.setCode(employee.getCode());
		employeeId.setEmail(employee.getEmail());
		employeeId.setAge(employee.getAge());
		employeeId.setName(employee.getName());
		return employeeId;
	}
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/search/{key}/{pageIndex}/{pageSize}" , method = RequestMethod.GET)
	public EmployeeDto SearchEmployee (@PathVariable("key") String key,@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
		EmployeeDto employeeDto = new EmployeeDto();
	    List<Employee> list = employeeService.search(key);
	    for (Employee employee : list) {
			System.out.println(employee.getId());
			
		}
	    List<Employee> list2 = new ArrayList<>();
	    for (int i = 0; i < list.size(); i++) {
	    	if(i >= pageIndex*pageSize ) {
				list2.add(list.get(i));
				pageSize--;
				System.out.println(list.get(i).getId());
			}
			if(pageSize==0) break;
		}
			
	    employeeDto.setList(list2);
		return employeeDto;
	}
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/search/{key}" , method = RequestMethod.GET)
	public EmployeeListSize ListSize(@PathVariable("key") String key) {
		EmployeeListSize employeeListSize = new EmployeeListSize();
	    List<Employee> list = employeeService.search(key);
	    int size = list.size();
	    employeeListSize.setLength(size);
		return employeeListSize;
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/Excel" , method = RequestMethod.GET)
	public void ExportToExcel  (HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=excel.xlsx";
		
		response.setHeader(headerKey,headerValue);
		List<Employee> list = employeeService.listEmployee();
		ExcelExpoterAPI exporter = new ExcelExpoterAPI(list);
		exporter.Export(response);
	}
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
	@RequestMapping(value = "/Excel/{key}" , method = RequestMethod.GET)
	public void ExportToExcel  (HttpServletResponse response , @PathVariable("key") String key) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=excel.xlsx";	
		response.setHeader(headerKey,headerValue);	
			List<Employee> list = employeeService.search(key);
			ExcelExpoterAPI exporter = new ExcelExpoterAPI(list);
			exporter.Export(response);
	}
	
}
