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
 *用户修改
 */
public class UserUpdateServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//创建UserBean对象，用来给用户重新赋值
	UserBean user=new UserBean();
	//将信息封装到bean中
	int id=Integer.parseInt(req.getParameter("id"));
	user.setId(id);
	user.setLoginname(req.getParameter("loginname"));
	user.setRealname(req.getParameter("realname"));
	//如果性别是1，赋值为男，否则是女
	if (req.getParameter("sex").equals("1")) {
		user.setSex("男");
	}else{
		user.setSex("女");
	}
	//user.setSex(req.getParameter("sex"));
	user.setBirthday(req.getParameter("birthday"));
	user.setEmail(req.getParameter("email"));
	user.setDept(Integer.parseInt(req.getParameter("dept")));
	user.setEnabled(Integer.parseInt(req.getParameter("enabled")));
	//创建逻辑层对象
	UserService us=new UserService();
	
	try {
		//调用修改的方法
		us.updateUser(user);
		//转发
		req.getRequestDispatcher("userlist.do").forward(req, resp);
	} catch (SysException e) {
		//修改失败
		req.setAttribute("id", id);
		req.setAttribute("msg", e.getErrMsg());
		req.getRequestDispatcher("userget.do").forward(req, resp);
	}
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
