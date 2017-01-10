package com.canoe.dao;

import java.util.List;
/**
 * 商品分类的Dao类接口
 * @author Administrator
 *
 */
import com.canoe.bean.GoodsType;

public interface IGoodsTypeDao {
	
	public void save(GoodsType goodsType);
	
	public void update(GoodsType goodsType);
	
	public List<GoodsType> list(GoodsType goodsType);
	
	public GoodsType get(int typeId);
}
