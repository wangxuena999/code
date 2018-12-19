package com.tjzs.zscms.advert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.advert.bean.AdvertBean;
import com.tjzs.zscms.advert.service.AdvertService;
import com.tjzs.zscms.exception.SysException;

public class AdvertGetByIdServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//获取修改的广告id
	int id=Integer.parseInt(req.getParameter("id"));
	//创建逻辑层对象
	AdvertService as=new AdvertService();
	
	try {
		//将id传递下去进行查询
		AdvertBean advert=as.queryAdvertById(id);
		//将对象存到作用域中
		req.setAttribute("advert", advert);
		//转发到广告修改的页面
		req.getRequestDispatcher("advert/update.jsp").forward(req, resp);
	} catch (SysException e) {
		resp.sendRedirect("error.html");
	}
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
