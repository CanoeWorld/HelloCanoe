package com.canoe.dao;

import java.util.List;

import com.canoe.bean.Goods;
import com.canoe.util.Pager;

/**
 * 商品dao接口
 * @author Administrator
 *
 */
public interface IGoodsDao {
	
	public void save(Goods goods);
	
	public void update(Goods goods);
	
	public List<Goods> list(Goods goods,Pager pager);
	
	//商品列表只取有效的和参与促销的
	public List<Goods> activeList();
	
	public Goods get(int goodsId);
	
	//修改商品的状态
	public void updateStatus(Goods goods);
	
	//批量修改商品的状态
	public void updateStatus(String[] goodsIds,int status);
}
