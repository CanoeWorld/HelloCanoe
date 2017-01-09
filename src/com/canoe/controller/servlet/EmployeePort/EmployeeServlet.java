package com.canoe.controller.servlet.EmployeePort;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.Employee;
import com.canoe.service.IEmployeeService;
import com.canoe.service.impl.EmployeeService;
import com.canoe.util.MD5;
import com.canoe.util.Pager;

public class EmployeeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IEmployeeService employeeService  = new EmployeeService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 
		String method = req.getParameter("m");
		
		if("list".equals(method)){
			
			String emplName = req.getParameter("emplName");
			String emplNo = req.getParameter("emplNo");
			String emplSex = req.getParameter("emplSex");
			String emplStatus =  req.getParameter("emplStatus");
			
			Employee employee = new Employee();
			
			employee.setEmplName(emplName);
			employee.setEmplNo(emplNo);
			
			if(emplSex != null && !"".equals(emplSex)){
				employee.setEmplSex(Integer.parseInt(emplSex));
			}
			
			if(emplStatus != null && !"".equals(emplStatus)){
				employee.setEmplStatus(Integer.parseInt(emplStatus));
			}
			
			Pager pager = new Pager("servlet/EmployeeServlet?m=list",req);
			String con = req.getParameter("cno");
			
			if(pager != null && "".equals(pager)){
				pager.setCurrentPageNo(Integer.parseInt(con));
			}
			
			List<Employee> list = employeeService.list(employee,pager);
			
			req.setAttribute("employees", list);
			req.setAttribute("pager", pager);
			
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/employee/list.jsp").forward(req,resp);
			
		}else if("add".equals(method)){
			
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/employee/edit.jsp").forward(req,resp);
			
		}else if("update".equals(method)){
			
			String emplId = req.getParameter("emplId");
			Employee employee = employeeService.get(Integer.parseInt(emplId));
			req.setAttribute("employee",employee);
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/employee/edit.jsp").forward(req,resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("m");
		
		if("save".equals(method)){
			String emplName = req.getParameter("emplName");
			String emplNo = req.getParameter("emplNo");
			String emplUsername = req.getParameter("emplUsername");
			String emplSex = req.getParameter("emplSex");
			String emplAge = req.getParameter("emplAge");
			String emplStatus = req.getParameter("emplStatus");
			String emplRemark = req.getParameter("emplRemark");
			
			Employee employee = new Employee();
			
			employee.setEmplName(emplName);
			employee.setEmplNo(emplNo);
			employee.setEmplPwd(MD5.createPassword(emplNo));
			employee.setEmplUsername(emplUsername);
			employee.setEmplSex(Integer.parseInt(emplSex));
			employee.setEmplAge(Integer.parseInt(emplAge));
			employee.setEmplStatus(Integer.parseInt(emplStatus));
			employee.setEmplRemark(emplRemark);
			
			String emplId =  req.getParameter("emplId");
			
			int code = 0;
			if(emplId == null || "".equals(emplId) || Integer.parseInt(emplId) == 0){
				code = employeeService.save(employee);
			}else{
				//有员工id才可以进行修改
				employee.setEmplId(Integer.parseInt(emplId));
				code = employeeService.update(employee);
			}
			
			if(code != 0){
				
				req.setAttribute("code", code);
				req.setAttribute("employee", employee);
				req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/employee/edit.jsp").forward(req,resp);
				return;
				
			}
		//位置写错了！！！！好坑	
		}else if("list".equals(method)){
			this.doGet(req, resp);
			return;
		}
		
		resp.sendRedirect(req.getContextPath()+ "/servlet/EmployeeServlet?m=list");
	}
	
}
