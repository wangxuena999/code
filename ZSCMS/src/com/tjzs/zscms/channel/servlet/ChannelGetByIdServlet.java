package com.tjzs.zscms.channel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.channel.bean.ChannelnameBean;
import com.tjzs.zscms.channel.service.ChannelService;
import com.tjzs.zscms.channel.service.ChannelnameService;
import com.tjzs.zscms.exception.SysException;
/**
 *通过ID查询栏目信息
 */
public class ChannelGetByIdServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 从页面获取栏目id
		int id = Integer.parseInt(req.getParameter("id"));
		// 创建栏目逻辑层对象
		ChannelService cs = new ChannelService();
		ChannelnameService cns = new ChannelnameService();
		try {
			// 调用栏目逻辑层根据栏目id查询栏目信息方法
			ChannelBean channel = cs.queryChannelById(id);
			//调用查询全部信息的方法，将信息放入集合
			List<ChannelnameBean> channelnames = cns.queryAll();
			// 将栏目信息存放到req对象的作用域中
			req.setAttribute("channel", channel);
			req.setAttribute("channelnames", channelnames);
			// 转发到修改栏目页面，传递req对象和resp对象
			req.getRequestDispatcher("channel/update.jsp").forward(req, resp);

		} catch (SysException e) {
			// 修改栏目失败，重定向到错误页面
			resp.sendRedirect("error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
