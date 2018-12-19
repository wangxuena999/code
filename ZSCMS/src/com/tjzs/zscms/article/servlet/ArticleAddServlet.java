package com.tjzs.zscms.article.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.article.service.ArticleService;
import com.tjzs.zscms.exception.SysException;
/**
 * 新增文章
 * */
public class ArticleAddServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	//获取新增文章页面中表单的数据，将数据封装到bean包
	//创建ArticleBean对象
	ArticleBean article=new ArticleBean();
	article.setTitle(req.getParameter("title"));
	article.setContent(req.getParameter("content"));
	article.setAuthor(req.getParameter("author"));
	article.setCrtime(req.getParameter("crtime"));
	article.setChannel(Integer.parseInt(req.getParameter("channel")));
	article.setIsremod(Integer.parseInt(req.getParameter("isremod")));
	article.setIshot(Integer.parseInt(req.getParameter("ishot")));
	//创建service层对象
	ArticleService as=new ArticleService();
	try {
		//如果新增成功，就转发到文章查询页面，进行展示
		if(as.insertArticle(article)>0){
			//转发到用户查询页面
			req.getRequestDispatcher("articlelist.do").forward(req, resp);
		}
	} catch (SysException e) {
		//数据库错误则重定向到错误页面
		resp.sendRedirect("article/add.jsp");
	}
}


@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
