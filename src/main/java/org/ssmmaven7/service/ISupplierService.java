package org.ssmmaven7.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Supplier;

public interface ISupplierService {
	public int insertSupplier(Supplier supplier);
	public void deleteBySupplierId(int supplierId);
	public Supplier findBySupplierName(String supplierName);
	public List<Supplier> findSupplierByCategory(String category);
	public List<Supplier> findAllSupplier();
	public Supplier findBySupplierId(int supplierId);
	public void updatePhone(String phone,int supplierId);
	public void updatePersonInCharge(String personInCharge, int supplierId);
	public void updateAddress(String address,int supplierId);
}
