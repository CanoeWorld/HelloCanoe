package com.canoe.dao.impl;

import java.util.List;

import com.canoe.bean.GoodsType;
import com.canoe.dao.IGoodsTypeDao;
import com.canoe.util.JdbcUtil;


/*TYPE_ID                                   NOT NULL NUMBER
TYPE_NAME                                          VARCHAR2(50)
TYPE_CODE                                          VARCHAR2(20)
PARENT_ID                                          NUMBER
TYPE_SORT                                          NUMBER
TYPE_STATUS                                        NUMBER(1)*/

/**
 * 商品分类Dao
 * @author Administrator
 *
 */
public class GoodsTypeDao implements IGoodsTypeDao {
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	@Override
	public void save(GoodsType goodsType) {
		

		 
		String sql = "insert into goods_type(TYPE_ID,TYPE_NAME,TYPE_CODE,PARENT_ID,TYPE_SORT,TYPE_STATUS) values(SEQ_GOODS_TYPE.nextval,?,?,?,?,?)";
		jdbcUtil.execute(sql, new Object[]{goodsType.getTypeName(),goodsType.getTypeCode(),goodsType.getParentId(),goodsType.getTypeSort(),goodsType.getTypeStatus()});
	}

	@Override
	public void update(GoodsType goodsType) {
		String sql = "update  goods_type set TYPE_NAME = ?,TYPE_CODE = ?,PARENT_ID = ?,TYPE_SORT = ?,TYPE_STATUS = ? where TYPE_ID = ?";
		jdbcUtil.execute(sql,new Object[]{goodsType.getTypeName(),goodsType.getTypeCode(),goodsType.getParentId(),goodsType.getTypeSort(),goodsType.getTypeStatus(),goodsType.getTypeId()} );
	}

	@Override
	public List<GoodsType> list(GoodsType goodsType) {
		String sql = "select t.*,pt.type_name as \"parent.type_name\" from goods_type t left join goods_type pt on t.parent_id = pt.type_id where 1=1 ";
		
		if(goodsType != null){
			if(goodsType.getParentId() < 1){
				sql += "and t.PARENT_ID is null or t.PARENT_ID = 0 ";
			}else{
				sql += "and t.PARENT_ID = " + goodsType.getParentId();
			}
		}
		
		sql += " order by t.TYPE_SORT";
		return jdbcUtil.executeQuery(GoodsType.class, sql, null);
	}

	@Override
	public GoodsType get(int typeId) {
		String sql = "select * from goods_type  where TYPE_ID = ? order by TYPE_SORT";
		return jdbcUtil.executeQuery(GoodsType.class, sql, new Object[]{typeId}).get(0);
	}

}
