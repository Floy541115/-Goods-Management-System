package org.ssmmaven7.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssmmaven7.dao.SupplierDao;
import org.ssmmaven7.model.Supplier;
import org.ssmmaven7.service.ISupplierService;
@Service
public class SupplierService implements ISupplierService{
	@Autowired
	private SupplierDao supplierDao;

	@Override
	public int insertSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		try {
			supplierDao.insertSupplier(supplier);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void deleteBySupplierId(int supplierId) {
		// TODO Auto-generated method stub
		supplierDao.deleteBySupplierId(supplierId);
	}

	@Override
	public Supplier findBySupplierName(String supplierName) {
		// TODO Auto-generated method stub
		return supplierDao.findBySupplierName(supplierName);
	}

	@Override
	public List<Supplier> findAllSupplier() {
		// TODO Auto-generated method stub
		return supplierDao.findAllSupplier();
	}

	@Override
	public void updatePhone(String phone, int supplierId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePersonInCharge(String personInCharge, int supplierId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAddress(String address, int supplierId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Supplier> findSupplierByCategory(String category) {
		// TODO Auto-generated method stub
		return supplierDao.findSupplierByCategory(category);
	}

	@Override
	public Supplier findBySupplierId(int supplierId) {
		// TODO Auto-generated method stub
		return supplierDao.findBySupplierId(supplierId);
	}

}
