package com.canoe.controller.servlet.UserPort;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.canoe.bean.User;
import com.canoe.service.IUserService;
import com.canoe.service.impl.UserService;
import com.canoe.util.MD5;
import com.canoe.util.ParameterUtil;
/**
 * 用户登陆、注册
 * @author Administrator
 *
 */
public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	IUserService userService = new UserService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("m");
		
		if("reg".equals(method)){
			
			User user = ParameterUtil.put(User.class, req);
			user.setUserStatus(1);
			user.setUserName("Canoe用户");
			userService.save(user);
			
			//做登陆
			req.getSession().setAttribute("user_session", user);
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
			
		}else if("login".equals(method)){
			
			User user = ParameterUtil.put(User.class, req);
			
			User user_date = userService.get(user.getUserAccount());
			//校验
			if(user_date != null && user_date.getUserPwd().equals(MD5.createPassword(user.getUserPwd()))){
				
				//做登陆
				
				String remberLogin = req.getParameter("remberLogin");
				//是否选中自动登陆
				if("1".equals(remberLogin)){
					Cookie cookie = new Cookie("userAccount", user_date.getUserAccount());
					
					//设置Cookie的生命周期，如果设置为负值的话，关闭浏览器就失效，0要删除该cookie
					cookie.setMaxAge(7*24*60);//单位为秒
					
					cookie.setPath("/");
					resp.addCookie(cookie);
				}
				req.getSession().setAttribute("user_session", user_date);
				resp.sendRedirect(req.getContextPath() + "/index");
				return;
			}
			
			req.setAttribute("msg", "登陆失败，请检查用户名或密码");
			req.getRequestDispatcher("/WEB-INF/eshop/UserPort/login.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("m");
		if("login".equals(method)){
			req.getRequestDispatcher("/WEB-INF/eshop/UserPort/login.jsp").forward(req, resp);
			return;
		}else if("reg".equals(method)){
			req.getRequestDispatcher("/WEB-INF/eshop/UserPort/reg.jsp").forward(req, resp);
			return;
		}else if("out".equals(method)){
			
			User user = (User)req.getSession().getAttribute("user_session");
			//确实是登陆状态再做退出
			if(user != null){
				
				Cookie cookie = new Cookie("userAccount",user.getUserAccount());
				//设置Cookie的生命周期，如果设置为负值的话，关闭浏览器就失效，0要删除该cookie
				cookie.setMaxAge(0);//单位为秒
				cookie.setPath("/");
				resp.addCookie(cookie);
				
				req.getSession().removeAttribute("user_session");
				req.getSession().invalidate();
			}
			
			resp.sendRedirect(req.getContextPath() + "/index");
			return;
		}
		
	}
}
