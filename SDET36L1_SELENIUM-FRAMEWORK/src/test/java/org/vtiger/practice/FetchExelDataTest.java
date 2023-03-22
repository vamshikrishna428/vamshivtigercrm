package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchExelDataTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
      FileInputStream fisExel=new FileInputStream("./src/test/resources/testdata.xlsx");
      Workbook workbook = WorkbookFactory.create(fisExel);
      String data = workbook.getSheet("contact").getRow(2).getCell(1).getStringCellValue();
      System.out.println(data);
      workbook.close();
      
	}

}
