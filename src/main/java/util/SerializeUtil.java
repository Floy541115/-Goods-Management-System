package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;

public class SerializeUtil {
	public static byte[] objectSerialization(Object object){
		String str = "";
		byte[] b = null ;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null ;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(object);
			oos.close();
			bos.close();
			b = bos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				if(oos != null) oos.close();
				if(bos != null) bos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		/*oos.writeObject(object);
			//oos是由bos实例化的，所以object是写入bos
			str = bos.toString("ISO-8859-1");
			str = new String(str.getBytes(), "utf-8");
		 */
		return b;
	}
	
	public static Object objectDeserialization(byte[] in){
		Object object = null;
		ByteArrayInputStream bis = null ;
		ObjectInputStream ois = null ;
		try {
			if(in != null){
				bis = new ByteArrayInputStream(in);
				ois = new ObjectInputStream(bis);
				object = ois.readObject();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				ois.close();
				bis.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
//		try {
//			String strCoder = URLDecoder.decode(str.toString(), "UTF-8");
//			Consol.log("-------------------------"+str.toString());
//			ByteArrayInputStream bis = new ByteArrayInputStream(strCoder
//					.getBytes("ISO-8859-1"));
////			 String encodeToString = Base64.encodeToString(byteArray, Base64.DEFAULT);
//			ObjectInputStream ois = new ObjectInputStream(bis);
//			object = ois.readObject();
//			bis.close();
//			ois.close();
		
		return  object;
	}
}
