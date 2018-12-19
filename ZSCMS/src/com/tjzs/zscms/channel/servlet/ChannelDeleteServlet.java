package com.tjzs.zscms.channel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.channel.service.ChannelService;
import com.tjzs.zscms.exception.SysException;
/**
 *删除栏目信息
 */
public class ChannelDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 从栏目页面获取需要删除的广告id
		String id = req.getParameter("id");
		// 创建栏目逻辑层对象
		ChannelService cs = new ChannelService();

		try {
			// 调用栏目逻辑层删除栏目方法
			int result = cs.deleteChannel(Integer.parseInt(id));
			// 判断栏目逻辑层删除栏目结果是否为0，不为0删除成功，0删除失败
			if (result > 0) {
				// 删除栏目信息成功，页面重定向到广告信息展示页面
				resp.sendRedirect("channellist.do");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SysException e) {
			resp.sendRedirect("error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
