package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ssmmaven7.model.User;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;

public class JsonUtil {
	public static String objectToJsonString(Object object){
		return JSONArray.toJSONString(object);
	}
	
	public static <T> T jsonToJavaBean(String jsonString, Class<T> objType){
		T jb = null;
		jb = JSONObject.parseObject(jsonString, objType);
		Consol.log(jb.toString());
		return jb;
	}
	
	public static <T> List<T> jsonToList(String jsonString, Class<T> objType){
		List<T> list = null;
		list = JSONArray.parseArray(jsonString, objType);
		return list;
	}
	
	public static Map<String, Object> jsonToMap(String jsonString){
		JSONObject jsonObject = JSONObject.parseObject(jsonString.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(jsonObject);
		return map;
	}
}
