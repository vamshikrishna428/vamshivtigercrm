package org.vtiger.practice;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.DatabaseUtility;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class ValidateGuiWrtDatabase_rmgyantraTest {

	public static void main(String[] args) {
		JavaUtility javaUtility=new JavaUtility();
		FileUtility fileUtility=new FileUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		DatabaseUtility databaseUtility=new DatabaseUtility();
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		int randomNumber =javaUtility.getRandomNumber();
		fileUtility.initializePropertyFile(IConstants.RMGYANTRAPROPERTYFILEPATH);
		excelUtility.initializeExcelFile(IConstants.RMGYANTRAEXCELFILEPATH);
		String url=fileUtility.getDataFromPropertyfile("url");
		String userName=fileUtility.getDataFromPropertyfile("username");
		String password=fileUtility.getDataFromPropertyfile("password");
		String timeouts=fileUtility.getDataFromPropertyfile("duration");
		String browser=fileUtility.getDataFromPropertyfile("browser");
		String projectName =excelUtility.getDataFromExcel("rmgyantra", 2, 1)+randomNumber;
		long timeoutsLong = javaUtility.convertStringToLong(timeouts);
		WebDriver driver= webDriverUtility.openBrowser(browser);
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("ap");
		WebElement projectStatusDropdown = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		webDriverUtility.selectDropdown(projectStatusDropdown, "Created");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		javaUtility.print("Project is successfully created");
		webDriverUtility.closeAllBrowser();
		String mySqlUrl = fileUtility.getDataFromPropertyfile("mysqlurl");
		String mySqlUserName = fileUtility.getDataFromPropertyfile("mysqlusername");
		String mySqlPassword = fileUtility.getDataFromPropertyfile("mysqlpassword");
		databaseUtility.getConnectionToDatabase(mySqlUrl+"/projects",mySqlUserName,mySqlPassword);
        List<String> allProjectName = databaseUtility.executeDatabaseQuery("select * from project;","project_name");
		for(String actualProjectName:allProjectName){
				if(actualProjectName.equals(projectName)) {
					javaUtility.print("project is present inside database");
					javaUtility.print("Actual project name is-->"+actualProjectName);
					break;
				}
			}
			databaseUtility.closeDataBaseConnection();

	}

}
