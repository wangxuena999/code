package com.tjzs.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.advert.service.AdvertService;
import com.tjzs.zscms.article.service.ArticleService;
import com.tjzs.zscms.exception.SysException;
/*
 * 批量删除
 */
public class DeleteArticlesServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// 创建逻辑层对象
	ArticleService as=new ArticleService();
	//获取id数组
	String ids=req.getParameter("idss");
	// 拆分
	String[] id = ids.split(",");
	try {
		for (int i = 0; i < id.length; i++) {
			// 调用删除方法
			as.deleteArticle(Integer.parseInt(id[i]));
		}
		// 重定向
		resp.sendRedirect("articlelist.do");
	} catch (NumberFormatException e) {
		e.printStackTrace();
	} catch (SysException e) {
		e.getErrMsg();
		resp.sendRedirect("articlelist.do");
	}
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
