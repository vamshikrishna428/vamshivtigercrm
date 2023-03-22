package org.tyss.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains only database related reusable methods
 * @author 
 *
 */
public class DatabaseUtility {
	private Driver dbDriver;
	private Connection connection;
	private Statement statement;
	ResultSet result;
	/**
	 * This method is used to Establish connection with database
	 * @param url
	 * @param username
	 * @param password
	 */
	public void getConnectionToDatabase(String url,String username,String password) {
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//register the driver to jdbc
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//estrsblish the connection
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to Execute sql query and its returns List<String> result
	 * @param sqlQuery
	 * @param columnIndexOrName
	 * @return
	 */
	public List<String> executeDatabaseQuery(String sqlQuery,String columnIndexOrName) {
		List<String> list=new ArrayList<>();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//execute query
		try {
			ResultSet result = statement.executeQuery(sqlQuery);
			while(result.next()) {
				list.add(result.getString(columnIndexOrName));
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * This method will close thr Database connection
	 */
	public void closeDataBaseConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to insert data into database
	 * @param sqlQuery
	 */
	public void updateDBQuery(String sqlQuery) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sqlQuery);
			System.out.println("Data successfully added to database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean verifyDataInDB(String sqlQuery, String columnIndexOrName,String expectedData){
		List<String>list=executeDatabaseQuery(sqlQuery, columnIndexOrName);
		boolean flag=false;
		for(String data:list) {
			if(data.equalsIgnoreCase(expectedData))
				flag=true;
			break;
		}
		return flag;  
	}
}
