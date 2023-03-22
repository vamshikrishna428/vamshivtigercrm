package org.vtiger.practice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter1 {

	public static void main(String[] args) {
      String s="04-07-2022"; //dd-MM-yyyy
      DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
      LocalDate month = LocalDate.parse(s, dtf);
      System.out.println(month.getMonth());
	}

}
