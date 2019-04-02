package org.ssmmaven7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Supplier;

public interface SupplierDao {
	public int insertSupplier(Supplier supplier);
	public void deleteBySupplierId(@Param("supplierId")int supplierId);
	public Supplier findBySupplierName(@Param("supplierName")String supplierName);
	public List<Supplier> findAllSupplier();
	public Supplier findBySupplierId(@Param("supplierId")int supplierId);
	public List<Supplier> findSupplierByCategory(@Param("category")String category);
	public void updatePhone(@Param("phone")String phone, @Param("supplierId")int supplierId);
	public void updatePersonInCharge(@Param("personInCharge")String personInCharge, @Param("supplierId")int supplierId);
	public void updateAddress(@Param("address")String address, @Param("supplierId")int supplierId);
}
