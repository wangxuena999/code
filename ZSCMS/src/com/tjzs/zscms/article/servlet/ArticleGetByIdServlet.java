package com.tjzs.zscms.article.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.article.service.ArticleService;
import com.tjzs.zscms.article.service.ChannelService;
import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;

public class ArticleGetByIdServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	int id=Integer.parseInt(req.getParameter("id"));
	//创建文章逻辑层对象
	ArticleService as=new ArticleService();
	//创建栏目逻辑层对象
	ChannelService cs=new ChannelService();
	
	
	try {
		//调用文章逻辑层通过文章id查询文章信息方法
		ArticleBean article=as.queryArticleById(id);
		//调用栏目逻辑层查询所有栏目的方法
		List<ChannelBean> channels=cs.queryAll();
		//将文章信息存放到req对象的作用域中
		req.setAttribute("article", article);
		//将文章信息存放到req对象的作用域中
		req.setAttribute("channels", channels);
		//转发到修改文章页面，传递req对象和resp对象
		req.getRequestDispatcher("article/update.jsp").forward(req, resp);
		
	} catch (SysException e) {
		//修改文章失败，将页面重定向到错误页面
		resp.sendRedirect("error.html");
	}
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
