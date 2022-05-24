package com.vKyc.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomMethod {
		public static String randomStringCustomerName() {
			String customerName = RandomStringUtils.randomAlphabetic(4).toUpperCase();
			try {

				File output = new File("customerName.txt");
				FileWriter writer = new FileWriter(output);

				writer.write(customerName);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		 return (customerName);
		}
		public static String randomMobileNumer() {
			String generateRandomNumber = RandomStringUtils.randomNumeric(10);
			String regex = "^0+(?!$)";
			generateRandomNumber = generateRandomNumber.replaceAll(regex, "1");
			try {

				File output = new File("RandomMobNumber.txt");
				FileWriter writer = new FileWriter(output);

				writer.write(generateRandomNumber);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (generateRandomNumber);
		}
		public static String randomString() {
			String generateString = RandomStringUtils.randomAlphabetic(4);
			try {

				File output = new File("randomString.txt");
				FileWriter writer = new FileWriter(output);

				writer.write(generateString);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (generateString);
		}
		
		public static String randomApplicantId() {
			String generateApplicantId = RandomStringUtils.randomNumeric(4);
			String regex = "^0+(?!$)";
			generateApplicantId = generateApplicantId.replaceAll(regex, "1");
			try {

				File output = new File("applicantId.txt");
				FileWriter writer = new FileWriter(output);

				writer.write(generateApplicantId);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return (generateApplicantId);
		}
		public static String getScheduleTime() {
			 //create Calendar instance
			  Calendar now = Calendar.getInstance();
			  DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			  String currentTime=dateFormat.format(now.getTime());
			  //add minutes to current date using Calendar.add method
			  now.add(Calendar.SECOND,80);
			  // get time after adding 80 seconds to current time
			  String scheduleTime=dateFormat.format(now.getTime());
			  return scheduleTime;
		  }
		public static String getPresentDate() {
			 //create Calendar instance
			  Calendar now = Calendar.getInstance();
			  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			  String presentDate=dateFormat.format(now.getTime());
			  return presentDate;
		  }
}
