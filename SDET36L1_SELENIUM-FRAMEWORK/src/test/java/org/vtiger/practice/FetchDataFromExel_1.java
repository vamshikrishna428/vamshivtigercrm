package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExel_1 {
		public static void main(String[] args) throws Exception {
			DataFormatter dataFormat=new DataFormatter();
			FileInputStream fis=new FileInputStream("./src/test/resources/testdata.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet("contact");
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				String data = dataFormat.formatCellValue(sheet.getRow(i).getCell(1));
				if(data.equalsIgnoreCase("organizationName"))
				System.out.println(dataFormat.formatCellValue(sheet.getRow(i+2).getCell(1)));
		}
			wb.close();
	}

}
