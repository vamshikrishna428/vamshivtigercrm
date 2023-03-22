package org.vtiger.campaigns;

import org.openqa.selenium.WebDriver;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;
import org.vtiger.objectRepository.CampaignInformationPage;
import org.vtiger.objectRepository.CampaignsPage;
import org.vtiger.objectRepository.CommonPage;
import org.vtiger.objectRepository.LoginPage;

public class CreateCampaignTest {

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
		String expectedCampaignName =excelUtility.getDataFromExcel("campaign", 2, 1)+randomNumber;
		long timeoutsLong = javaUtility.convertStringToLong(timeouts);
		WebDriver driver= webDriverUtility.openBrowser(browser);
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		new LoginPage(driver).loginAction(userName, password);
		CommonPage commonPage=new CommonPage(driver);
		commonPage.mousehoverToMoreLinkAction(webDriverUtility);
		commonPage.clickCampaignAction();
		CampaignsPage campaignPage=new CampaignsPage(driver);
		campaignPage.createNewCampaignAction(expectedCampaignName);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		String actualCampaignName =campaignInformationPage.getCampaignName();
		if(actualCampaignName.equals(expectedCampaignName))
			javaUtility.print("Campaign name is matching-->Testcase Passed");
		else
			javaUtility.print("Campaign name is matching-->Testcase Failed");
		commonPage.logoutAction(webDriverUtility);
		webDriverUtility.closeAllBrowser();
	}		
}


  
