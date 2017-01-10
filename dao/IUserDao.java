package com.canoe.dao;

import java.util.List;

import com.canoe.bean.User;
import com.canoe.util.Pager;

/**
 * 用户dao接口
 * @author Administrator
 *
 */
public interface IUserDao {
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(int userId);
	
	public List<User> list(User user,Pager pager);
	
	public User get(int userId);
	
	public User get(String userAccount);
	
	
}
