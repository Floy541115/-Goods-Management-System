package org.ssmmaven7.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.dao.GoodDao;
import org.ssmmaven7.model.Good;
import org.ssmmaven7.service.IGoodService;

import util.Decode;
import util.JsonUtil;

@Controller
@RequestMapping("/good")
public class GoodController {
	@Autowired
	private IGoodService goodService;
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping(value = "/allGoods.action", produces = "text/plain; charset=UTF-8")
	public String allGoods(){
		/** layUi动态表格数据结构
		 * {code:xx
		 * msg:xx
		 * count(数据量):xx
		 * 表格数据 data：mapList(map的集合):xx}
		 * 最外层为map 
		 */
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("code", 0);
		rsMap.put("msg", "");
		List<Good> goods = new ArrayList<Good>();
		try {
			goods = goodService.findAllGood();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		rsMap.put("count", goods.size());
		rsMap.put("data", goods);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JsonUtil.objectToJsonString(rsMap).replaceAll("\\\\", ""));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return "goodViews/allGoods";
	}
	
	@RequestMapping(value="/findGoodByCategory.action", produces = "text/plain; charset=UTF-8")
	public String findGoodByCategory(String category){
		//点击页面某类再查询，默认打开第一个，可以不用返回值吗，如果返回一次加载一次第一个太浪费
//		返回值resultMap中supplierName保存在List<Good>的supplier对象中，所以resultMap对应的还是Good，supplierName并没有破坏Good结构
		String cg = "" ;
		try {
			cg = Decode.toUTF8(category);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if("".equals(cg)) cg = "玩具";
		List<Good> goodList = null;
		try {
			goodList = goodService.findGoodByCategory(cg);
		} catch (Exception e) {
			// TODO: handle exception
			goodList = new ArrayList<>();
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null ;
		try {
			out = response.getWriter();
			out.write(dataFormat(goodList));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return "goodViews/allGoods";
	}
    
	@RequestMapping(value="findGoodByName.action", produces="text/plain; charset=UTF-8")
	public String findGoodByName(String goodName){
		List<Good> goodList = null;
		try {
			goodList = goodService.findGoodByName(goodName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			goodList = new ArrayList<>();
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null ;
		try {
			out = response.getWriter();
			out.write(dataFormat(goodList));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return "goodViews/allGoods";
	}
//	@RequiresPermissions("good:add")
	@RequestMapping(value = "/addGood.action", produces = "text/plain; charset=UTF-8")
	public String addGood(String code, String goodName, String price, String category, String quantity
			, String supplier, String specification, String shelve){
		Good good = new Good();
		try {
			good.setCategory(category);
			good.setCode(code);
			good.setGoodName(goodName);
//			BigDecimal decimal = new BigDecimal(price);
			good.setPrice(Double.parseDouble(price));
			good.setQuantity(Integer.parseInt(quantity));
			good.setSupplierId(Integer.parseInt(supplier));
			good.setSpecification(specification);
			good.setShelve(shelve);
			goodService.insertGood(good);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "goodViews/allGoods";
	}
//	@RequiresPermissions("deleteGood")
	@RequestMapping(value="/deleteGood", produces = "text/plain; charset=UTF-8")
	public String deleteGoodById(String goodId){
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			goodService.deleteByGoodId(Integer.parseInt(goodId));
			out.write("success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			out.write("fail");
		}finally{
			out.flush();
			out.close();
		}
		return "goodViews/allGoods";
	}
	/*
	 * @param updateType 出库或入库 
	 */
	@RequestMapping(value = "/updateGood.action", produces = "text/plain; charset=UTF-8")
	public String updateGood(String updateType,String goodId, String quantity){
		int updateNumber = 0;
		if("input".equals(updateType)){
			updateNumber = Integer.parseInt(quantity);
		}else if("output".equals(updateType)){
			updateNumber = Integer.parseInt(quantity)*(-1);
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			goodService.updateGood(updateNumber, Integer.parseInt(goodId));
			out.write("success");
		} catch (Exception e) {
			// TODO: handle exception
			out.write("fail");
			e.printStackTrace();
		}
		return "goodViews/allGoods";
	}
	
	public String dataFormat(List<Good> goodList){
		Map<String,Object> rsMap = new HashMap<>();
		rsMap.put("code", 0);
		rsMap.put("msg", "");
		Map<String, Object> goodMap ;
		List<Map<String, Object>> goodMapList = new ArrayList<>();
		try {
			
			for(Good good : goodList){
				goodMap = new HashMap<>();
				goodMap.put("supplierName", good.getSupplier().getSupplierName()); 
				goodMap.put("goodId", good.getGoodId()+"");
				goodMap.put("goodName", good.getGoodName());
				goodMap.put("price", good.getPrice());
				goodMap.put("category", good.getCategory());
				goodMap.put("quantity", good.getQuantity());
				goodMap.put("specification", good.getSpecification());
				goodMap.put("shelve", good.getShelve());
				goodMapList.add(goodMap);
			}
			rsMap.put("count", goodMapList.size());
			rsMap.put("data", goodMapList);
		} catch (Exception e) {
			// TODO: handle exception
			rsMap.put("count", goodMapList.size());
			rsMap.put("data", goodMapList);
			e.printStackTrace();
		}
		return JsonUtil.objectToJsonString(rsMap).replaceAll("\\\\", "");
	}
}
