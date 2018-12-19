package com.tjzs.zscms.article.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.article.service.ArticleService;
import com.tjzs.zscms.exception.SysException;

public class QueryArticleListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建逻辑层对象
		ArticleService as = new ArticleService();

		try {
			// 定义每页的查询条数
			int size = 5;
			// 获取当前页数
			String p = req.getParameter("currentPage");
			// 设置页数为首页
			int page = 1;
			// 如果新增，会显示空，则直接令p为空时，显示到首页
			if (p == null) {
				page = 1;
			} else {
				page = Integer.parseInt(p);
			}
			// 调用查询所有文章信息的方法，把信息存到list集合
			List<ArticleBean> articles = as.queryByPage(page,size);
			//获取总页数
			int pageCount=as.queryPageCount(size);
			//将当前页面继续返回到页面中
			req.setAttribute("currentPage", page);
			//将总页数返回到页面中
			req.setAttribute("pageCount", pageCount);
			// 将文章信息存到req作用域中
			req.setAttribute("articles", articles);
			// 转发
			req.getRequestDispatcher("article/list.jsp").forward(req, resp);
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
