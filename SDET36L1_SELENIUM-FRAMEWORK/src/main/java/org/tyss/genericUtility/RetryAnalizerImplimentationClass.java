package org.tyss.genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalizerImplimentationClass implements IRetryAnalyzer{
	int count=0;
	int retryLimit=3;
	@Override
	public boolean retry(ITestResult result) {
		while(count<retryLimit) {
			count++;
			return true;
		}
		return false;
	}

}
