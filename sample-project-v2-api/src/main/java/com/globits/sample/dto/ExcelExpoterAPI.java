package com.globits.sample.dto;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.globits.sample.domain.Employee;
import com.globits.sample.domain.InformationReport;
public class ExcelExpoterAPI {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Employee> listemployee;
	
	
	public ExcelExpoterAPI(List<Employee> listemployee) {
		this.listemployee = listemployee;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Employee");
	}
	
	private void writeHeaderRows() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("id");
		
		 cell = row.createCell(1);
		cell.setCellValue("code");
		
		cell = row.createCell(2);
		cell.setCellValue("name");
		
		 cell = row.createCell(3);
		cell.setCellValue("email");
		
		
		 cell = row.createCell(4);
		cell.setCellValue("phone");
		
		cell = row.createCell(5);
		cell.setCellValue("age");
		
		
	}
	private void writeDataRows() {
		int rowCount = 1 ;
		for (Employee employee : listemployee) {
			Row row =  sheet.createRow(rowCount);
			rowCount++;
		
			Cell cell = row.createCell(0);
			cell.setCellValue(employee.getId());
			
			 cell = row.createCell(1);
			cell.setCellValue(employee.getCode());

			 cell = row.createCell(2);
			cell.setCellValue(employee.getName());
			
			 cell = row.createCell(3);
			cell.setCellValue(employee.getEmail());
			
			 cell = row.createCell(4);
			cell.setCellValue(employee.getPhone());
			
			 cell = row.createCell(5);
			cell.setCellValue(employee.getAge());
			
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
