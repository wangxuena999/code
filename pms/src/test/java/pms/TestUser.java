package pms;

import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TDept;
import com.zs.pms.po.TPermission;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

/**
 * 测试用户表
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TestUser {

	@Autowired //按类型装配service
	UserService us;
	
	public void testLogin(){
		//有多个参数时，要封装到对象中
		QueryUser query=new QueryUser();
		//输入用户名和密码
		query.setLoginname("test7");
		query.setPassword("test1");
		
		try {
			//调用service层登录的方法
			TUser user = us.chkLogin(query);
			//输出信息
			System.out.println(user.getRealname()+"、"+user.getDept().getDname());
			for(TPermission per:user.getPermissions()){
				System.out.println(per.getPname()+","+per.getUrl());
			}
			System.out.println("-------------整理后--------------");
			
			for(TPermission per1:user.getMenu()){
				System.out.println(per1.getPname());
				for(TPermission per2:per1.getChildren()){
					System.out.println("\t"+per2.getPname());
				}
			}
			
		
		} catch (BusinessException e) {
			//抛出异常信息
			System.out.println(e.getErrMsg());
		} catch (Exception e1) {
			//系统异常
			e1.printStackTrace();
		}
	}
	
	
	
	public void testQuery(){
		//有多个参数时，要封装到对象中
		QueryUser query=new QueryUser();
		//输入用户名和密码
		query.setLoginname("test1");
		query.setPassword("5A105E8B9D40E1329780D62EA2265D8A");
		query.setIsenabled(1);
		query.setPage(2);
		
		us.queryByCon(query);
		
	}
	
	
	public void testId(){
		TUser user=us.queryById(1005);
		System.out.println(user.getRealname());
	}
	
	
	public void testDels(){
		int[] ids={3113,3114};
		try {
			us.deleteByIds(ids);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testDel(){
		try {
			us.delete(1006);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testUpdate(){
		TUser user=new TUser();
		user.setId(3088);
		user.setBirthday(new Date());
		//部门
		TDept dept=new TDept();
		dept.setId(6);
		user.setDept(dept);
		
		user.setEmail("qwe@123.com");
		user.setIsenabled(-1);
		user.setPassword("1234");
		user.setPic("aaa.jpg");
		user.setRealname("测试员3");
		user.setSex("女");
		user.setUpdator(1001);
		
		try {
			us.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void testAdd(){
		TUser user=new TUser();
		user.setBirthday(new Date());
		//部门
		TDept dept=new TDept();
		dept.setId(7);
		user.setDept(dept);
		
		user.setLoginname("test4");
		user.setEmail("qwe@123.com");
		user.setPassword("1234");
		user.setPic("aaa.jpg");
		user.setRealname("测试员4");
		user.setSex("女");
		user.setCreator(1000);
		
		try {
			us.insert(user);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testPage(){
		QueryUser query=new QueryUser();
		query.setSex("女");
		System.out.println("当前总页数"+us.queryPageCount(query));
		for (TUser user : us.queryByPage(query, 2)) {
			System.out.println(user.getRealname());
		}
	}
	
	
	
}
