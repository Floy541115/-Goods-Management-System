package org.ssmmaven7.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.service.IRedisService;

import util.Consol;
import util.JsonUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private IRedisService redisService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	
	private static final String KEY = "category";
	

	@RequestMapping(value="/addCategory.action")
	public String addCategory(String categoryName)throws Exception{
//		String categoryName = request.getParameter("categoryName");
		@SuppressWarnings("unchecked")
		Map<String, Object> map = redisService.hgetAll(KEY); 
		if(map == null){
			map = new HashMap<>();
		}
		Map<String, Object> subMap = new HashMap<>();
		String category = URLDecoder.decode(categoryName, "utf-8");
		redisService.hset(KEY, category, subMap.toString());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(KEY, categoryName);
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return "good/category/category";     //views已经配置为默认前缀
	}
	@RequestMapping(value="/addSubCategory.action")
	public String addSubCategory(String categoryName, String subCategoryName)throws Exception{
		String subString = redisService.hget(KEY, categoryName);
		Map<String, Object> map ;
		if(subString.equals("{}")){
			map = new HashMap<>();
		}else {
			try {
				map = JsonUtil.jsonToMap(subString);
			} catch (Exception e) {
				// TODO: handle exception
				map = new HashMap<>();
				e.printStackTrace();
			}
		}
		
		map.put(map.size()+"", subCategoryName);
		try {
			redisService.hset(KEY, URLDecoder.decode(categoryName, "utf-8")
					, URLDecoder.decode(JsonUtil.objectToJsonString(map),"utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(KEY, URLDecoder.decode(categoryName, "utf-8"));
		jsonObject.put("subCategoryName", URLDecoder.decode(subCategoryName, "utf-8"));
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return "good/category/category";	
	}
	@RequestMapping(value="/allCategory.action")
	public String allCategory()throws Exception{
		Map map = redisService.hgetAll(KEY);
//		for(Map.Entry<String, Object>: map.entrySet()){
		Map<String, Object> rsMap = new HashMap<>();
		HashMap<String, Object> valueMap;
		List<String> valueList;
		
		Set<String> strSet = redisService.hkeys(KEY);
		List<String> fieldList = new ArrayList<>();
		for(String str : strSet){
			fieldList.add(str);
			valueMap = new HashMap<>();
			valueList = new ArrayList<>();
			try {
				valueMap = (HashMap<String, Object>) JsonUtil.jsonToMap(redisService.hget(KEY, str));
				for(Map.Entry<String, Object> entry : valueMap.entrySet()){
					valueList.add(entry.getValue().toString());
				}
				//https://blog.csdn.net/zcl1199/article/details/51372691
			} catch (Exception e) {
				// TODO: handle exception
				valueMap = new HashMap<String,Object>();
				Consol.log(KEY+"-"+str+" 对应值为空"); 
			}
			rsMap.put(str, valueList);
		}
		rsMap.put("field",fieldList);
		JSONObject jsonObject = new JSONObject(rsMap);
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		out.write(jsonObject.toString());
		out.flush();
		out.close();
		return "goodViews/category/category";  //不会重新加载一次页面
	}
}
