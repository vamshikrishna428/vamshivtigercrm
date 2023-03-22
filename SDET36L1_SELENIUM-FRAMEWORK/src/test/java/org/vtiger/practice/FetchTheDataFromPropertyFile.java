package org.vtiger.practice;

import java.io.FileInputStream;
import java.util.Properties;

public class FetchTheDataFromPropertyFile {

	public static void main(String[] args) {
		try {
 FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties");
 Properties p=new Properties();
 p.load(fis);
 String url = p.getProperty("url");
 System.out.println(url);
		}
		catch (Exception e) {
			
		}
	}

}
