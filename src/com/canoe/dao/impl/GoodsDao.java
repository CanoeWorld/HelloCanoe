package com.canoe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.canoe.bean.Goods;
import com.canoe.dao.IGoodsDao;
import com.canoe.util.DateUtil;
import com.canoe.util.JdbcUtil;
import com.canoe.util.Pager;
import com.canoe.util.Util;
/**
 * 商品dao实现类
 * @author Administrator
 *
 */
public class GoodsDao implements IGoodsDao{
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	@Override
	public void save(Goods goods) {
		
		String sql = "insert into GOODS (GOODS_ID, GOODS_NAME, GOODS_CODE, GOODS_OUTDATE, GOODS_STOREDAY, GOODS_STORENUM, TYPE_ID, ACTIVE_ID, GOODS_PRICE, GOODS_REMARK, GOODS_PIC1, GOODS_PIC2, GOODS_PIC3, GOODS_PIC4, GOODS_PIC5, GOODS_STATUS)"+
				" values (SEQ_GOODS.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		jdbcUtil.execute(sql, new Object[]{
				goods.getGoodsName(),
				goods.getGoodsCode(),
				goods.getGoodsOutdate(),
				goods.getGoodsStoreday(),
				goods.getGoodsStorenum(),
				goods.getTypeId(),
				goods.getActiveId(),
				goods.getGoodsPrice(),
				goods.getGoodsRemark(),
				goods.getGoodsPic1(),
				goods.getGoodsPic2(),
				goods.getGoodsPic3(),
				goods.getGoodsPic4(),
				goods.getGoodsPic5(),
				goods.getGoodsStatus()
		});
	}

	@Override
	public void update(Goods goods) {
		
		String sql = "update  GOODS set GOODS_NAME = ?, GOODS_CODE = ?, GOODS_OUTDATE = ?, GOODS_STOREDAY = ?, GOODS_STORENUM = ?, TYPE_ID = ?, ACTIVE_ID = ?, GOODS_PRICE = ?, GOODS_REMARK = ?, GOODS_PIC1 = ?, GOODS_PIC2 = ?, GOODS_PIC3 = ?, GOODS_PIC4 = ?, GOODS_PIC5 = ?, GOODS_STATUS = ? where GOODS_ID = ? ";
		
		jdbcUtil.execute(sql, new Object[]{
				goods.getGoodsName(),
				goods.getGoodsCode(),
				goods.getGoodsOutdate(),
				goods.getGoodsStoreday(),
				goods.getGoodsStorenum(),
				goods.getTypeId(),
				goods.getActiveId(),
				goods.getGoodsPrice(),
				goods.getGoodsRemark(),
				goods.getGoodsPic1(),
				goods.getGoodsPic2(),
				goods.getGoodsPic3(),
				goods.getGoodsPic4(),
				goods.getGoodsPic5(),
				goods.getGoodsStatus(),
				goods.getGoodsId()
		});
		
	}
	@Override
	public List<Goods> list(Goods goods,Pager pager) {
		
		String sql = "select g.*,gt.type_name  \"goodsType.type_name\",ga.active_name  \"goodsActive.active_name\" from goods g left join goods_type gt on gt.type_id = g.type_id " +
				"left join goods_active ga on ga.active_id = g.active_id where 1=1 ";
		
		List<Object> parameter = new ArrayList<Object>();
		if(goods != null){
			//处理商品分类
			String goodsName = goods.getGoodsName();
			if(Util.isNotEmpty(goodsName)){
				sql += " and g.goods_Name like '%'||?||'%'";
				parameter.add(goodsName);
			}
			
			//处理一级分类
			int parentId = goods.getParentId();
			if(parentId != 0){
				sql += " and gt.parent_id = ? ";
				parameter.add(parentId);
			}
			
			//处理二级分类
			int typeId = goods.getTypeId();
			if(typeId != 0){
				sql += " and gt.type_id = ? ";
				parameter.add(typeId);
			}
			
			//处理促销活动
			Integer activeId = goods.getActiveId();
			if(activeId != null){
				sql += " and ga.active_id = ? ";
				parameter.add(activeId);
			}
			
			//处理状态
			Integer goodsStatus = goods.getGoodsStatus();
			if(goodsStatus != null){
				sql += " and g.goods_status = ? ";
				parameter.add(goodsStatus);
			}
		}
		
		return jdbcUtil.executeQuery(Goods.class, sql, parameter.toArray(), pager);
	}

	@Override
	public Goods get(int goodsId) {
		String sql = "select g.*,gt.type_name  \"goodsType.type_name\",ga.active_name  \"goodsActive.active_name\" from goods g left join goods_type gt on gt.type_id = g.type_id " +
				"left join goods_active ga on ga.active_id = g.active_id" +
				" where goods_id = ?";
		
		return jdbcUtil.executeQuery(Goods.class, sql, new Object[]{goodsId}).get(0);
	}
	
	//更新商品状态
	@Override
	public void updateStatus(Goods goods) {
		String sql = "update goods set goods_status = ? where goods_id = ?";
		jdbcUtil.execute(sql, new Object[]{goods.getGoodsStatus(),goods.getGoodsId()});
	}

	@Override
	public void updateStatus(String[] goodsIds, int status) {
		String sql = "update goods set goods_status = ? where goods_id in (";
		
		Object[] objs = new Object[goodsIds.length + 1];
		objs[0] = status;
		
		for(int i = 0; i < goodsIds.length; i ++){
			sql +="?,";
			objs[i + 1] = goodsIds[i];
		}
		sql = sql.substring(0,sql.length() -1);
		sql +=")";
		jdbcUtil.execute(sql,objs);
	}
	
	//用户端参与促销的商品
	@Override
	public List<Goods> activeList() {
		String sql = "select g.* from goods g inner join goods_active ga " +
				" on ga.active_id = g.active_id and ga.active_status = 1 and ACTIVE_START_DATE <= to_date(?,'yyyy-mm-dd') and  ACTIVE_END_DATE >= to_date(?,'yyyy-mm-dd') " +
				" where g.GOODS_STATUS = 1 order by ga.active_sort";
		String nowDate = DateUtil.format("yyyy-MM-dd");
		Pager pager = new Pager();
		//每页设置的条数
		pager.setPageCount(8);
		return jdbcUtil.executeQuery(Goods.class, sql, new Object[]{nowDate,nowDate},pager);
	}

}
