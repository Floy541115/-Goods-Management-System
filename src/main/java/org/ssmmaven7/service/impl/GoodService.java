package org.ssmmaven7.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssmmaven7.dao.GoodDao;
import org.ssmmaven7.model.Good;
import org.ssmmaven7.service.IGoodService;
@Service
public class GoodService implements IGoodService{
	@Autowired
	private GoodDao goodDao;

	@Override
	public int insertGood(Good good) {
		// TODO Auto-generated method stub
		return goodDao.insertGood(good);
	}

	@Override
	public Good findGoodById(int goodId) {
		// TODO Auto-generated method stub
		return goodDao.findGoodById(goodId);
	}

	@Override
	public List<Good> findGoodByCategory(String category) {
		// TODO Auto-generated method stub
		return goodDao.findGoodByCategory(category);
	}

	@Override
	public void deleteByGoodId(int goodId) {
		// TODO Auto-generated method stub
		goodDao.deleteByGoodId(goodId);
	}

	@Override
	public void updateGood(int quantity, int goodId) {
		// TODO Auto-generated method stub
		goodDao.updateGood(quantity, goodId);
	}

	@Override
	public List<Good> findAllGood() {
		// TODO Auto-generated method stub
		return goodDao.findAllGood();
	}

	@Override
	public List<Good> findGoodByName(String goodName) {
		// TODO Auto-generated method stub
		return goodDao.findGoodByName(goodName);
	}

}
