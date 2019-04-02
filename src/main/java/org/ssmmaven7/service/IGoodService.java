package org.ssmmaven7.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Good;

public interface IGoodService {
	public int insertGood(Good good);
	public Good findGoodById(int goodId);
	public List<Good> findGoodByCategory(String category);
	public List<Good> findGoodByName(String goodName);
	public void deleteByGoodId(int goodId);
	public void updateGood(int quantity,int goodId);
	public List<Good> findAllGood();
}
