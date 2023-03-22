package org.tyss.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementationClass implements ITestListener {
	private ExtentSparkReporter spark;
	private ExtentReports report;
	private ExtentTest test;
	public static ExtentTest testlog;
	//@BeforeMethod  
	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		test.assignAuthor("Ambrit Patra");
		test.assignCategory("Smoke");
		testlog=test;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName()+" is Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName()+" is Failed");
		test.fail(result.getThrowable());
		String pathOfScreenshot=new WebDriverUtility().takeScreenshot(result.getMethod().getMethodName(),UtilityObjectClass.getJavaUtility(),UtilityObjectClass.getDriver());
		test.addScreenCaptureFromPath(pathOfScreenshot);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName()+" is Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}
	//@BeforeTest
	@Override
	public void onStart(ITestContext context) {
		spark=new ExtentSparkReporter("./extentreport-output/emailable-extentreport.html");
		spark.config().setDocumentTitle("Document Title");
		spark.config().setReportName("ReportName");
		spark.config().setTheme(Theme.DARK);
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows 11");
		report.setSystemInfo("browser", "chrome");
		report.setSystemInfo("Browser Version", "103.11.234");


	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
