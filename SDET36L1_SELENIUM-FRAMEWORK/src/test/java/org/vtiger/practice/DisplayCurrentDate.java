package org.vtiger.practice;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayCurrentDate {

	public static void main(String[] args) {
       Date date=new Date();
       System.out.println(date);
       SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
       String currentDate = sdf.format(date);
       System.out.println(currentDate);
	}

}
