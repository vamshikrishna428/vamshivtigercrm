package org.vtiger.practice;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.tyss.genericUtility.JavaUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSExecutorPractice {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		JavaUtility javaUtility=new JavaUtility();
		JSExecutorPractice currentclass=new JSExecutorPractice();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String url = "http://localhost:8888";
		js.executeScript("window.location=arguments[0]",url);
		WebElement userNameTextBox = driver.findElement(By.xpath("//input[@name='user_name']"));
		WebElement passwordTextBox = driver.findElement(By.xpath("//input[@name='user_password']"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='submitButton']"));
		js.executeScript("arguments[0].value=arguments[1]",userNameTextBox,"admin");
		js.executeScript("arguments[0].value=arguments[1]", passwordTextBox,"admin");
		js.executeScript("arguments[0].click()", loginButton);
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");    //scroll to buttom of the webpage
		//js.executeScript("window.scrollBy(0,300)");
		//WebElement scrollTillElement = driver.findElement(By.xpath("(//div[@class='componentName'])[11]"));
		//js.executeScript("arguments[0].scrollIntoView()",scrollTillElement);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./errorshots/"+currentclass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}

}
