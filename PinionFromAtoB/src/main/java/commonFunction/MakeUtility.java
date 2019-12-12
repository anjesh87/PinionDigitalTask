package main.java.commonFunction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MakeUtility {
	static String strFrameworkLocation =System.getProperty("user.dir");

	public static String getTimeStamp()
	{
		Date nd=new Date();
		DateFormat formatters = new SimpleDateFormat("dd_MM_yyyyhhmmss");
		String strdate  = formatters.format(nd);
		return strdate;
	}

}


