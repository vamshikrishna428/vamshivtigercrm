package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
    FileInputStream fisExel=new FileInputStream("./src/test/resoueces/testdata.xlsx");
    Workbook wb = WorkbookFactory.create(fisExel);
    Sheet sheet = wb.getSheet("contact");
	}

}
