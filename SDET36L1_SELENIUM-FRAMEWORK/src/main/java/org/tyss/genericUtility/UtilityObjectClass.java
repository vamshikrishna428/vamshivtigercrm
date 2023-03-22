package org.tyss.genericUtility;

import org.openqa.selenium.WebDriver;

public class UtilityObjectClass {
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	private static ThreadLocal<JavaUtility> javaUtility=new ThreadLocal<>();

	public static JavaUtility getJavaUtility() {
		return javaUtility.get();
	}

	public static void setJavaUtility(JavaUtility actjavaUtility) {
		javaUtility.set(actjavaUtility);
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver actualdriver) {
		driver.set(actualdriver);
	}

}
