package org.vtiger.campaigns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.tyss.genericUtility.ExcelUtility;
import org.tyss.genericUtility.FileUtility;
import org.tyss.genericUtility.IConstants;
import org.tyss.genericUtility.JavaUtility;
import org.tyss.genericUtility.WebDriverUtility;

public class CreateCampaignWithProductTest {

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
		String expectedProductName=excelUtility.getDataFromExcel("campaign", 4, 1)+randomNumber;
		String expectedCampaignName=excelUtility.getDataFromExcel("campaign", 4, 2)+randomNumber;
		long timeoutsLong =javaUtility.convertStringToLong(timeouts);
		WebDriver driver=webDriverUtility.openBrowser(browser);  
		webDriverUtility.navigateToApplication(url);
		webDriverUtility.maxizeBrowser();
		webDriverUtility.implicitWait(timeoutsLong);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[.='Products']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		webDriverUtility.initiallizeExplicitWait(timeoutsLong,IConstants.LONGPOLLINGTIME);
		webDriverUtility.waitTillElementIsVisible(driver.findElement(By.xpath("//span[@class='lvtHeaderText']")));
		WebElement moreLink = driver.findElement(By.xpath("//a[.='More']"));
		webDriverUtility.mouseHoverToElement(moreLink);
		driver.findElement(By.xpath("//a[.='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//input[@name='product_name']/following-sibling::img")).click();
		webDriverUtility.switchToWindow("url", "Products");
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[.='"+expectedProductName+"']")).click();
		webDriverUtility.switchToWindowUsingIterator("url", "Campaigns");
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		String actualCampaignName = driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText().trim();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product']")).getText().trim();
		if(actualCampaignName.equals(expectedCampaignName) && actualProductName.equals(expectedProductName)) {
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
