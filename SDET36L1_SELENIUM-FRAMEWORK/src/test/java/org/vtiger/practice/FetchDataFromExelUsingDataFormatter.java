package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExelUsingDataFormatter {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		DataFormatter dataFormat=new DataFormatter();
		FileInputStream fis=new FileInputStream("./src/test/resources/testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = dataFormat.formatCellValue(wb.getSheet("contact").getRow(5).getCell(1));
		System.out.println(data);
		wb.close();
	}
}
