package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcel(String sheetname, int rownum, int cellnum) throws IOException {
		FileInputStream fis = new FileInputStream("./TestData/TestCase.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue().toString();
		wb.close();
		return data;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		FileInputStream fis = new FileInputStream("./TestData/TestCase.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
	    int rowCount = wb.getSheet(sheetname).getLastRowNum();
		wb.close();
		return rowCount;
		}
	
	public void setDataToExcel(String sheetname, int rownum, int cellnum, String data) throws IOException {
		FileInputStream fis = new FileInputStream("./TestData/TestCase.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).setCellValue(data);
		
		FileOutputStream fos = new FileOutputStream("./TestData/TestCase.xlsx");
		wb.write(fos);
		wb.close();
	}	
}
