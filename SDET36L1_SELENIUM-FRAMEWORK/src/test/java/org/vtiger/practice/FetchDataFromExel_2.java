package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExel_2 {

	public static void main(String[] args) throws Exception {
         DataFormatter dataFormat=new DataFormatter();
         FileInputStream fisExel=new FileInputStream("./src/test/resources/testdata.xlsx");
         Workbook wb = WorkbookFactory.create(fisExel);
         Sheet sheet = wb.getSheet("contact");
         Row row = sheet.getRow(0);
         int lastRowNum = sheet.getLastRowNum();
         short lastCellNum = row.getLastCellNum();
         String str[][]=new String[lastRowNum][lastCellNum];
         for(int i=1;i<=lastRowNum;i++) {
        	 for(int j=0;j<lastCellNum;j++) {
        		 str[i-1][j] = dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
        	 }
         }
    System.out.println(str[1][1]);    
	}

}
