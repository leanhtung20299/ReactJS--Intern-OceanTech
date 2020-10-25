package com.globits.sample.dto;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.globits.sample.domain.InformationReport;

public class ExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<InformationReport> informationReport;
	
	
	public ExcelExporter(List<InformationReport> informationReport) {
		this.informationReport = informationReport;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("informationReport");
	}
	
	private void writeHeaderRows() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("leadId");
		
		 cell = row.createCell(1);
		cell.setCellValue("name");
		
		cell = row.createCell(2);
		cell.setCellValue("agencyId");
		
		 cell = row.createCell(3);
		cell.setCellValue("doId");
		
		
		 cell = row.createCell(4);
		cell.setCellValue("offer_id");
		
		cell = row.createCell(5);
		cell.setCellValue("price");
		
		cell = row.createCell(6);
		cell.setCellValue("terms");
		
		cell = row.createCell(7);
		cell.setCellValue("lead_revenue");
		
		cell = row.createCell(8);
		cell.setCellValue("lead_status");
		
		cell = row.createCell(9);
		cell.setCellValue("product_id");
		
		cell = row.createCell(10);
		cell.setCellValue("product_name");
		
		cell = row.createCell(11);
		cell.setCellValue("create_date");
		
		cell = row.createCell(12);
		cell.setCellValue("lead_phone");
		
		cell = row.createCell(13);
		cell.setCellValue("click_id");
		
		cell = row.createCell(14);
		cell.setCellValue("org_id");
		
		cell = row.createCell(15);
		cell.setCellValue("status_Od");
		
		cell = row.createCell(16);
		cell.setCellValue("unit");
		
		cell = row.createCell(17);
		cell.setCellValue("affiliate_id");
		
	}
	private void writeDataRows() {
		int rowCount = 1 ;
		for (InformationReport informationReport2 : informationReport) {
			
			Row row = sheet.createRow(rowCount);
			rowCount++;
			
			Cell cell = row.createCell(0);
			cell.setCellValue(informationReport2.getLeadId());
			
			cell = row.createCell(1);
			cell.setCellValue(informationReport2.getName());
			
			 cell = row.createCell(2);
			cell.setCellValue(informationReport2.getAgencyId());
				
			 cell = row.createCell(3);
			cell.setCellValue(informationReport2.getDoId());
			
			 cell = row.createCell(4);
			cell.setCellValue(informationReport2.getOffer_id());
			
			cell = row.createCell(5);
			cell.setCellValue(informationReport2.getPrice());

			cell = row.createCell(6);
			cell.setCellValue(informationReport2.getTerms());
			
			cell = row.createCell(7);
			cell.setCellValue(informationReport2.getLead_revenue());
			
			cell = row.createCell(8);
			cell.setCellValue(informationReport2.getLead_status());
			
			cell = row.createCell(9);
			cell.setCellValue(informationReport2.getProduct_id());
			
			cell = row.createCell(10);
			cell.setCellValue(informationReport2.getProduct_name());
			
			cell = row.createCell(11);
			cell.setCellValue(informationReport2.getCreate_date());
			
			cell = row.createCell(12);
			cell.setCellValue(informationReport2.getLead_phone());
			
			cell = row.createCell(13);
			cell.setCellValue(informationReport2.getClick_id());
			
			cell = row.createCell(14);
			cell.setCellValue(informationReport2.getOrg_id());
			
			cell = row.createCell(15);
			cell.setCellValue(informationReport2.getStatus_Od());
			
			cell = row.createCell(16);
			cell.setCellValue(informationReport2.getUnit());
			
			cell = row.createCell(17);
			cell.setCellValue(informationReport2.getAffiliate_id());	
		}

	}
	public void Export(HttpServletResponse response) throws IOException {
		writeHeaderRows();
		writeDataRows();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
