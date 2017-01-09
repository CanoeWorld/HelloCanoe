package com.canoe.controller.servlet.EmployeePort;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.Employee;
import com.canoe.service.IEmployeeService;
import com.canoe.service.impl.EmployeeService;
import com.canoe.util.MD5;

public class EmployeeLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IEmployeeService employeeService = new EmployeeService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String emplNo = req.getParameter("emplNo");
		String emplPwd = req.getParameter("emplPwd");
		
		Employee employee = employeeService.getByEmplNo(emplNo);
		
		if(employee != null && MD5.createPassword(emplPwd).equals(employee.getEmplPwd())){
			
			//登陆成功
			req.getSession().setAttribute("employee_user", employee);
			
			resp.sendRedirect(req.getContextPath()+"/employee/main");
			
			return;
		}
		
		//登陆失败
		req.setAttribute("msg","登录失败，请检查工号或密码");
		req.getRequestDispatcher("/Eshop/login.jsp").forward(req,resp);
		
		return;
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getSession().removeAttribute("employee_user");
		req.getSession().invalidate();
		
		resp.sendRedirect(req.getContextPath() + "/Eshop/login.jsp");
	}
}
