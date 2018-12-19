package com.tjzs.zscms.advert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.advert.service.AdvertService;
import com.tjzs.zscms.exception.SysException;

public class AdvertDeleteServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//获取页面的id
	String id = req.getParameter("id");
	//创建逻辑层对象
	AdvertService as=new AdvertService();
	
	try {
		//调用删除广告信息的方法
		as.deleteAdvert(Integer.parseInt(id));
		//删除完重定向
		resp.sendRedirect("advertlist.do");
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SysException e) {
		//重定向
		resp.sendRedirect("advertlist.do");
	}
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
