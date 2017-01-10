package com.canoe.controller.servlet.EmployeePort;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.GoodsType;
import com.canoe.service.IGoodsTypeService;
import com.canoe.service.impl.GoodsTypeService;
import com.canoe.util.ParameterUtil;
import com.canoe.util.listener.ApplicationListener;

public class GoodsTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	IGoodsTypeService goodsTypeService = new GoodsTypeService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");

		if ("list".equals(method)) {

			List<GoodsType> list = goodsTypeService.list(null);

			req.setAttribute("goodsTypes", list);

			req.getRequestDispatcher(
					"/WEB-INF/eshop/EmployeePort/goodstype/list.jsp").forward(
					req, resp);
		}else if("edit".equals(method)){
			
			String typeId = req.getParameter("typeId");
			
			if(typeId != null && !"".equals(typeId)){
				//不为空是修改 为空是添加
				req.setAttribute("goodsType", goodsTypeService.get(Integer.parseInt(typeId)));
			}
			
			GoodsType goodsType = new GoodsType();
			
			goodsType.setParentId(-1);
			
			req.setAttribute("parentGoodsTypes",goodsTypeService.list(goodsType) );
			
			req.getRequestDispatcher(
					"/WEB-INF/eshop/EmployeePort/goodstype/edit.jsp").forward(
					req, resp);
		}else if("cache".equals(method)){
			
			resp.setCharacterEncoding("UTF-8");
			//更新缓存
			try {
				ApplicationListener applicationListener = new ApplicationListener();
				applicationListener.updateCache(req.getServletContext());
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().write("{code:'failed',msg:'更新缓存失败'}");
				resp.getWriter().close();
			}
			resp.getWriter().write("{code:'success',msg:'更新缓存成功'}");
			resp.getWriter().close();
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		
		GoodsType goodsType = ParameterUtil.put(GoodsType.class, req);
		
		if("save".equals(method)){
			
			int typeId = goodsType.getTypeId();
			
			if(typeId != 0){
				
				goodsTypeService.update(goodsType);
			}else{
				goodsTypeService.save(goodsType);
			}
			
			
			
			resp.sendRedirect(req.getContextPath() + "/servlet/GoodsTypeServlet?m=list");
			
		}
	}
}
