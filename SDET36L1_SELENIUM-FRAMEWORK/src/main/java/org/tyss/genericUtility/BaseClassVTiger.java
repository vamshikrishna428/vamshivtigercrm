package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;

public class BaseClassVTiger {
	public JavaUtility javaUtility;
	public FileUtility fileUtility;
	public ExcelUtility excelUtility;
	public int randomNumber;
	public WebDriverUtility webDriverUtility;
	private String url;
	private String userName;
	private String password;
	private String timeouts;
	private String browser;
	public long timeoutsLong;
	public WebDriver driver;
	public CommonPage commonpage;
	public LoginPage login;
	@BeforeClass
	public void classSetup() {
		javaUtility=new JavaUtility();
		fileUtility=new FileUtility();
		excelUtility=new ExcelUtility();
		randomNumber =javaUtility.getRandomNumber();
		webDriverUtility=new WebDriverUtility();
		fileUtility.initializePropertyFile(IConstants.VTIGERPROPERTYFILEPATH);
		excelUtility.initializeExcelFile(IConstants.VTIGEREXCELFILEPATH);
		url=fileUtility.getDataFromPropertyfile("url");
		userName=fileUtility.getDataFromPropertyfile("username");
		password=fileUtility.getDataFromPropertyfile("password");
		timeouts=fileUtility.getDataFromPropertyfile("duration");
		browser=fileUtility.getDataFromPropertyfile("browser");
		timeoutsLong = javaUtility.convertStringToLong(timeouts);
		driver= webDriverUtility.openBrowser(browser);
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		
		UtilityObjectClass.setDriver(driver);
		UtilityObjectClass.setJavaUtility(javaUtility);

	}
	@BeforeMethod
	public void methodSetup() {

		login=new LoginPage(driver);
		login.loginAction(userName, password);
		commonpage=new CommonPage(driver);

	}
	@AfterMethod
	public void methodTeardown() {
		commonpage.logoutAction(webDriverUtility);
		
	}
	@AfterClass
	public void classTeardown() {
		excelUtility.closeExcelWorkbook();
		webDriverUtility.closeAllBrowser();

	}

}
