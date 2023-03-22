package org.tyss.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains reusable methods related to webdriver and pre-setting for browser
 * @author 
 *
 */
public final class WebDriverUtility {
	private WebDriver driver;
	private WebDriverWait wait;
	/**
	 * This method is used to setup the browser instance;
	 * @param browser
	 */
	public WebDriver openBrowser(String browser) {
		switch (browser) {
		case "chrome":
//			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(options);

//			WebDriverManager.chromedriver().setup();  //method chaining
//			driver=new ChromeDriver();     //abstraction
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			break;

		default:
			System.out.println("You have entered invalid browser key in property file");
			break;
		}
		return driver;
	}
	/**
	 * This method will maxize the browser with browser presettings
	 */
	public void maxizeBrowser() {
		driver.manage().window().maximize();
	}
	/**
	 * This method will hold the execution for a perticular time period to match the selenium speed with application speed
	 * @param longTimeouts
	 */
	public void implicitWait(long longTimeouts) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeouts));
	}
	/**
	 * This will navigate to the application
	 * @param url
	 */
	public void navigateToApplication(String url) {
		driver.get(url);
	}
	/**
	 * This method will mousehover to an element
	 * @param element
	 */
	public void mouseHoverToElement(WebElement element) {
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	/**
	 * This method is used for explicit wait
	 * @param longTimeouts
	 * @param longPollingTime
	 */
	public void initiallizeExplicitWait(long longTimeouts,long longPollingTime) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(longTimeouts));
		wait.pollingEvery(Duration.ofMillis(longPollingTime));
		wait.ignoring(Exception.class);
	}
	/**
	 * This method is used to wait untill visible of perticular webelement
	 * @param element
	 */
	public void waitTillElementIsVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used to wait untill invisible of perticular webelement
	 * @param element
	 */
	public void waitTillElementIsInvisible(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	/**
	 * This method will wait till the element is clickable(Custom Wait)
	 * @param element
	 * @param totalDuration
	 * @param longPollingTime
	 */
	public void waitTillElementIsClickable(WebElement element,int totalDuration,long longPollingTime) {
		int currentTime=0;
		while(currentTime<=totalDuration) {
			try {
				element.click();
				break;
			}
			catch (Exception e) {
				try {
					Thread.sleep(longPollingTime);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				currentTime++;
			}
		}
	}

	/**
	 * This method is used to close the browser
	 */
	public void closeBrowser() {
		driver.close();
	}
	/**
	 * This method is used to close all browser tabs 
	 */
	public void closeAllBrowser(){
		driver.quit();
	}
	/**
	 * This method is used to select dropdown by visible text
	 * @param element
	 * @param text
	 */
	public void selectDropdown(WebElement element,String text) {
		Select select=new Select(element);
		select.selectByVisibleText(text);
	}
	/**
	 * This method is used to select dropdown by value
	 * @param element
	 * @param text
	 */
	public void selectDropdown(String value,WebElement element) {
		Select select=new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This method is used to select dropdown by index
	 * @param element
	 * @param text
	 */
	public void selectDropdown(WebElement element,int index) {
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This method is used to take screenshot of webpage
	 * @param driver
	 * @param currentClassObjReference
	 * @return 
	 */
	public String takeScreenshot(String currentMethodName,JavaUtility javaUtility,WebDriver driver) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./errorshots/"+currentMethodName+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dest.getAbsolutePath();
	}
	/**
	 * This method is used to take screenshot of webelement
	 * @param currentClassObjReference
	 * @param element
	 * @param javaUtility
	 * @return 
	 */
	public void takeElementScreenshot(Object currentClassObjReference,WebElement element,JavaUtility javaUtility) {
		File src = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("./elementScreenshots/"+currentClassObjReference+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method will accept Js/Alert popup
	 */
	public void JsPopupAccept() {
		driver.switchTo().alert().accept();
	}
	/**
	 * This method will dismiss Js/Alert popup
	 */
	public void JsPopupDismiss() {
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method will send data to Js/Alert popup
	 * @param data
	 */
	public void JsPopupSendData(String data) {
		driver.switchTo().alert().sendKeys(data);
	}
	/**
	 * This method will fetch text from Js/Alert popup
	 */
	public void JsPopupGetText() {
		driver.switchTo().alert().getText();
	}
	/**
	 * This method will switch the window base on startegy like url or title
	 * @param startegy
	 * @param partialText
	 */
	public void switchToWindow(String startegy,String partialText) {
		Set<String> allWh = driver.getWindowHandles();
		for(String wh:allWh) {
			driver.switchTo().window(wh);
			if(startegy.equalsIgnoreCase("url")) {
				if(driver.getCurrentUrl().contains(partialText))
					break;
			}
			else if(startegy.equalsIgnoreCase("title")) {
				if(driver.getTitle().contains(partialText))
					break;
			}

		}
	}
	/**
	 * This method will switch the window using Iterator base on startegy like url or title
	 * @param startegy
	 * @param partialText
	 */
	public void switchToWindowUsingIterator(String startegy,String partialText) {
		Iterator<String> allWh = driver.getWindowHandles().iterator();
		while(allWh.hasNext()) {
			String wh = allWh.next();
			driver.switchTo().window(wh);
			if(startegy.equalsIgnoreCase("url")) {
				if(driver.getCurrentUrl().contains(partialText))
					break;
			}
			else if(startegy.equalsIgnoreCase("title")) {
				if(driver.getTitle().contains(partialText))
					break;
			}

		}
	}
	/**
	 * This method will switch the control into Frame
	 * @param element
	 */
	public void switchFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * This method will switch back the control to Default Frame
	 * @param element
	 */
	public void switchBackDefaultFrame() {
		driver.switchTo().defaultContent();
	}
	/**
	 * This method will switch back the control to Parent Frame
	 * @param element
	 */
	public void switchBackToParentFrame() {
		driver.switchTo().parentFrame();
	}
}
