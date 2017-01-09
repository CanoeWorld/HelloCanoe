package com.canoe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.canoe.bean.Employee;
import com.canoe.dao.IEmployeeDao;
import com.canoe.util.JdbcUtil;
import com.canoe.util.Pager;

public class EmployeeDao implements IEmployeeDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	@Override
	public void save(Employee employee) {

		String sql = "insert into employee (empl_id,empl_name,empl_sex,empl_no,empl_pwd,empl_remark,EMPL_USERNAME,EMPL_AGE,EMPL_STATUS) values(SEQ_EMPLOYEE.nextval,?,?,?,?,?,?,?,?) ";

		jdbcUtil.execute(
				sql,
				new Object[] { employee.getEmplName(), employee.getEmplSex(),
						employee.getEmplNo(), employee.getEmplPwd(),
						employee.getEmplRemark(), employee.getEmplUsername(),
						employee.getEmplAge(), employee.getEmplStatus() });
	}

	@Override
	public void update(Employee employee) {

		String sql = "update  employee set empl_name = ?,empl_sex = ?,empl_no = ?,empl_pwd = ?,empl_remark = ?,EMPL_USERNAME = ?,EMPL_AGE = ?,EMPL_STATUS = ? where empl_Id = ?";

		jdbcUtil.execute(
				sql,
				new Object[] { employee.getEmplName(), employee.getEmplSex(),
						employee.getEmplNo(), employee.getEmplPwd(),
						employee.getEmplRemark(), employee.getEmplUsername(),
						employee.getEmplAge(), employee.getEmplStatus(),
						employee.getEmplId() });
	}

	@Override
	public List<Employee> list(Employee employee) {

		// 参数列表
		String sql = "select e.* from employee e where 1=1 ";

		List<Object> parameterList = new ArrayList<Object>();

		if (employee != null) {

			String emplNo = employee.getEmplNo();
			if (emplNo != null && !"".equals(emplNo)) {
				sql += " and empl_no like '%'||?||'%' ";
				parameterList.add(emplNo);
			}

			String emplName = employee.getEmplName();
			if (emplName != null && !"".equals(emplName)) {
				sql += " and empl_name like '%'||?||'%'";
				parameterList.add(emplName);
			}

			Integer emplSex = employee.getEmplSex();
			if (emplSex != null) {
				sql += " and empl_sex = ?";
				parameterList.add(emplSex);
			}

			Integer emplStatus = employee.getEmplStatus();
			if (emplStatus != null) {
				sql += "and EMPL_STATUS = ?";
				parameterList.add(emplStatus);
			}
		}

		sql += " order by empl_no desc";
		System.out.println(sql);
		return jdbcUtil.executeQuery(Employee.class, sql,
				parameterList.toArray());
	}

	@Override
	public List<Employee> list(Employee employee, Pager pager) {

		// 参数列表
		String sql = "select e.* from employee e where 1=1 ";
		
		List<Object> parameterList = new ArrayList<Object>();
		
		//如果有对象才做搜索
		if (employee != null) {

			String emplNo = employee.getEmplNo();
			if (emplNo != null && !"".equals(emplNo)) {
				sql += " and empl_no like '%'||?||'%' ";
				parameterList.add(emplNo);
			}

			String emplName = employee.getEmplName();
			if (emplName != null && !"".equals(emplName)) {
				sql += " and empl_name like '%'||?||'%'";
				parameterList.add(emplName);
			}

			Integer emplSex = employee.getEmplSex();
			if (emplSex != null) {
				sql += " and empl_sex = ?";
				parameterList.add(emplSex);
			}

			Integer emplStatus = employee.getEmplStatus();
			if (emplStatus != null) {
				sql += "and EMPL_STATUS = ?";
				parameterList.add(emplStatus);
			}
		}
		sql += " order by empl_no desc";
		
		return jdbcUtil.executeQuery(Employee.class, sql,
				parameterList.toArray(), pager);
	}

	@Override
	public Employee get(int emplId) {

		String sql = "select * from employee where empl_id = ?";
		return jdbcUtil.executeQuery(Employee.class, sql,
				new Object[] { emplId }).get(0);
	}

	@Override
	public Employee get(String emplUsername) {

		String sql = "select * from employee where EMPL_USERNAME = ?";

		List<Employee> list = jdbcUtil.executeQuery(Employee.class, sql,
				new Object[] { emplUsername });

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	@Override
	public Employee getByEmplNo(String emplNo) {

		String sql = "select * from employee where empl_no = ?";

		List<Employee> list = jdbcUtil.executeQuery(Employee.class, sql,
				new Object[] { emplNo });

		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}

	}

	public static void main(String[] args) {
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.list(null, new Pager());
	}
}
