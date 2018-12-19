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
/**
 *新增广告
 */
public class AdvertAddServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//获取新增广告表单中的数据，将数据封装到bean包
	//创建AdvertBean对象
	AdvertBean advert=new AdvertBean();
	//封装
	advert.setTitle(req.getParameter("title"));
	advert.setContent(req.getParameter("content"));
	advert.setCrtime(req.getParameter("crtime"));
	System.out.println(req.getParameter("crtime"));
	//advert.setCrman(req.getParameter("crman"));
	//创建session对象，用于获取已经登录的用户id，因为新增的广告crman等于登录用户名loginname
	HttpSession session=req.getSession();
	//从session中获取登录的用户信息
	//（登录的用户信息已经在用户登录时被封装成userbean存放到session中）
	//返回类型Object，强转为UserBean类型，括号中的userBean是登录是存放到session中
	UserBean users=(UserBean) session.getAttribute("userBean");
	//将从登录用户信息中获取的登录用户名赋值给新增的广告信息中的创建人，并进行封装
	System.out.println(users.getLoginname());
	advert.setCrman(users.getLoginname());
	
	//创建service对象
	AdvertService as=new AdvertService();
	try {
		//如果新增成功，就转发到广告查询页面，进行展示
		if(as.insertAdvert(advert)>0){
			//转发
			req.getRequestDispatcher("advertlist.do").forward(req, resp);
		}
	} catch (SysException e) {
		//重定向
		resp.sendRedirect("advert/add.jsp");
	}
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
