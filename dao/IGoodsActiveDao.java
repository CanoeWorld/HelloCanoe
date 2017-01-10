package com.canoe.dao;

import java.util.List;
/**
 * 商品活动的Dao接口
 * @author Administrator
 *
 */
import com.canoe.bean.GoodsActive;

public interface IGoodsActiveDao {
	
	public void save(GoodsActive goodsActive);
	
	public void update(GoodsActive goodsActive);
	
	public void delete(int activeId);
	
	public List<GoodsActive> list(GoodsActive goodsActive);
	
	public GoodsActive get(int activeId);
}
