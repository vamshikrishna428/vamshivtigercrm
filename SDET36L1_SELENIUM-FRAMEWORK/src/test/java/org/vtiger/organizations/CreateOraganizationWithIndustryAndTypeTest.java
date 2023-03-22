package org.vtiger.organizations;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOraganizationWithIndustryAndTypeTest {

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
		String expectedOrganizationName =excelUtility.getDataFromExcel("organization",4,1)+randomNumber;
		String expectedIndustry =excelUtility.getDataFromExcel("organization",4,2);
		String expectedType =excelUtility.getDataFromExcel("organization",4,3);
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
		WebElement industryDropdown = driver.findElement(By.xpath("//select[@name='industry']"));
		webDriverUtility.selectDropdown("Education", industryDropdown);
		WebElement typeDropdown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		webDriverUtility.selectDropdown("Press", typeDropdown);
		driver.findElement(By.xpath("//input[@name='assigntype' and @value='T']")).click();
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String actualOrgName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		String actualIndustry = driver.findElement(By.xpath("//font[.='Education']")).getText();
		String actualType = driver.findElement(By.xpath("//font[.='Press']")).getText();
		if(actualOrgName.equals(expectedOrganizationName) && actualIndustry.equals(expectedIndustry) && actualType.equals(expectedType))
			javaUtility.print("Test case is passed");
		else
			javaUtility.print("Test case is failed");
		WebElement  adminstrationIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webDriverUtility.mouseHoverToElement(adminstrationIcon);
		driver.findElement(By.linkText("Sign Out")).click();
		excelUtility.closeExcelWorkbook();
		webDriverUtility.closeAllBrowser();

	}

}
