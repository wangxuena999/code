	package com.zs.pms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.RedisService;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.DateUtil;
import com.zs.pms.vo.QueryUser;

@Controller
public class FrameController {
	@Autowired //注入业务层
	UserService us;
	
	@Autowired
	RedisService rs;
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/tologin.do")
	public String tologin(){
		return"login";
	}
	
	/**
	 * 检测登录
	 * @param query 登录名和密码
	 * @param idenfitying 验证码
	 * @param map 回带数据到页面
	 * @param session 返回页面
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(QueryUser query,String idenfitying,ModelMap map,HttpSession session) {
		try {
			//使用TUser类型的变量接收所查到的信息
			TUser user = us.chkLogin(query);
			//获取图片中的验证码
			String code=(String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			//判断
			if(!idenfitying.equals(code)){
				map.addAttribute("ERROR", "验证码输入有误，请重新输入");
				return "login";
			}

			//使用List<TPermission>类型的变量接收该用户的一级权限
			List<TPermission> permissions = user.getMenu();
			//通过session将一级权限传到页面上
			session.setAttribute("PERMISSIONS", permissions);
			//通过session将用户信息传到页面上
			session.setAttribute("USER", user);
			//将当前日期传到页面
			session.setAttribute("TODAY", DateUtil.getDateToString(new Date(),"yyyy-MM-dd"));
			//登录成功，将码表取出后写入Redis中
			rs.setTCodes("F");//材质
			rs.setTCodes("C");//颜色
			rs.setTCodes("S");//尺码
			rs.setBrands();//品牌
			rs.setTypes(1);//上衣类型的子类别
			
			return "main";
		} catch (BusinessException e) {
			//将异常信息回带回页面
			map.addAttribute("ERROR", e.getErrMsg());
			//返回登录页面
			return "login";
		} catch (Exception e1) {
			//返回错误页面
			e1.printStackTrace();
			return "error";
		}
	
	}
	
	/**
	 * 左侧菜单
	 * @return
	 */
	@RequestMapping("/toleft.do")
	public String toLetf(){
		return "left";
	}
	/**
	 * 右侧页面
	 * @return
	 */
	@RequestMapping("/toright.do")
	public String toRight(){
		return "right";
	}
	/**
	 * 去top页
	 * @return
	 */
	@RequestMapping("/totop.do")
	public String toTop(){
		return "top";
	}
	
}
