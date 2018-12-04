package com.zs.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TDept;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DeptService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

/**
 * 用户控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService us;// 用户服务
	@Autowired
	DeptService ds;// 部门服务

	@RequestMapping("/user/list.do") // 二级URL
	public String list(QueryUser query, ModelMap map, String page) {
		// page为空
		if (page == null || "".equals(page)) {
			page = "1";// 默认第一页
		}
		// 带回分页数据
		map.addAttribute("LIST", us.queryByPage(query, Integer.parseInt(page)));
		// 带回总页数
		map.addAttribute("PAGECOUNT", us.queryPageCount(query));
		// 带回当前页数
		map.addAttribute("PAGE", page);
		// 回带查询条件
		map.addAttribute("QUERY", query);
		// 返回user/list.jsp
		return "user/list";
	}

	/**
	 * 新增时获取的部门
	 * @param map
	 * @return
	 */
	@RequestMapping("/user/toadd.do")
	public String toadd(ModelMap map) {
		// 获得一级部门列表
		List<TDept> list1 = ds.queryByPid(0);
		//数据回带页面
		map.addAttribute("DLIST1", list1);
		// 获得默认一级部门下的二级列表
		List<TDept> list2 = ds.queryByPid(list1.get(0).getId());
		map.addAttribute("DLIST2", list2);
		return "user/add";

	}

	/**
	 * 以ajax方式响应 方法返回为string时，直接返回文本 方法返回为对象时，返回json格式，springMVC自动调用JSONArray
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/user/getdept.do")
	@ResponseBody //异步操作
	public List<TDept> getDept(int pid) {
		//调用通过上级部门获取部门列表的方法
		List<TDept> list = ds.queryByPid(pid);
		return list;

	}

	/**
	 * 新增
	 * @param user
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/add.do")
	public String add(TUser user, ModelMap map, HttpSession session) {
		
		try {
			// 获得session中的用户信息
			TUser cuser = (TUser) session.getAttribute("USER");
			user.setCreator(cuser.getId());// 创建人
			user.setIsenabled(1);
			us.insert(user);
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
	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		try {
			us.delete(id);

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:list.do";

	}

	/**
	 * 删除多条
	 * 
	 * @param ids
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping("/user/deletes.do")
	public String deletes(int[] ids) {
		try {
			us.deleteByIds(ids);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:list.do";
		
		

	}
	
	/**
	 * 获得修改页面
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping("/user/get.do")
	public String get(int id, ModelMap map) {
		TUser user = us.queryById(id);
		map.addAttribute("USER", user);
		// 获得一级部门列表
		List<TDept> list1 = ds.queryByPid(0);
		map.addAttribute("DLIST1", list1);
		// 获得默认一级部门下的二级列表
		List<TDept> list2 = ds.queryByPid(user.getDept().getPid());
		map.addAttribute("DLIST2", list2);
		return "user/update";

	}
	
	/**
	 * 修改
	 * @param user
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/user/update.do")
	public String update(TUser user,ModelMap map,HttpSession session){
		//获得session中user的信息
		TUser cuser = (TUser) session.getAttribute("USER");
		user.setUpdator(cuser.getId());
		try {
			us.update(user);
			return "redirect:list.do";
		} catch (BusinessException e) {
			map.addAttribute("ERROR", e.getErrMsg());
			return get(user.getId(),map);
		}
		
		
	}
}
