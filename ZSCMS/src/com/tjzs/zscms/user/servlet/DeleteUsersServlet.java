package com.tjzs.zscms.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.service.UserService;

/*
 * 批量删除
 */
public class DeleteUsersServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 创建逻辑层对象
		UserService us = new UserService();
		// 获取id数组
		String ids = req.getParameter("idss");
		// 拆分
		String[] id = ids.split(",");
		
		try {
			for (int i = 0; i < id.length; i++) {
				// 调用删除方法
				us.deleteUser(Integer.parseInt(id[i]));
			}
			// 重定向
			resp.sendRedirect("userlist.do");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SysException e) {
			e.getErrMsg();
			resp.sendRedirect("userlist.do");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
