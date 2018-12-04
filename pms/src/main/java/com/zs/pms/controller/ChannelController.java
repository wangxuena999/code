package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TArticle;
import com.zs.pms.po.TChannel;
import com.zs.pms.po.TUser;
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryChannel;


/**
 * 栏目控制器
 * @author Administrator
 *
 */
@Controller
public class ChannelController {

	@Autowired
	ChannelService cs;
	
	/**
	 * 查询
	 * @param query
	 * @param map
	 * @param page
	 * @return
	 */
	@RequestMapping("/channel/list.do") // 二级URL
	public String list(QueryChannel query, ModelMap map, String page) {
		// page为空
		if (page == null || "".equals(page)) {
			page = "1";// 默认第一页
		}
		// 带回分页数据
		map.addAttribute("LIST", cs.queryByPage(query, Integer.parseInt(page)));
		// 带回总页数
		map.addAttribute("PAGECOUNT", cs.queryPageCount(query));
		// 带回当前页数
		map.addAttribute("PAGE", page);
		// 回带查询条件
		map.addAttribute("QUERY", query);
		// 返回user/list.jsp
		return "channel/list";
	}
	
	/**
	 * 新增时的显示页面
	 * @return
	 */
	@RequestMapping("/channel/toadd.do")
	public String toadd(ModelMap map){
		//获得上级栏目列表
		List<TChannel> list1=cs.queryByPid(0);
		//数据回带
		map.addAttribute("CLIST1", list1);
		return "channel/add";

	}
	
	/**
	 * 以ajax方式响应 方法返回为string时，直接返回文本 方法返回为对象时，返回json格式，springMVC自动调用JSONArray
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/channel/getchannel.do")
	@ResponseBody
	public List<TChannel> getChannel(int pid) {
		List<TChannel> list = cs.queryByPid(pid);
		return list;
	}
	
	@RequestMapping("/channel/add.do")
	public String add(TChannel channel,ModelMap map,HttpSession session){
		//获得session中的用户信息
		TUser cuser = (TUser) session.getAttribute("USER");
		try {
			cs.insert(channel);
			// 跳到指定URL上，不需要传参
			return "redirect:list.do";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			map.addAttribute("ERROR", e.getErrMsg());
			return this.toadd(map);
		}
		
		
	}
	
	/**
	 * 修改时显示的页面
	 * @return
	 */
	@RequestMapping("/channel/get.do")
	public String get(int id,ModelMap map){
		TChannel channel = cs.queryById(id);
		map.addAttribute("CHANNEL", channel);
		//获得一级栏目
		List<TChannel> list = cs.queryByPid(0);
		//数据回带页面
		map.addAttribute("LIST", list);
		return "channel/update";
		
	}
	
	/**
	 * 修改
	 * @param article
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/channel/update.do")
	public String update(TChannel channel,ModelMap map,HttpSession session){
		//获得session中user的信息
		TUser cuser = (TUser) session.getAttribute("USER");
		
		try {
			cs.update(channel);
			return "redirect:list.do";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			map.addAttribute("ERROR", e.getErrMsg());
			return get(channel.getId(),map);
		}

	}
	
	
	/**
	 * 删除一条
	 * @param id
	 * @return
	 */
	@RequestMapping("/channel/delete.do")
	public String delete(int id,ModelMap map,QueryChannel query,String page){
		try {
			cs.delete(id);
			return "redirect:list.do";
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			map.addAttribute("ERROR", e.getErrMsg());
			return this.list(query,map,page);
		}
		
		
	}
	
	/**
	 * 删除多条
	 * @param ids
	 * @return
	 */
	@RequestMapping("/channel/deletes.do")
	public String deleteByIds(int[] ids){
		try {
			cs.deleteByIds(ids);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:list.do";
		
	}
	
	
}
