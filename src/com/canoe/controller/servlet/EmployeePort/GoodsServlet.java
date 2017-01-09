package com.canoe.controller.servlet.EmployeePort;


import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.Goods;
import com.canoe.bean.GoodsActive;
import com.canoe.bean.GoodsType;
import com.canoe.service.IGoodsActiveService;
import com.canoe.service.IGoodsService;
import com.canoe.service.IGoodsTypeService;
import com.canoe.service.impl.GoodsActiveService;
import com.canoe.service.impl.GoodsService;
import com.canoe.service.impl.GoodsTypeService;
import com.canoe.util.Pager;
import com.canoe.util.ParameterUtil;

public class GoodsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//商品服务
	IGoodsService goodsService = new GoodsService();
	
	//商品分类服务
	IGoodsTypeService goodsTypeService = new GoodsTypeService();
	//商品促销活动服务
	IGoodsActiveService goodsActiveService = new GoodsActiveService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		
		if("list".equals(method)){
			
			//搜索
			Goods goods = ParameterUtil.put(Goods.class, req);
			
			Pager pager = new Pager("servlet/GoodsServlet?1=1",req);
			req.setAttribute("goodsList", goodsService.list(goods,pager));
			
			
			
			//查询促销活动
			GoodsActive goodsActive = new GoodsActive();
			goodsActive.setActiveStatus(1);
			req.setAttribute("goodsActiveList",goodsActiveService.list(goodsActive));
			
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/goods/list.jsp").forward(req,resp);
			return;
			
		}else if("edit".equals(method)){
			
			//查促销活动
			GoodsActive goodsActive = new GoodsActive();
			goodsActive.setActiveStatus(1);
			req.setAttribute("goodsActiveList", goodsActiveService.list(goodsActive));
			
			String goodsId = req.getParameter("goodsId");
			if(goodsId != null && !"".equals(goodsId)){
				//查询商品信息
				Goods goods = goodsService.get(Integer.parseInt(goodsId));
				req.setAttribute("goods",goods);
				
				//查父级别的分类列表
				int typeId = goods.getTypeId();
				if(typeId != 0){
					GoodsType type = goodsTypeService.get(typeId);
					//分类的父类(一级分类的id)
					int parentId = type.getParentId();
					req.setAttribute("parentId",parentId);
				}
			}
			req.getRequestDispatcher("/WEB-INF/eshop/EmployeePort/goods/edit.jsp").forward(req,resp);
			return;
		}else if("ajaxType".equals(method)){
			
			
			//优化后不需要走ajax
			
			
			resp.setCharacterEncoding("UTF-8");
			
			//获得输出流写出去
			Writer writer = resp.getWriter();
			
			
			String id = req.getParameter("id");
			
			GoodsType goodsType = new GoodsType();
			goodsType.setParentId(Integer.parseInt(id));
			
			//获得二级分类 拼装成json格式
			/*List<GoodsType> list = goodsTypeService.list(goodsType);
			
			
			
			StringBuffer buffer = new StringBuffer();
			
			buffer.append("[");
			//拼装成如: [{id:1,name:"苹果"},{id:2,name:"香蕉"}]
			for(GoodsType type : list){
				buffer.append("{id:").append(type.getTypeId());
				buffer.append(",name:\"").append(type.getTypeName()).append("\"}");
				buffer.append(",");
			}
			
			//删除最后一个逗号
			if(buffer.length() > 1){
				//如果不减1会造成数组越界
				buffer.deleteCharAt(buffer.length() - 1);
			}
			buffer.append("]");
			
			writer.write(buffer.toString());*/
			
			writer.close();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Goods goods = ParameterUtil.put(Goods.class, req);
		String method = req.getParameter("m");
		if("save".equals(method)){
			
			int goodsId = goods.getGoodsId();
			
			if(goodsId != 0){
				goodsService.update(goods);
			}else{
				goodsService.save(goods);
			}
			
			resp.sendRedirect(req.getContextPath() +"/servlet/GoodsServlet?m=list");
			return;
		}else if("list".equals(method)){
			doGet(req, resp);
			return;
		}else if("updateStatus".equals(method)){
			
			String status = req.getParameter("status");
			
			String[] goodsIds = req.getParameterValues("goodsId");
			
			goodsService.updateStatus(goodsIds,Integer.parseInt(status));
			
			resp.sendRedirect(req.getContextPath() +"/servlet/GoodsServlet?m=list");
			
			return;
		}
		
	}
}
