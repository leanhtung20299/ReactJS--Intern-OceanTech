package com.globits.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.sample.domain.Employee;
import com.globits.sample.domain.InformationReport;
import com.globits.sample.repository.EmployeeRepository;
import com.globits.sample.repository.ReportRespository;
import com.globits.sample.service.EmployeeService;
import com.globits.sample.service.ReportService;

@Transactional
@Service
public class ReportServiceImpl extends GenericServiceImpl<InformationReport, Long> implements ReportService{
	@Autowired
	ReportRespository reportRespository;
	
	public List<InformationReport> listreport(){
		return reportRespository.findAll();
	}
}
