package com.canoe.dao.impl;

import java.util.List;

import com.canoe.bean.User;
import com.canoe.dao.IUserDao;
import com.canoe.util.JdbcUtil;
import com.canoe.util.Pager;

public class UserDao implements IUserDao {
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	@Override
	public void save(User user) {
		String sql = "insert into T_USER (USER_ID, USER_ACCOUNT, USER_NAME, USER_PWD, USER_PHONE, USER_EMAIL, USER_STATUS) " +
				" values (SEQ_USER.nextval, ?, ?, ?, ?, ?, ?)";
		
		jdbcUtil.execute(sql, new Object[]{
				user.getUserAccount(),
				user.getUserName(),
				user.getUserPwd(),
				user.getUserPhone(),
				user.getUserEmail(),
				user.getUserStatus()
		});
	}

	@Override
	public void update(User user) {
		String sql = "update  T_USER set USER_NAME = ?, USER_PHONE = ?, USER_EMAIL = ?, USER_STATUS = ? where USER_ID = ? ";
		
		jdbcUtil.execute(sql, new Object[]{
				user.getUserName(),
				user.getUserPhone(),
				user.getUserEmail(),
				user.getUserStatus(),
				user.getUserId()
		});
	}

	@Override
	public void delete(int userId) {
		String sql = "delete  T_USER where USER_ID = ?";
		jdbcUtil.execute(sql, new Object[]{userId});
	}

	@Override
	public List<User> list(User user, Pager pager) {
		
		String sql = "select * from t_user";
		return jdbcUtil.executeQuery(User.class, sql, null);
	}

	@Override
	public User get(int userId) {
		
		String sql = "select * from t_user where USER_ID = ?";
		return jdbcUtil.executeQuery(User.class, sql, new Object[]{userId}).get(0);
	}

	@Override
	public User get(String userAccount) {
		String sql = "select * from t_user where USER_ACCOUNT = ?";
		List<User> list = jdbcUtil.executeQuery(User.class, sql, new Object[]{userAccount});
		
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
