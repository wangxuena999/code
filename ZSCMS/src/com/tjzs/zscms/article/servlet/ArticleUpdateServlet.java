package com.tjzs.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.article.service.ArticleService;
import com.tjzs.zscms.exception.SysException;

public class ArticleUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建articletbean对象，用于给文章信息赋值，即修改文章信息
		ArticleBean article = new ArticleBean();
		// 从页面获取要修改的文章id
		String id = req.getParameter("id");
		// 将从页面获取的文章信息封装到articlebean中
		article.setId(Integer.parseInt(id));
		article.setTitle(req.getParameter("title"));
		article.setContent(req.getParameter("content"));
		article.setAuthor(req.getParameter("author"));
		article.setCrtime(req.getParameter("crtime"));
		article.setChannel(Integer.parseInt(req.getParameter("channel")));
		article.setIsremod(Integer.parseInt(req.getParameter("isremod")));
		/*if (req.getParameter("isremod").equals("1")) {
			article.setIsremodTxt("推荐");
		}else{
			article.setIsremodTxt("不推荐");
		}*/
		article.setIshot(Integer.parseInt(req.getParameter("ishot")));
		/*if (req.getParameter("ishot").equals("1")) {
			article.setIshotTxt("热点");
		}else{
			article.setIshotTxt("非热点");
		}*/
		// 创建文章逻辑层对象
		ArticleService as = new ArticleService();
		try {
			// 调用文章逻辑层修改方法
			as.updateArticle(article);
			// 转发到文章信息页面，传递req对象和resp对象
			req.getRequestDispatcher("articlelist.do").forward(req, resp);

		} catch (SysException e) {
			// 修改文章失败
			req.setAttribute("id", id);
			req.setAttribute("errmsg", "修改文章信息失败");
			// 修改广告信息失败，重定向到错误页面
			req.getRequestDispatcher("articleget.do").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
