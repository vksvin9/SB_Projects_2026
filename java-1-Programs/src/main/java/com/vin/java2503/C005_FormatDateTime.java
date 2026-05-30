package com.vin.java2503;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class C005_FormatDateTime {
//  LocalDate			: Represents a date (year, month, day (yyyy-MM-dd))
//	LocalTime			: Represents a time (hour, minute, second and nanoseconds (HH-mm-ss-ns))
//	LocalDateTime		: Represents both a date and a time (yyyy-MM-dd-HH-mm-ss-ns)
//	DateTimeFormatter	: Formatter for displaying and parsing date-time objects
//	UTC (Coordinated Universal Time) and GMT (Greenwich Mean Time) are same, 
//	but technically, GMT is a time zone, while UTC is a time standard.
	public static void main(String[] args) {
		LocalDate localDate = LocalDate.now();
		System.out.println("LocalDate is " + localDate);
		
		LocalTime localTime = LocalTime.now();
		System.out.println("LocalTime is " + localTime);
		
		LocalDateTime localDateTime = LocalDateTime.now();
	    System.out.println("Before formatting: " + localDateTime);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = localDateTime.format(myFormatObj);
	    System.out.println("After formatting: " + formattedDate);
	    
		// java.util.Date
		Date date = new Date();
		System.out.println("Current date is " + date);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		String format = simpleDateFormat.format(date);
		System.out.println("Formate date :- " + format);
		
		Instant now = Instant.now();
		ZonedDateTime zdt = ZonedDateTime.ofInstant(now, ZoneId.systemDefault());
		System.out.println(" Local : " + zdt);

		Instant instant = Instant.now();
		System.out.println(" GMT : " + instant.toString());
	}
}
