package org.vtiger.organizations;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tyss.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOrganizationTest {

	public static void main(String[] args) {
		int randomNumber = new Random().nextInt(1000);
		WebDriver driver=null;
		try {
			FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
			Properties p=new Properties();
			p.load(fis);
			FileInputStream fisExel=new FileInputStream("./src/test/resources/testdata.xlsx");
			Workbook workbook = WorkbookFactory.create(fisExel);
			String url=p.getProperty("url");
			String userName=p.getProperty("username").trim();
			String password=p.getProperty("password").trim();
			String timeouts=p.getProperty("duration").trim();
			String browser=p.getProperty("browser").trim();
			long timeoutsLong = Long.parseLong(timeouts);
			String expectedOrganizationName =workbook.getSheet("organization").getRow(2).getCell(1).getStringCellValue()+randomNumber;
			if(browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver(); 
			}
			else if (browser.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else if (browser.equals("ie")) {
				WebDriverManager.iedriver();
				driver=new InternetExplorerDriver();
			}
			else {
				System.out.println("You have entered invalid browser key in property file");
			}    
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutsLong));
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeoutsLong));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		String[] arrPageCount = driver.findElement(By.xpath("//span[@class='small' and @name='Accounts_listViewCountContainerName']")).getText().split(" ");
		String pageCount = arrPageCount[arrPageCount.length-1];
		driver.findElement(By.xpath("//input[@class='small' ]")).clear();
		driver.findElement(By.xpath("//input[@class='small' ]")).sendKeys(pageCount,Keys.ENTER);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@id='status']"))));
		List<WebElement> listOfOrgNames = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[3]/a"));
		System.out.println("org stored");
		System.out.println(listOfOrgNames.size());
		for(int i=0;i<listOfOrgNames.size();i++) {
			System.out.println("entered into loop");
			String orgName = listOfOrgNames.get(i).getText();
			System.out.println(orgName);
			System.out.println(expectedOrganizationName);
			if(orgName.equals(expectedOrganizationName)) {
				System.out.println(orgName);
				driver.findElement(By.xpath("//table[@class='lvt small']/tbody/tr["+(i+3)+"]/td[1]/input")).click();
				break;
			}
		}
		driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();
	
		//driver.quit();
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
