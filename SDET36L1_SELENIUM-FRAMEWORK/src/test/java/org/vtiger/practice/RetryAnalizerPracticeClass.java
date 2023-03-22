package org.vtiger.practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalizerPracticeClass {
	@Test(retryAnalyzer = org.tyss.genericUtility.RetryAnalizerImplimentationClass.class)
	public void display() {
		System.out.println("1");
		Assert.fail();
		System.out.println("2");
		System.out.println("3");
	}

}
