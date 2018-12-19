package com.tjzs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;
/**
 * 检查用户名和邮箱是否唯一
 * */
public class ChkExistServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
			//获取ajax请求携带的type参数，用于区分是检查用户名还是邮箱的唯一性
			String type=req.getParameter("type");
			//创建业务逻辑层
			UserService us=new UserService();
			//定义User对象
			UserBean user=null;
			try {
			switch (type) {
			case "1"://检查用户名是否唯一
				//获取输入的用户名
				String loginname=req.getParameter("loginname");
				//调用检查用户名是否唯一的方法
				user=us.queryUserByLoginname(loginname);
				//如果为空
				if(user==null){//表示没有重复
					//系统响应为用户名添加成功
					resp.getWriter().write("true");
				}else{
					//否则失败
					resp.getWriter().write("false");
				}
				break;
			case "2"://检查邮箱是否唯一
				//获取输入的邮箱
				String email=req.getParameter("email");
				//调用邮箱是否重复的方法
				user=us.queryUserByEmail(email);
				//如果返回的是空
				if(user==null){//表示没有重复
					//系统响应为邮箱添加成功
					resp.getWriter().write("true");
				}else{
					//否则失败
					resp.getWriter().write("false");
				}
				break;
				}
			} catch (SysException e) {
				
				e.printStackTrace();
			}
}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
