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

public class ValidateDatabaseWrtGui_rmgyantraTest {

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
		String expectedProjectName =excelUtility.getDataFromExcel("rmgyantra", 4, 1)+randomNumber;
		long timeoutsLong = javaUtility.convertStringToLong(timeouts);
		String mySqlUrl = fileUtility.getDataFromPropertyfile("mysqlurl");
		String mySqlUserName = fileUtility.getDataFromPropertyfile("mysqlusername");
		String mySqlPassword = fileUtility.getDataFromPropertyfile("mysqlpassword");
		String projectId = excelUtility.getDataFromExcel("rmgyantra",4,2)+randomNumber;
		String createdBy = excelUtility.getDataFromExcel("rmgyantra",4,3);
		String status = excelUtility.getDataFromExcel("rmgyantra",4,4);
		String teamSize= excelUtility.getDataFromExcel("rmgyantra",4,5);
		String date = javaUtility.getCurrentDate("dd/MM/yyyy");
		databaseUtility.getConnectionToDatabase(mySqlUrl+"/projects",mySqlUserName,mySqlPassword);
		databaseUtility.updateDBQuery("insert into project values('"+projectId+"','"+createdBy+"','"+date+"','"+expectedProjectName+"','"+status+"',"+teamSize+");");
		databaseUtility.closeDataBaseConnection();
		WebDriver driver= webDriverUtility.openBrowser(browser);
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		List<WebElement> listOfProjects = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));
		for(WebElement project:listOfProjects) {
			String actualProjectName = project.getText();
			if(actualProjectName.equals(expectedProjectName)) {
				javaUtility.print("Project is present inside list of projects");
				javaUtility.print("Actual project is-->"+actualProjectName);
				break;
			}
		}
		webDriverUtility.closeAllBrowser();
	}

}
