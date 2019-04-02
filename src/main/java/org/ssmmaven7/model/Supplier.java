package org.ssmmaven7.model;

import java.io.Serializable;
import java.util.Date;

public class Supplier implements Serializable{
	
	private Integer supplierId;
	private String supplierName;
	private String address;
	private Date startTime;
	private String personInCharge;  //负责人
	private String phone;
	private String category;
	
	public Supplier(){}
	/**
	 * 
	 * @param supplierId        供应商编号
	 * @param supplierName      供应商名
	 * @param address			联系地址
	 * @param startTime			开始合作时间
	 * @param personInCharge	负责人
	 * @param phone				负责人联系方式
	 * @param category			供应商供应的商品类型
	 */
	public Supplier(Integer supplierId, String supplierName, String address, Date startTime
			,String personInCharge,String phone,String category){
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.address = address;
		this.startTime = startTime;
		this.personInCharge = personInCharge;
		this.phone = phone;
		this.category = category;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getPersonInCharge() {
		return personInCharge;
	}
	public void setPersonInCharge(String personInCharge) {
		this.personInCharge = personInCharge;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
		
}
