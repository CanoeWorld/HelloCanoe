package com.canoe.controller.servlet.EmployeePort;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 员工主界面servlet
 * @author Administrator
 *
 */
public class EmployeeMainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String method = req.getParameter("m");
		
		if("top".equals(method)){
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/top.jsp").forward(req,resp);
		}else if("left".equals(method)){
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/left.jsp").forward(req,resp);
		}else if("index".equals(method)){
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/index.jsp").forward(req,resp);
		}else{
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/main.jsp").forward(req,resp);
		}
		
	}
}
