package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TArticle;
import com.zs.pms.po.TChannel;
import com.zs.pms.po.TUser;
import com.zs.pms.service.ArticleService;
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryArticle;
import com.zs.pms.vo.QueryUser;

/**
 * 文章控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class ArticleController {

	@Autowired
	ArticleService as;
	@Autowired
	ChannelService cs;

	@RequestMapping("/article/list.do") // 二级URL
	public String list(QueryArticle query, ModelMap map, String page) {
		// page为空
		if (page == null || "".equals(page)) {
			page = "1";// 默认第一页
		}

		// 带回分页数据
		map.addAttribute("LIST", as.queryByPage(query, Integer.parseInt(page)));
		// 带回总页数
		map.addAttribute("PAGECOUNT", as.queryPageCount(query));
		// 带回当前页数
		map.addAttribute("PAGE", page);
		// 回带查询条件
		map.addAttribute("QUERY", query);
		// 返回user/list.jsp
		return "article/list";
	}

	/**
	 * 新增时获取栏目
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/article/toadd.do")
	public String toadd(ModelMap map) {
		// 获得一级栏目列表
		List<TChannel> list1 = cs.queryByPid(0);
		// 数据回带页面
		map.addAttribute("CLIST1", list1);
		// 获得默认一级栏目下的二级栏目
		List<TChannel> list2 = cs.queryByPid(list1.get(0).getId());
		map.addAttribute("CLIST2", list2);

		return "article/add";

	}

	/**
	 * 以ajax方式响应 方法返回为string时，直接返回文本 方法返回为对象时，返回json格式，springMVC自动调用JSONArray
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/article/getchannel.do")
	@ResponseBody
	public List<TChannel> getChannel(int pid) {
		List<TChannel> list = cs.queryByPid(pid);
		return list;
	}

	/**
	 * 新增
	 * 
	 * @param article
	 * @param map
	 * @return
	 */
	@RequestMapping("/article/add.do")
	public String add(TArticle article, ModelMap map,HttpSession session) {
		try {
			// 获得session中的用户信息
			TUser cuser = (TUser) session.getAttribute("USER");
			article.setCreator(cuser.getId());
			as.insert(article);
			// 跳到指定URL上，不需要传参
			return "redirect:list.do";
		} catch (BusinessException e) {
			map.addAttribute("ERROR", e.getErrMsg());
			// 执行方法，传参
			return this.toadd(map);
		}

	}

	/**
	 * 删除一条
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/article/delete.do")
	public String delete(int id) {
		try {
			// 调用service层删除一条的方法
			as.delete(id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 删除成功后刷新列表
		return "redirect:list.do";

	}

	/**
	 * 删除多条
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/article/deletes.do")
	public String deletes(int[] ids) {
		try {
			as.deleteByIds(ids);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:list.do";
	}

	/**
	 * 修改时显示的页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/article/get.do")
	public String get(int id, ModelMap map) {
		//调用service层查询的方法
		TArticle article = as.queryById(id);
		//将查询到的数据回带页面
		map.addAttribute("ARTICLE", article);
		// 获得一级栏目列表
		List<TChannel> list1 = cs.queryByPid(0);
		// 数据回带页面
		map.addAttribute("CLIST1", list1);
		// 获得默认一级栏目下的二级栏目
		List<TChannel> list2 = cs.queryByPid(list1.get(0).getId());
		map.addAttribute("CLIST2", list2);

		return "article/update";
	}
	
	/**
	 * 修改
	 * @param article
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/article/update.do")
	public String update(TArticle article,ModelMap map,HttpSession session){
		//获得session中user的信息
		TUser cuser = (TUser) session.getAttribute("USER");
		article.setUpdator(cuser.getId());
		
		try {
			as.update(article);
			return "redirect:list.do";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			map.addAttribute("ERROR", e.getErrMsg());
			return get(article.getId(),map);
		}
		
		
		
		
	}


}
