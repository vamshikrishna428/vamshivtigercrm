package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import com.mysql.cj.jdbc.Driver;

public class Rmgy2{

	public static void main(String[] args) {
		Random ran=new Random();
		int randomNumber = ran.nextInt(1000);
		String projectName = "TY_PROJ"+randomNumber;
		try {
		Driver driver2=new Driver();
		DriverManager.registerDriver(driver2);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("insert into project(project_id, created_by,created_on,project_name, status,team_size)values('TY_PROJ_0"+randomNumber+"','jk','22/06/2022','"+projectName+"','created',0);");
		if(result==1)
			System.out.println("project details inserted");
		else
			System.out.println("project details is not inserted");
		connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}