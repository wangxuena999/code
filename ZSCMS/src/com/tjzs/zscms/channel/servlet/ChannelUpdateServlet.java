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
 *修改栏目信息
 */
public class ChannelUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建channelbean对象，用于给栏目信息赋值，即修改栏目信息
		ChannelBean channel = new ChannelBean();
		// 从页面获取修改栏目的id
		String id = req.getParameter("id");
		// 将从页面获取的栏目信息封装到channelbean中
		channel.setId(Integer.parseInt(id));
		channel.setCname(req.getParameter("cname"));
		channel.setPid(Integer.parseInt(req.getParameter("pid")));
		channel.setLev(Integer.parseInt(req.getParameter("lev")));
		channel.setIsleaf(Integer.parseInt(req.getParameter("isleaf")));
		channel.setSort(1);
		if (req.getParameter("isleaf").equals("1")) {
			channel.setIsleafTxt("是");
		}else{
			channel.setIsleafTxt("否");
		}
		
		// 创建栏目逻辑层对象
		ChannelService cs = new ChannelService();
		try {
			// 调用栏目逻辑层修改栏目方法
			cs.updateChannel(channel);
			// 转发到栏目信息页面，传递req对象和resp对象
			req.getRequestDispatcher("channellist.do").forward(req, resp);

		} catch (SysException e) {
			// 修改栏目失败,转发到修改页面
			req.setAttribute("id", id);
			req.getRequestDispatcher("channelget.do").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
