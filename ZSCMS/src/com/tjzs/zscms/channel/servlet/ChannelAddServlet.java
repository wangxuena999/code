package com.tjzs.zscms.channel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.channel.service.ChannelService;
import com.tjzs.zscms.exception.SysException;
/**
 * 新增栏目
 * */
public class ChannelAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		// 创建channelbean对象，用于封装栏目信息
		ChannelBean channel = new ChannelBean();
		// 将栏目信息封装到channelbean中
		channel.setCname(req.getParameter("cname"));
		channel.setPid(Integer.parseInt(req.getParameter("pid")));
		channel.setLev(Integer.parseInt(req.getParameter("lev")));
		channel.setIsleaf(Integer.parseInt(req.getParameter("isleaf")));
		channel.setSort(1);

		// 创建栏目逻辑层对象
		ChannelService cs = new ChannelService();

		try {
			// 调用栏目逻辑层新增栏目方法
			if (cs.insertChannel(channel) > 0) {
				// 页面转发到栏目信息页面，传递req对象和resp对象
				req.getRequestDispatcher("channellist.do").forward(req, resp);
			}
		} catch (SysException e) {
			// 新增栏目失败，重定向到新增页面
			resp.sendRedirect("channel/add.jsp");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
