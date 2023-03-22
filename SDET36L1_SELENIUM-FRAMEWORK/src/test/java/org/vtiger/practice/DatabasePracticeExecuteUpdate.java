package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabasePracticeExecuteUpdate {

	public static void main(String[] args) {
		try {
      Driver driver=new Driver();
      DriverManager.registerDriver(driver);
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tyss","root","root");
      Statement statement = connection.createStatement();
      int result = statement.executeUpdate("insert into sdet36(empName,empId,phoneNumber) values('asdRK',1005,'897456231');");
      if(result==1)
    	  System.out.println("Data inserted into Database");
      else
    	  System.out.println("Data is not inserted");
      connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

}
