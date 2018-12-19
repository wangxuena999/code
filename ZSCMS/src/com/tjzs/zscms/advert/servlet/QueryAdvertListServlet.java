package com.tjzs.zscms.advert.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.advert.bean.AdvertBean;
import com.tjzs.zscms.advert.service.AdvertService;
import com.tjzs.zscms.exception.SysException;

public class QueryAdvertListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建逻辑层对象
		AdvertService as = new AdvertService();

		try {
			// 定义每页的查询条数
			int size = 5;
			// 获取当前页数
			String p = req.getParameter("currentPage");
			//设置页数为首页
			int page = 1;
			//如果新增，会显示空，则直接令p为空时，显示到首页
			if (p == null) {
				page = 1;
			} else {
				page = Integer.parseInt(p);
			}
			//获取当前页数，把信息存到list集合中
			List<AdvertBean> adverts = as.queryByPage(page,size);
			//获取总页数
			int pageCount=as.queryPageCount(size);
			//将当前页面继续返回到页面中
			req.setAttribute("currentPage", page);
			//将总页数返回到页面中
			req.setAttribute("pageCount", pageCount);
			// 将广告集合存入req作用域
			req.setAttribute("adverts", adverts);
			// 转发到广告查询页面，在查询页面显示数据
			req.getRequestDispatcher("advert/list.jsp").forward(req, resp);
		} catch (SysException e) {
			// 若数据库错误则重定向到错误页面
			resp.sendRedirect("error.html");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
