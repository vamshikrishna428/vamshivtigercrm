package org.vtiger.contacts;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.ContactsPage;
import org.vtiger.objectRepository.LoginPage;

public class CreateContactTest {

	public static void main(String[] args) {
		//java---->mvn repository
		//maven--->build management tool
		//According java coding std-->class and interface name-->ClassName
		//generating randomnumber
		JavaUtility javaUtility=new JavaUtility();
		int randomNumber =javaUtility.getRandomNumber();
		//handling exception with try catch block
		//initialize data from properties file
		FileUtility fileUtility=new FileUtility();
		fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		//get the control for perticular sheet in exel
		ExcelUtility excelUtility=new ExcelUtility();
		excelUtility.initializeExcelFile(IConstants.VTIGEREXCELFILEPATH);
		//fetching the data from property file
		String url=fileUtility.getDataFromPropertyfile("url");
		String userName=fileUtility.getDataFromPropertyfile("username");
		String password=fileUtility.getDataFromPropertyfile("password");
		String timeouts=fileUtility.getDataFromPropertyfile("duration");
		String browser=fileUtility.getDataFromPropertyfile("browser");
		//fetching the data from exel file
		String expectedLastName =excelUtility.getDataFromExcel("contact",2,1)+randomNumber;
		//converting string to long
		long longTimeouts =javaUtility.convertStringToLong(timeouts);
		//run time polymorphism
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		WebDriver driver=webDriverUtility.openBrowser(browser);
		//pre-setting for the browser
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(longTimeouts);
		//navigating to application
		webDriverUtility.navigateToApplication(url);
		//loging to application
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginAction(userName, password);
		//create contacts
		CommonPage commonPage=new CommonPage(driver);
		commonPage.clickContactAction();
		ContactsPage contactsPage=new ContactsPage(driver);
		contactsPage.createContact(expectedLastName);
		//fetching actual contact lastname
		String actualLastName =contactsPage.getActualContactLastname();
		//validating contacts lastname
		if(actualLastName.equals(expectedLastName))
			javaUtility.print("Last name is matching--Testcase is passed");
		else
			javaUtility.print("Last name is not matching--Testcase is failed");
		//logout from application
		commonPage.logoutAction(webDriverUtility);
		//closing workbook
		excelUtility.closeExcelWorkbook();
		//closing browser
		webDriverUtility.closeAllBrowser();
	}

}
