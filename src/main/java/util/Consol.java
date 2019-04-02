package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consol {
	
	public static void log(String msg){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("ssm-maven4 "+simpleDateFormat.format(new Date())+" : "+msg);
	}
	
	public static void logRunningTime(String fun, long start, long end){
		System.out.println(fun+" running in "+(end-start)+" ms");
	}

}
