package com.canoe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.canoe.bean.GoodsActive;
import com.canoe.dao.IGoodsActiveDao;
import com.canoe.util.JdbcUtil;

/**
 * 商品活动的Dao类
 * 
 * @author Administrator
 * 
 */
public class GoodsActiveDao implements IGoodsActiveDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	@Override
	public void save(GoodsActive goodsActive) {
		String sql = "insert into goods_active (ACTIVE_ID,ACTIVE_NAME,ACTIVE_CODE,ACTIVE_SORT,ACTIVE_STATUS,ACTIVE_START_DATE,ACTIVE_END_DATE) values(SEQ_GOODS_ACTIVE.nextval,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'))";
		jdbcUtil.executeQuery(
				GoodsActive.class,
				sql,
				new Object[] { goodsActive.getActiveName(),
						goodsActive.getActiveCode(),
						goodsActive.getActiveSort(),
						goodsActive.getActiveStatus(),
						goodsActive.getActivestartdate(),
						goodsActive.getActiveenddate() });
	}

	@Override
	public void update(GoodsActive goodsActive) {
		String sql = "update goods_active set ACTIVE_NAME = ?,ACTIVE_CODE = ?,ACTIVE_SORT = ?,ACTIVE_STATUS = ?,ACTIVE_START_DATE = to_date(?,'yyyy-mm-dd'),ACTIVE_END_DATE = to_date(?,'yyyy-mm-dd') where ACTIVE_ID = ?";
		jdbcUtil.execute(
				sql,   new Object[] { 
						goodsActive.getActiveName(),
						goodsActive.getActiveCode(),
						goodsActive.getActiveSort(),
						goodsActive.getActiveStatus(),
						goodsActive.getActivestartdate(),
						goodsActive.getActiveenddate(),
						goodsActive.getActiveId() });
	}

	@Override
	public void delete(int activeId) {
		String sql = "delete goods_active where active_id = ?";
		jdbcUtil.executeQuery(GoodsActive.class, sql, new Object[] { activeId });
	}

	@Override
	public List<GoodsActive> list(GoodsActive goodsActive) {

		String sql = "select ga.*,ga.active_start_date activestartdate,ga.active_end_date activeenddate from goods_active ga where 1=1 ";

		List<Object> parameters = new ArrayList<Object>();

		if (goodsActive != null) {
			int activeStatus = goodsActive.getActiveStatus();
			// 按照状态过滤
			if (activeStatus != 0) {
				sql += " and ACTIVE_STATUS = ?";
				parameters.add(activeStatus);
			}

			// 按照起始日期和结束日期过滤
			String startDate = goodsActive.getActivestartdate();
			if (startDate != null && !"".equals(startDate)) {
				sql += " and ACTIVE_START_DATE <= to_date(?,'yyyy-mm-dd')";
				parameters.add(startDate);
			}

			String endDate = goodsActive.getActiveenddate();
			if (endDate != null && !"".equals(endDate)) {
				sql += " and ACTIVE_END_DATE >= to_date(?,'yyyy-mm-dd')";
				parameters.add(endDate);
			}
		}

		return jdbcUtil.executeQuery(GoodsActive.class, sql,
				parameters.toArray());
	}

	@Override
	public GoodsActive get(int activeId) {
		String sql = "select ga.*,ga.active_start_date activestartdate,ga.active_end_date activeenddate from goods_active ga where ACTIVE_ID = ?  ";
		return jdbcUtil.executeQuery(GoodsActive.class, sql,
				new Object[] { activeId }).get(0);
	}

}
