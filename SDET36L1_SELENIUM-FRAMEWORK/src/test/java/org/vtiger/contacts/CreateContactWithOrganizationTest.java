package org.vtiger.contacts;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {

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
		String expectedContactLastName=excelUtility.getDataFromExcel("contact", 2, 1)+randomNumber;
		String expectedOrganizationName=excelUtility.getDataFromExcel("contact", 4, 1)+randomNumber;
		long timeoutsLong =javaUtility.convertStringToLong(timeouts);  
		WebDriver driver=webDriverUtility.openBrowser(browser); 
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		webDriverUtility.initiallizeExplicitWait(timeoutsLong,IConstants.LONGPOLLINGTIME);
		webDriverUtility.waitTillElementIsVisible(driver.findElement(By.xpath("//span[@class='dvHeaderText']")));
		driver.findElement(By.xpath("//a[.='Contacts']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedContactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		webDriverUtility.switchToWindow("url","Accounts");
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[.='"+expectedOrganizationName+"']")).click();
		webDriverUtility.switchToWindow("url","Contacts");
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String actualContactLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText().trim();
		String actualOrganizationName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).getText().trim();
		if(actualContactLastName.equals(expectedContactLastName) && actualOrganizationName.equals(expectedOrganizationName))
			javaUtility.print("Test case passed");
		else
			javaUtility.print("Test case is failed");
		WebElement adminstrationIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.mouseHoverToElement(adminstrationIcon);
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		excelUtility.closeExcelWorkbook();
		webDriverUtility.closeAllBrowser();
	}

}
