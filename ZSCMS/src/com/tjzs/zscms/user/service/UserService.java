package com.tjzs.zscms.user.service;

import java.util.List;

import com.tjzs.zscms.exception.BusinessException;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.user.dao.impl.UserDao;
import com.tjzs.zscms.user.dao.impl.UserDaoImpl;

/**
 * 用户的业务逻辑处理层
 * */
public class UserService {
	//采用多态的形式，创建UserDao的对象
	UserDao ud=new UserDaoImpl();
	//判断是否有相应的用户信息，成功就返回信息，失败返回异常信息
	public UserBean userLogin(UserBean user) throws SysException, BusinessException{
		//将信息存入集合中
		List<UserBean> users=ud.login(user);
		//如果用户信息为空，则数据库中没有该用户信息
		if(user==null || users.size() == 0){
			throw new BusinessException(400, "此用户不存在");
		}
		return users.get(0);	
	}
	
	/*查询所有用户信息*/
	/**
	 * page表示查询的当前页数
	 * size：条数
	 * */
	public List<UserBean> queryByPage(int page,int size) throws SysException{
		//查询的每一页的m值：m=(page-1)*n
		int start=(page-1)*size;
		//返回每一页的数据条数
		return ud.queryByPage(start,size);
	}
	/*查询总页数*/
	public int queryPageCount(int size) throws SysException{
		//将用户的总记录转换为总页数
		int userCount=ud.queryUserCounts();
		if(userCount%size==0){//表示总页数能够整除每页查询的数据量
			//总页数
			return userCount/size;
		}else{
			//不能整除，就多加一页
			return userCount/size+1;
		}
	}
	
	/*检查用户名的唯一性*/
	public UserBean queryUserByLoginname(String loginname) throws SysException{
		//创建集合，将信息存入集合
		List<UserBean> users=ud.queryByLoginname(loginname);
		//判断如果用户不为空或长度不为0，表示重复
		if(users!=null&&users.size()!=0){
			//根据此用户名查出用户
			return users.get(0);
		}
		//根据此用户名没有查出用户
		return null;
	}
	/*检查邮箱的唯一性*/
	public UserBean queryUserByEmail(String email) throws SysException{
		//创建集合，将信息存入
		List<UserBean> users=ud.queryByEmail(email);
		//判断如果用户不为空或长度不为0，表示重复
		if(users!=null&&users.size()!=0){
			//根据此邮箱名查出邮箱
			return users.get(0);
		}
		//根据此邮箱没有查出邮箱
		return null;
	}
	
	/*新增用户*/
	public int insertUser(UserBean user) throws SysException, BusinessException{
		//如果执行到这一步，表示用户名和邮箱都不重复，创建成功，返回结果
		return ud.insertUser(user);
	}
	
	/*根据id查询用户信息*/
	public UserBean queryUserById(int id) throws SysException{
		//将查询到的信息存入集合
		List<UserBean> users=ud.queryUserById(id);
		//返回第一条信息
		return users.get(0);
		
	}
	
	/*修改用户信息*/
	public int updateUser(UserBean user) throws SysException{
		//调用修改用户信息的方法
		int user1=ud.updateUser(user);
		//返回结果
		return user1;
		
	}
	
	/*删除用户信息*/
	public int deleteUser(int id) throws SysException{
		//调用删除用户信息的方法
		int user=ud.deleteUser(id);
		return user;
	}
}
