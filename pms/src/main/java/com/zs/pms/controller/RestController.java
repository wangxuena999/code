package com.zs.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;

/**
 * 提供restful服务
 * @author Administrator
 *
 */
@Controller
public class RestController {

	@Autowired
	UserService us;
	//{id} 占位，实际为/userinfo/3084.do
	@RequestMapping(value="/userinfo/{id}.do")
	@ResponseBody //json返回
	public TUser queryById(@PathVariable("id") int id){
		
		return us.queryById(id);
		
	}
	
	
}
