package com.tjzs.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.article.service.ArticleService;
import com.tjzs.zscms.exception.SysException;

public class ArticleDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 从文章页面获取需要删除的广告id
		String id = req.getParameter("id");
		// 创建文章逻辑层对象
		ArticleService as = new ArticleService();

		try {
			// 调用文章逻辑层删除文章方法
			int result = as.deleteArticle(Integer.parseInt(id));
			// 判断删除文章结果是否为0，不为0删除成功，0删除失败
			if (result > 0) {
				// 删除文章信息成功，页面重定向到文章信息展示页面
				resp.sendRedirect("articlelist.do");
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SysException e) {
			// 删除文章信息失败，页面重定向到文章信息展示页面
			resp.sendRedirect("articlelist.do");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
