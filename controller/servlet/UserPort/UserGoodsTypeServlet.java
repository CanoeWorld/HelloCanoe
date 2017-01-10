package com.canoe.controller.servlet.UserPort;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.Goods;
import com.canoe.service.IGoodsService;
import com.canoe.service.IGoodsTypeService;
import com.canoe.service.impl.GoodsService;
import com.canoe.service.impl.GoodsTypeService;
import com.canoe.util.Pager;

public class UserGoodsTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//商品分类服务
	IGoodsTypeService goodsTypeService = new GoodsTypeService();
	
	//商品服务
	IGoodsService goodsService = new GoodsService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//查分类下面的商品
		String typeId = req.getParameter("typeId");
		Goods goods = new Goods();
		goods.setTypeId(Integer.parseInt(typeId));
		Pager pager = new Pager("User/UserGoodsTypeServlet?1=1",req);
		pager.setPageCount(11);
		req.setAttribute("goodsList",goodsService.list(goods,pager));
		
		req.getRequestDispatcher("/WEB-INF/eshop/UserPort/goods_type.jsp").forward(req,resp);
		
		
		
	}
}
