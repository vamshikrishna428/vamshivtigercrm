package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RmgYantraTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Random random=new Random();
		int randomNumber = random.nextInt(10000);
		String projectName="sdet36"+randomNumber;
		driver.get("http://localhost:8084/");
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		driver.findElement(By.xpath("//a[.='Projects']")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("ap");
		WebElement projectStatus = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select select=new Select(projectStatus);
		select.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		driver.quit();
		try {
			Driver driver2=new Driver();
			DriverManager.registerDriver(driver2);
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from project;");
			int count=0;
			while(result.next()) {
				System.out.println(result.getString(1)+"    "+result.getString(2)+"   "+result.getString(3));
				if(result.getString("project_name").equals(projectName)) {
					count++;
					System.out.println("project is created");
					break;
				}
			}
			if(count==0)
				System.out.println("project is not created");

			connection.close();

		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

}
