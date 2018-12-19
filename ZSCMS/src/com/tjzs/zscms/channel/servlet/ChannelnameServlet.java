package com.tjzs.zscms.channel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.channel.bean.ChannelnameBean;
import com.tjzs.zscms.channel.service.ChannelnameService;
import com.tjzs.zscms.exception.SysException;

public class ChannelnameServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//创建逻辑层对象
		ChannelnameService cns=new ChannelnameService();
		try {
			//调用查询所有栏目信息的方法放入集合中
			List<ChannelnameBean> channelnames = cns.queryAll();
			//将信息放入req对象中
			req.setAttribute("channelnames", channelnames);
			//转发到新增页面
			req.getRequestDispatcher("channel/add.jsp").forward(req, resp);
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
