package com.tjzs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.service.UserService;
/**
 * 新增用户
 * */
public class UserAddServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//获取新增用户页面中表单的数据，将数据封装到bean包
	//创建UserBean对象
	UserBean user=new UserBean();
	user.setLoginname(req.getParameter("loginname"));
	user.setPassword(req.getParameter("password"));
	user.setRealname(req.getParameter("realname"));
	//user.setSex(req.getParameter("sex"));
	if(req.getParameter("sex").equals("1")){
		user.setSex("男");
	}else{
		user.setSex("女");
	}
	user.setBirthday(req.getParameter("birthday"));
	user.setEmail(req.getParameter("email"));
	user.setDept(Integer.parseInt(req.getParameter("dept")));
	user.setEnabled(1);//新增用户默认可用
	//谁登录谁就是创建人，我们把创建人已经放入了session中，所有要从session中获取信息
	//创建session
	HttpSession session = req.getSession();
	//从session中获取登录的用户信息,进行强转
	UserBean users=(UserBean) session.getAttribute("userBean");
	//创建人与用户id一致，所以将用户登录的id赋值
	user.setCreatman(users.getId());
	//确认密码的数据
	//String repwd=req.getParameter("repwd");
	//判断两次密码是否一致
	/*if(!user.getPassword().equals(repwd)){
		//不一致则显示重新输入，转发到新增页面，使用dept.do是因为在它里面有跳转新增的页面，部门也存在
		//如果转发到useradd.do,则部门就不存在了
		req.setAttribute("pwdMsg", "两次密码不一致，请重新输入");
		req.getRequestDispatcher("dept.do").forward(req, resp);
		//如果一致，跳出这个方法
		return;
	}*/
	//创建service层对象
	UserService us=new UserService();
	try {
		//如果新增成功，就转发到用户查询页面，进行展示
		if(us.insertUser(user)>0){
			//转发到用户查询页面
			req.getRequestDispatcher("userlist.do").forward(req, resp);
		}
	} catch (BusinessException e) {
		//若新增失败,设置错误提示信息
		req.setAttribute("msg", e.getErrMsg());
		//转发到新增页面
		req.getRequestDispatcher("dept.do").forward(req, resp);
	} catch (SysException e) {
		//数据库错误则重定向到错误页面
		resp.sendRedirect("error.html");
	}
	
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
