package com.tjzs.zscms.channel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.channel.service.ChannelService;
import com.tjzs.zscms.exception.SysException;

public class DeleteChannelsServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// 创建逻辑层对象
	ChannelService cs=new ChannelService();
	//获取数组的id
	String ids=req.getParameter("idss");
	//拆分
	String[] id=ids.split(",");
	
	try {
		//遍历数组
		for (int i = 0; i < id.length; i++) {
			//调用删除方法
			cs.deleteChannel(Integer.parseInt(id[i]));
			}
			//重定向
			resp.sendRedirect("channellist.do");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			
		} catch (SysException e) {
			e.printStackTrace();
			//重定向
			resp.sendRedirect("channellist.do");
		}
	
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
