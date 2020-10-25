package com.globits.sample.rest;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.core.Constants;
import com.globits.sample.domain.Employee;
import com.globits.sample.domain.InformationReport;
import com.globits.sample.dto.ExcelExporter;
import com.globits.sample.service.AnimalService;
import com.globits.sample.service.ReportService;

@RestController
@RequestMapping("/api/ReportController")
public class RestReportController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ReportService reportService;
	
	@Secured({ Constants.ROLE_ADMIN,Constants.ROLE_USER})
//	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/Excel" , method = RequestMethod.GET)
	public void ExportToExcel  (HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=excel.xlsx";
		
		response.setHeader(headerKey,headerValue);
		
		List<InformationReport> list = reportService.listreport();
		ExcelExporter exporter = new ExcelExporter(list);
		exporter.Export(response);
	}
}
