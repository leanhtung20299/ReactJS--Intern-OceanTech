package com.globits.sample.service;

import java.util.List;

import com.globits.core.service.GenericService;
import com.globits.sample.domain.InformationReport;

public interface ReportService extends GenericService<InformationReport, Long>{
	List<InformationReport> listreport();
}
