package com.comcast.crm.generic.webdriverutility;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(5000);
	}
	
	public String getSystemDateYYYYDDMM() {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateObj);
		return date;
	}
	
	public String getRequiredDateYYYYDDMM(int days) {
		Date dateObj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		sim.format(dateObj);
	
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String reqDate = sim.format(cal.getTime());
		return reqDate;
	}
}