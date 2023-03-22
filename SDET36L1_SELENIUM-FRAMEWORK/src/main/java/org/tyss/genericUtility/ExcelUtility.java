package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * This class contains excel related reusable methods
 * @author 
 *
 */
public final class ExcelUtility {
	private Workbook workbook;
	/**
	 * This method is used to initialize excelfile
	 * @param filePath
	 */
	public void initializeExcelFile(String filePath) {
		FileInputStream fisExel=null;
		
			try {
				fisExel = new FileInputStream(filePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				workbook = WorkbookFactory.create(fisExel);
			} catch (EncryptedDocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public String getDataFromExcel(String sheetname,int rownum,int cellnum) {
		return new DataFormatter().formatCellValue(workbook.getSheet(sheetname).getRow(rownum).getCell(cellnum));
	}
	/**
	 * This method will close the workbook operation
	 */
	public void closeExcelWorkbook() {
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
