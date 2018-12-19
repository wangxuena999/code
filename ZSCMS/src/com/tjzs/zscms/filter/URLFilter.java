package com.tjzs.zscms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tjzs.zscms.user.bean.UserBean;

public class URLFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 拦截器的作用：如果没有登录不允许访问其他页面，登录成功允许
		// 如何判断登录成功
		// 1.获取访问的URL路径
		// 2.获取session中的用户信息来判断是否登录成功，如果成功放行，否则跳转到登录页面
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 获取请求的路径  .html.do.jsp
		String url = req.getServletPath();
		// 拦截路径中所有的.do请求，不包括login.do
		if (url.contains(".do") && (!url.contains("login.do"))) {
			// 获取session对象
			HttpSession session = req.getSession();
			// 获取登录信息进行判断
			UserBean user = (UserBean) session.getAttribute("userBean");
			//如果session中内容为空，则重定向到登录页面
			if (user == null) {
				resp.sendRedirect("login.jsp");
				return;
			}
		}
		// 拦截路径中所有的.jsp请求，不包括login.jsp
		if (url.contains(".jsp") && (!url.contains("login.jsp"))&&(!url.contains("kaptcha.jsp"))) {
			// 获取session对象
			HttpSession session = req.getSession();
			// 获取登录信息进行判断
			UserBean user = (UserBean) session.getAttribute("userBean");
			//如果session中内容为空，则重定向到登录页面
			if (user == null) {
				resp.sendRedirect("login.jsp");
				return;
			}
		}
		// 拦截路径中所有的.html请求，不包括error.html
		if (url.contains(".html") && (!url.contains("error.html"))) {
			// 获取session对象
			HttpSession session = req.getSession();
			// 获取登录信息进行判断
			UserBean user = (UserBean) session.getAttribute("userBean");
			//如果session中内容为空，则重定向到登录页面
			if (user == null) {
				resp.sendRedirect("login.jsp");
				return;
			}
		}
		//继续执行过滤器链的剩余部分
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
