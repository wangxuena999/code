package com.tjzs.zscms.advert.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjzs.zscms.advert.bean.AdvertBean;
import com.tjzs.zscms.advert.service.AdvertService;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;

/**
 * 广告修改
 */
public class AdvertUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建AdvertBean对象，用来给广告重新赋值
		AdvertBean advert = new AdvertBean();
		//创建用户逻辑对象
		//UserService us=new UserService();
		int id = Integer.parseInt(req.getParameter("id"));
		advert.setId(id);
		advert.setTitle(req.getParameter("title"));
		advert.setContent(req.getParameter("content"));
		advert.setCrtime(req.getParameter("crtime"));
		advert.setCrman(req.getParameter("crman"));
		// 创建逻辑层对象
		AdvertService as=new AdvertService();
		
		try {
			// 调用修改的方法
			as.updateAdvert(advert);
			// 转发
			req.getRequestDispatcher("advertlist.do").forward(req, resp);
		} catch (SysException e) {
			// 修改失败
			req.setAttribute("id", id);
			req.getRequestDispatcher("advertget.do").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
