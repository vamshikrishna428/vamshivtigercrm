package org.vtiger.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	
	
	@Test(dataProvider="data")
	public void dataProviderTest(String un,int pw) {
		System.out.println(un + "  pw");
	}
	
	@DataProvider(name="data")
	public Object[][] getData() {
		Object [][] objArr=new Object[3][2];
		objArr[0][0]="abc";
		objArr[0][1]=123;
		objArr[1][0]="efg";
		objArr[1][1]=456;
		objArr[2][0]="pqr";
		objArr[2][1]=789;
		return objArr;
	}
	

}
