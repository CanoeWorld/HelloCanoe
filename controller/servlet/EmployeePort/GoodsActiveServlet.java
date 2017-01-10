package com.canoe.controller.servlet.EmployeePort;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.GoodsActive;
import com.canoe.service.IGoodsActiveService;
import com.canoe.service.impl.GoodsActiveService;
import com.canoe.util.ParameterUtil;

public class GoodsActiveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IGoodsActiveService goodsActiveService = new GoodsActiveService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("m");
		
		if("list".equals(method)){
			
			List<GoodsActive> list = goodsActiveService.list(null);
			
			req.setAttribute("goodsActiveList",list);
			
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/goodsactive/list.jsp").forward(req, resp);
			
			return;
			
		}else if("edit".equals(method)){
			
			String activeId = req.getParameter("activeId");
			
			if(activeId != null && !"".equals(activeId)){
				
				req.setAttribute("goodsActive", goodsActiveService.get(Integer.parseInt(activeId)));
			}
			
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/goodsactive/edit.jsp").forward(req, resp);
			
			return;
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("m");
		
		GoodsActive goodsActive = ParameterUtil.put(GoodsActive.class, req);
		
		if("save".equals(method)){
			
			int activeId = goodsActive.getActiveId();
			
			if(activeId != 0){
				goodsActiveService.update(goodsActive);
			}else{
				goodsActiveService.save(goodsActive);
			}
			
			resp.sendRedirect(req.getContextPath() + "/servlet/GoodsActiveServlet?m=list");
			
			return;
		}
		
	}
}
