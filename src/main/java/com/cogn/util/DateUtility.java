package com.cogn.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public static Date convertFromStrToDate(String dateStr) {
		// 1988-03-29
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (Exception e) {
		}
		return date;
	}

}
