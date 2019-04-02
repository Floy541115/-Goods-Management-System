package org.ssmmaven7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ssmmaven7.model.Good;

public interface GoodDao {
	public int insertGood(Good good);
	public Good findGoodById(@Param("goodId")int goodId);
	public List<Good> findGoodByCategory(@Param("category")String category);
	public List<Good> findGoodByName(@Param("goodName")String goodName);
	public void deleteByGoodId(@Param("goodId")int goodId);
	public void updateGood(@Param("quantity")int quantity, @Param("goodId")int goodId);
	public List<Good> findAllGood();
}
