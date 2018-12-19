package com.tjzs.zscms.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.user.service.DeptService;
/**
 * 获取部门名称
 * */
public class DeptServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		DeptService ds=new DeptService();
		try {
			//调用查询所有部门信息的方法放入集合中
			List<DeptBean> depts = ds.queryAll();
			//将信息放入req对象中
			req.setAttribute("depts", depts);
			//转发到新增页面
			req.getRequestDispatcher("user/add.jsp").forward(req, resp);
		} catch (SysException e) {
			//数据库异常则重定向到错误页面
			resp.sendRedirect("error.html");
		}

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
