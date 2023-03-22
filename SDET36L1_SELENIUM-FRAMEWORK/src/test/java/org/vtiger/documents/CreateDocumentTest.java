package org.vtiger.documents;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

import com.mysql.cj.protocol.a.CompressedPacketSender;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest {

	public static void main(String[] args) {
		JavaUtility javaUtility=new JavaUtility();
		FileUtility fileUtility=new FileUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		int randomNumber =javaUtility.getRandomNumber();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		excelUtility.initializeExcelFile(IConstants.VTIGEREXCELFILEPATH);
		String url=fileUtility.getDataFromPropertyfile("url");
		String userName=fileUtility.getDataFromPropertyfile("username");
		String password=fileUtility.getDataFromPropertyfile("password");
		String timeouts=fileUtility.getDataFromPropertyfile("duration");
		String browser=fileUtility.getDataFromPropertyfile("browser");
		String expectedTitle=excelUtility.getDataFromExcel("document",2,1)+randomNumber;
		String expectedNote=excelUtility.getDataFromExcel("document",2,2);
		String filePath =excelUtility.getDataFromExcel("document",2,3);
		long timeoutsLong =javaUtility.convertStringToLong(timeouts);  
		WebDriver driver=webDriverUtility.openBrowser(browser); 
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[.='Documents']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(expectedTitle);
		WebElement frame = driver.findElement(By.xpath("//iframe"));
		webDriverUtility.switchFrame(frame);
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(expectedNote,Keys.CONTROL+"a");
		webDriverUtility.switchBackDefaultFrame();
		driver.findElement(By.xpath("//a[@id='cke_5' ]")).click();
		driver.findElement(By.xpath("//a[@id='cke_6' ]")).click();
		driver.findElement(By.xpath("//a[@id='cke_7' ]")).click();
		String expectedFilepath = System.getProperty("user.dir")+filePath;
		driver.findElement(By.xpath("//input[@id='filename_I__']")).sendKeys(expectedFilepath);
		driver.findElement(By.xpath("//input[@class='crmbutton small save' and @type='submit']")).click();
		String actualTitle = driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText().trim();
		String actualNotes = driver.findElement(By.xpath("//td[@class='dvtCellInfo']//strong")).getText().trim();
		String actualFileName = driver.findElement(By.xpath("//td[@class='dvtCellInfo']/a")).getText().trim().replace("_"," ");
		System.out.println(actualFileName);
		String[] splitFilePath = expectedFilepath.split("/");
		String expectedFileName = splitFilePath[splitFilePath.length-1];
		if(actualFileName.equals(expectedFileName) && actualTitle.equals(actualTitle) && actualNotes.equals(expectedNote)){
			javaUtility.print("Pass");
		}
		else {
			javaUtility.print("Fail");
		}
		WebElement adminstrationIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.mouseHoverToElement(adminstrationIcon);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excelUtility.closeExcelWorkbook();
		webDriverUtility.closeAllBrowser();
	}

}
