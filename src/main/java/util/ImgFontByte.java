package util;

import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ImgFontByte {
	//加载字符并处理字符及其样式
	public Font getFont(int fontHeight){
		Font baseFont ;
		try {
			baseFont = Font.createFont(Font.TRUETYPE_FONT
					, new ByteArrayInputStream(hex2byte(getFontByteStr())));
			return baseFont.deriveFont(Font.PLAIN, fontHeight);   //    通过复制此 baseFont 对象并应用新样式和变换，创建一个新 Font 对象。
		} catch (Exception e) {
			// TODO: handle exception
			return new Font("Arial", Font.PLAIN, fontHeight);
		}
	}
	public byte[] hex2byte(String str){
		if(str == null)
			return null;
		str = str.trim();  //忽略字符串前后空格copy字符串
		int len = str.length();
		if(len == 0 || len%2 == 1)
			return null;
		byte[] b = new byte[len/2];
		try {
			for(int i=0; i<str.length(); i+=2){
				b[i/2] = (byte)Integer
						.decode("0x" + str.substring(i,i+2)).intValue();
			}
			return b;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public String getFontByteStr() throws IOException{
		return readTxt();
	}
	
	public String readTxt()throws IOException{    
		StringBuffer strb = new StringBuffer("");
//		File file = new File("${pageContext.request.contextPath}/0x-word.txt");  //找不到路径
		File file = new File("E:\\Project\\javaee\\0x-word.txt");
		FileReader fr = new FileReader(file);
		int lg = 0;
		while((lg = fr.read()) != -1 ){
			strb.append((char)lg);
		}
 		fr.close();
 		return strb.toString();
	}
}
