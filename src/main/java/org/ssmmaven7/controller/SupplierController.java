package org.ssmmaven7.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ssmmaven7.model.Supplier;
import org.ssmmaven7.service.ISupplierService;

import util.JsonUtil;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private HttpServletRequest request;
	@RequestMapping(value = "/insertSupplier.action", produces = "text/plain; charset=UTF-8")
	public String insertSupplier(String supplierName, String startTime, String personInCharge
			, String phone, String address,String category){
		Supplier supplier = null;
		String requestPage = request.getParameter("request");
		try {
			supplier = supplierService.findBySupplierName(supplierName);
			if(supplier == null){
				supplier = new Supplier();
				supplier.setAddress(address);
				SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
				Date startDate = format.parse(startTime);
				supplier.setStartTime(startDate);
				supplier.setPersonInCharge(personInCharge);
				supplier.setSupplierName(supplierName);
				supplier.setPhone(phone);
				supplier.setCategory(category);
				supplierService.insertSupplier(supplier);
			}else {
				if(requestPage != null && "InAddGood".equals(requestPage)) return "good/addGoods";
				else return "supplier/addSupplier";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(requestPage != null && "InAddGood".equals(requestPage)) return "good/addGoods";
			else return "supplier/addSupplier";
		}
		if(requestPage != null && "InAddGood".equals(requestPage)) return "good/addGoods";
		else return "supplierViews/supplierList";
	}
	
	@RequestMapping(value = "/allSupplier.action", produces = "text/plain; charset=UTF-8")
	public String allSupplier(){
		/** layUi动态表格数据结构
		 * {code:xx
		 * msg:xx
		 * count(数据量):xx
		 * 表格数据 data：mapList(map的集合):xx}
		 * 最外层为map 
		 */
		Map<String, Object> rsMap = new HashMap<String,Object>();
		rsMap.put("code", 0);
		rsMap.put("msg", "");
		List<Supplier> suppliers = new ArrayList<Supplier>();
		try {
			suppliers = supplierService.findAllSupplier();
			rsMap.put("count", suppliers.size());
			rsMap.put("data", suppliers);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String rsJsonString = JsonUtil.objectToJsonString(rsMap).replaceAll("\\\\", "");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(URLDecoder.decode(rsJsonString, "utf-8"));
//			out.flush();如果out.write(....)异常就没办法执行flush和close了
//			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return "supplierViews/supplierList";
	}
	
	@RequestMapping(value = "/deleteBySupplierId.action", produces = "text/plain; charset=UTF-8")
	public String deleteBySupplierId(String supplierId){
		try {
			supplierService.deleteBySupplierId(Integer.parseInt(supplierId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "supplierViews/supplierList";
	}
	
	@RequestMapping(value = "/findBySupplierName.action", produces="text/plain; charset=UTF-8")
	public String findBySupplierName(String supplierName)throws Exception{
		String supplierNameString = new String(supplierName.getBytes("iso8859-1"),"UTF-8");
		Supplier supplier = null;
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("code", 0);
		rsMap.put("msg", "");
		rsMap.put("count", 1);
		List<Supplier> splMapList = new ArrayList<>();
		try {
			supplier = supplierService.findBySupplierName(supplierNameString);
			if(supplier == null){
				supplier = new Supplier();
			}
			splMapList.add(supplier);
			rsMap.put("data", splMapList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
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
		return "goodViews/addGoods";
	}
	
	@RequestMapping(value = "/findSupplierByCategory.action", produces = "text/plain; charset=UTF-8")
	public String findSupplierByCategory(String category){
		List<Supplier> suppliers = new ArrayList<>();
		try {
			suppliers = supplierService.findSupplierByCategory(category);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String jsonString = JsonUtil.objectToJsonString(suppliers);
			out.write( jsonString.replaceAll("\\\\", ""));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
		return "goodViews/addGoods";
	}
	@RequestMapping(value="/findBySupplierId", produces = "text/plain;charset=UTF-8")
	public String findBySupplierId(String supplierId)throws Exception{
		String sId = new String(supplierId.getBytes("iso8859-1"),"UTF-8");
		Supplier supplier = null;
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("code", 0);
		rsMap.put("msg", "");
		rsMap.put("count", 1);
		List<Supplier> splMapList = new ArrayList<>();
		try {
			supplier = supplierService.findBySupplierId(Integer.parseInt(sId));
			if(supplier == null){
				supplier = new Supplier();
			}
			splMapList.add(supplier);
			rsMap.put("data", splMapList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
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
		return "goodViews/addGoods";
	}
}
