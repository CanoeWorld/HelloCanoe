package com.canoe.controller.servlet.UserPort;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.Goods;
import com.canoe.bean.GoodsActive;
import com.canoe.service.IGoodsActiveService;
import com.canoe.service.IGoodsService;
import com.canoe.service.IGoodsTypeService;
import com.canoe.service.impl.GoodsActiveService;
import com.canoe.service.impl.GoodsService;
import com.canoe.service.impl.GoodsTypeService;

public class IndexServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	
	//商品分类服务
	IGoodsTypeService goodsTypeService = new GoodsTypeService();
	
	//商品促销服务
	IGoodsActiveService goodsActiveService = new GoodsActiveService();
	
	//商品服务
	IGoodsService goodsService = new GoodsService();
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		GoodsActive goodsActive = new GoodsActive();
		//状态为上架的
		goodsActive.setActiveStatus(1);
		
		//商品促销
		req.setAttribute("goodsActiveList", goodsActiveService.list(goodsActive));
		
		//取促销商品
		List<Goods> activeGoodsList = goodsService.activeList();
		req.setAttribute("activeGoodsList",activeGoodsList);
		
		req.getRequestDispatcher("/WEB-INF/eshop/UserPort/index.jsp").forward(req, resp);
	}
}
