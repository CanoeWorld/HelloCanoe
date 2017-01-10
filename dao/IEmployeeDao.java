package com.canoe.dao;

import java.util.List;

import com.canoe.bean.Employee;
import com.canoe.util.Pager;

public interface IEmployeeDao {

	public void save(Employee employee);

	public void update(Employee employee);

	public List<Employee> list(Employee employee);
	
	public List<Employee> list(Employee employee,Pager pager);

	// 通过员工ID查询员工对象
	public Employee get(int emplId);
	
	//作为登陆用 根据用户名给员工对象
	public Employee get(String emplUsername);
	
	public Employee getByEmplNo(String emplNo);
}
