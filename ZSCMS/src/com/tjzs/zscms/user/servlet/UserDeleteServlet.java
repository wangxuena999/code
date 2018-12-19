package com.tjzs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.service.UserService;
/**
 *删除用户信息
 */
public class UserDeleteServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//获取页面的id
	String id=req.getParameter("id");
	//创建逻辑层的对象
	UserService us=new UserService();
	
	try {
		//调用删除的方法
		us.deleteUser(Integer.parseInt(id));
		//重定向
		resp.sendRedirect("userlist.do");
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SysException e) {
		//重定向
		resp.sendRedirect("userlist.do");
	}
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
