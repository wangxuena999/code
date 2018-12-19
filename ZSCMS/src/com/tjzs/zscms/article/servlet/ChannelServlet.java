package com.tjzs.zscms.article.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.article.service.ChannelService;
import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;

public class ChannelServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//创建逻辑层对象
	ChannelService cs=new ChannelService();
	try {
		//调用查询所有栏目信息的方法放入集合中
		List<ChannelBean> channels = cs.queryAll();
		//将信息放入req对象中
		req.setAttribute("channels", channels);
		//转发到新增页面
		req.getRequestDispatcher("article/add.jsp").forward(req, resp);
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
