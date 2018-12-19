package com.tjzs.zscms.channel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.channel.service.ChannelService;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
/**
 *查询栏目列表
 */
public class QueryChannelListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 设置编码格式
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		// 创建栏目逻辑层对象
		ChannelService cs = new ChannelService();
		try {
			//定义每页的查询条数
			int size=5;
			//获取当前页数
			String p=req.getParameter("currentPage");
			//设置页数为首页
			int page=1;
			//如果新增，会显示空，则直接令p为空时，显示到首页
			if(p==null){
				page=1;
			}else{
				page=Integer.parseInt(p);
			}
			//获取当前页数，把信息存到list集合中
			List<ChannelBean> channels=cs.queryByPage(page,size);
			//获取总页数
			int pageCount=cs.queryPageCount(size);
			//将当前页面继续返回到页面中
			req.setAttribute("currentPage", page);
			//将总页数返回到页面中
			req.setAttribute("pageCount", pageCount);
			// 将栏目信息存放到req对象的作用域中
			req.setAttribute("channels", channels);
			// 转发到栏目信息页面，传递req对象和resp对象
			req.getRequestDispatcher("channel/list.jsp").forward(req, resp);
		} catch (SysException e) {
			// 查询失败，重定向到错误页面
			resp.sendRedirect("error.html");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
