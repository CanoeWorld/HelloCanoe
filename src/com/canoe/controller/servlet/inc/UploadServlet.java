package com.canoe.controller.servlet.inc;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.canoe.util.ConfigProperties;

@MultipartConfig
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//文件上传的目录 下划线代表系统变量
	private String _uploadPath;
	
	//允许上传的文件类型
	private String[] _allowedTypes;
	
	//允许上传的文件最大size
	private long _allowedMaxSize;
		

		@Override
		public void init(ServletConfig config) throws ServletException {
			//初始化上传文件夹物理地址
			_uploadPath = ConfigProperties.getValue("uploadPath");
			
			//初始化允许上传的文件类型
			String types = ConfigProperties.getValue("allowedType").toLowerCase();
			_allowedTypes = types.split(",");
			
			//初始化允许上传的文件最大size
			_allowedMaxSize = Long.parseLong(ConfigProperties.getValue("allowedMaxSize"));
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			
			//接收客户端上传的文件
			Part imgPart = req.getPart("uploadFile");
			
			long size = imgPart.getSize();
			
			if (size > 0){
				
				//允许上传的文件最大size
				long allowedMaxSize = _allowedMaxSize;
				
				String sizep = req.getParameter("size");
				
				if(sizep != null && !"".equals(sizep)){
					//如果客户端定制的大小没有超出系统允许的最大值，则以客户端定制的大小为准
					if(Long.parseLong(sizep) < _allowedMaxSize ){
						allowedMaxSize = Long.parseLong(sizep);
					}
				}
				
				if(size > allowedMaxSize){
					req.setAttribute("msg", "上传失败，文件超也了允许的大小[" + allowedMaxSize + "B]");
					req.setAttribute("code", "failed");
					req.getRequestDispatcher("/pages/inc/upload.jsp").forward(req, resp);
					return;
				}
				
				//允许上传的文件类型
				List<String> allowedTypes = new ArrayList<String>();
				String allowedTypes_str = "";
				
				//取客户端定制的文件类型
				String type = req.getParameter("type");
				if(type != null && !"".equals(type)){
					type = type.toLowerCase();
					//解析客户端定制的文件类型
					String[] types = type.split(",");
					for (String t :types){
					   //检查客户端定制的类型，是否在允许的范围内
						for (String _allowedType : _allowedTypes){
							if(t.equals(_allowedType)){
								allowedTypes.add("." + t);
								allowedTypes_str += t + ",";
								break;
							}
						}
					}
				}else{
					//如果客户端未定制，则走系统默认的
					for (String _allowedType : _allowedTypes){
						allowedTypes.add("." + _allowedType);
						allowedTypes_str += _allowedType + ",";
					}
				}
				allowedTypes_str = allowedTypes_str.substring(0, allowedTypes_str.length() - 1);
				
				//从头里获取上传信息
				String heard = imgPart.getHeader("content-disposition");
				
				//截取文件名
				String filename = heard.substring(heard.indexOf("filename=") + 10, heard.length() - 1);
						
				//取扩展名
				String samllName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
				
				//检查文件是否合法
				if(allowedTypes.indexOf(samllName) == -1){
					req.setAttribute("msg", "上传失败，文件类型超出了允许的范围[" + allowedTypes_str + "]");
					req.setAttribute("code", "failed");
					req.getRequestDispatcher("/pages/inc/upload.jsp").forward(req, resp);
					return;
				}
				
				
				InputStream inputstream = imgPart.getInputStream();
				
				String nowDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				
				String randomFileName = nowDate + ((int)(Math.random() * Math.pow(10, 5))) + samllName;
				
				OutputStream outputStream = new FileOutputStream(new File(_uploadPath + "/" +  randomFileName));
				
				int b;
				while((b = inputstream.read()) != -1){
					outputStream.write(b);
				}
				outputStream.close();
				inputstream.close();
				
				req.setAttribute("msg", "上传成功");
				req.setAttribute("code", "success");
				req.setAttribute("path", randomFileName);
			}
			
			req.getRequestDispatcher("/pages/inc/upload.jsp").forward(req, resp);
		}
}
