package util;

public class Decode {
	public static String toUTF8(String str)throws Exception{
		String strutf8 = new String(str.getBytes("iso8859-1"),"UTF-8");
		return strutf8;
	}
}
