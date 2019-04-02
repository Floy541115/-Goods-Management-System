package org.ssmmaven7.model;

import java.io.Serializable;
import java.util.Date;

public class Good implements Serializable{
	private Integer goodId;
	private String code;
	private String goodName;
	private Integer supplierId;
	private double price;
	private String category;
	private Integer quantity;
	private String specification;
	private String shelve;
	private Supplier supplier;
	
	public Good(){}
	/**
	 * 
	 * @param goodId  货物编号
	 * @param code    货物编码
	 * @param goodName 货物名
	 * @param supplierId 供应商编号
	 * @param price 货物单价
	 * @param category 货物分类
	 * @param quantity 库存数量
	 * @param specification 货物规格
	 * @param shelve 货架
	 * @param supplier 供应商
	 */
	public Good(Integer goodId,String code,String goodName,Integer supplierId
			,double price,String category,Integer quantity,String specification
			,String shelve,Supplier supplier){
		this.goodId = goodId;
		this.code = code;
		this.goodName = goodName;
		this.supplierId = supplierId;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.specification = specification;
		this.shelve = shelve;
		this.supplier = supplier;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Integer getGoodId() {
		return goodId;
	}
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getShelve() {
		return shelve;
	}
	public void setShelve(String shelve) {
		this.shelve = shelve;
	}
    
	
}
