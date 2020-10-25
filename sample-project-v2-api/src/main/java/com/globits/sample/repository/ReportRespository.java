package com.globits.sample.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.globits.sample.domain.InformationReport;

@Repository
public interface ReportRespository  extends JpaRepository<InformationReport,Long>{
	
}
