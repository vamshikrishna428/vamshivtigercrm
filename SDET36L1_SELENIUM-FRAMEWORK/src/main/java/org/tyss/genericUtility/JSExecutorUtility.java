package org.tyss.genericUtility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class contains only JS reusable methods 
 * @author 
 *
 */
public final class JSExecutorUtility {
	private JavascriptExecutor js;
	/**
	 * This method will initillize and typecast Javascript Executor
	 * @param driver
	 */
	public void initiallizeJSExecutor(WebDriver driver) {
		js=(JavascriptExecutor)driver;
	}
	/**
	 * This method will Navigate to perticular url
	 * @param url
	 */
	public void navigateToApplication(String url) {
		js.executeScript("window.location=arguments[0]",url);
	}
	/**
	 * This method will enter the data to perticular textfield
	 * @param element
	 * @param data
	 */
	public void enterData(WebElement element,String data) {
	js.executeScript("arguments[0].value=arguments[1]",element,data);
	}
	/**
	 * This method will click on the perticular WebElement
	 * @param element
	 */
	public void clickOnElement(WebElement element) {
		js.executeScript("arguments[0].click()", element);
	}
	/**
	 * This method will scroll the webpage to End or to Top based on Startegy("-"-up &"+"-down)
	 * @param startegy
	 */
	public void scrollTillEnd(String startegy) {
		String sign=startegy.equalsIgnoreCase("up")?"-":"+";
		js.executeScript("window.scrollBy(0,"+sign+"document.body.scrollHeight)");
	}
	/**
	 * This method will scroll the webpage till someposition,based on startegy it will goes up or down("-"-up &"+"-down)
	 * @param startegy
	 * @param y_position
	 */
	public void scrollTillSomePosition(String startegy,int y_position) {
		String sign=startegy.equalsIgnoreCase("up")?"-":"+";
		js.executeScript("window.scrollBy(0,"+sign+"arguments[0])",y_position);
	}
	/**
	 * This method will scroll till element is visible
	 * @param element
	 */
	public void scrollTillElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	/**
	 * This method will highlight the element
	 * @param element
	 */
	public void highLightElement(WebElement element) {
		js.executeScript("arguments[0].setAttribute('style','border:5px solid red;')", element);
	}
}
