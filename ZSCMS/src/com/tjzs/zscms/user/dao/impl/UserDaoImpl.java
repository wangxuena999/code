package com.tjzs.zscms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.util.DButil;

/**
 * 对tuser表进行crud（增删改查）操作
 * */
public class UserDaoImpl implements UserDao {
	// 创建DBUtil对象来连接数据库
	DButil db = new DButil();
	/*用户登录查询*/
	@Override
	public List<UserBean> login(UserBean user) throws SysException {
		// 登录查询的sql语句
		String sql = "select * from tuser where loginname=? and password=?";
		// 给sql语句的参数赋值
		Object[] objs = { user.getLoginname(), user.getPassword() };
		// 执行查询，返回结果
		List<Map<String, String>> list = db.execQuery(sql, objs);
		// 创建list集合来存储user对象
		List<UserBean> users = new ArrayList<UserBean>();
		// 遍历集合，将map集合中的数据取出，直接封装到Userbean中
		for (Map<String, String> map : list) {
			// 创建UserBean对象
			UserBean userbean = new UserBean();
			// map集合是根据key来获取value值的
			userbean.setId(Integer.parseInt(map.get("id")));
			userbean.setLoginname(map.get("loginname"));
			userbean.setPassword(map.get("password"));
			userbean.setRealname(map.get("realname"));
			userbean.setSex(map.get("sex"));
			userbean.setBirthday(map.get("birthday"));
			userbean.setDept(Integer.parseInt(map.get("dept")));
			userbean.setEmail(map.get("email"));
			userbean.setEnabled(Integer.parseInt(map.get("enabled")));
			userbean.setCreatman(Integer.parseInt(map.get("creatman")));
			// 将userbean对象加入集合中
			users.add(userbean);
		}

		return users;
	}

	/* 根据条件查询用户信息 */
	@Override
	public List<UserBean> queryByCondition(String sql, Object[] objs) throws SysException {
		//调用DBUtil的查询方法，将信息存到集合中
		List<Map<String, String>> list = db.execQuery(sql, objs);
		// 创建list集合来存储user对象
		List<UserBean> users = new ArrayList<UserBean>();
		// 遍历集合，将map集合中的数据取出，直接封装到Userbean中
		for (Map<String, String> map : list) {
			// 创建UserBean对象
			UserBean userbean = new UserBean();
			// map集合是根据key来获取value值的
			userbean.setId(Integer.parseInt(map.get("id")));
			userbean.setLoginname(map.get("loginname"));
			userbean.setPassword(map.get("password"));
			userbean.setRealname(map.get("realname"));
			userbean.setSex(map.get("sex"));
			userbean.setBirthday(map.get("birthday"));
			userbean.setDept(Integer.parseInt(map.get("dept")));
			userbean.setEmail(map.get("email"));
			userbean.setEnabled(Integer.parseInt(map.get("enabled")));
			userbean.setCreatman(Integer.parseInt(map.get("creatman")));
			//从数据库中获取用户表中部门编号判断，1代表有部门名称，0代表没有部门名称
			if(map.get("dept")!=null){
				//如果用户表中部门编号不为null，表示用户有部门，将部门表的部门名称封装到bean中
				userbean.setDname(map.get("dname"));
			}
			//判断用户表中是否可用字段，1为可用，0为不可用
			if(userbean.getEnabled()==1){
				//用户为可用，将可用信息封装到bean中
				userbean.setEnabledTxt("可用");
			}else{
				//用户为不可用，将不可用信息封装到bean中
				userbean.setEnabledTxt("不可用");
			}
			// 将userbean对象加入集合中
			users.add(userbean);
		}

		return users;
	}
	/*查询用户的所有信息*/
	@Override
	public List<UserBean> queryByPage(int start,int size) throws SysException {
		//创建查询sql语句，将用户表和部门表进行左外连接查询，以用户表为主表，左外连接
		String sql="select u.*,d.dname from tuser u left join tdept d on u.dept=d.id order by u.id desc limit ?,?";
		//给参数赋值
		Object[] objs={start,size};
		//调用根据sql语句查询用户信息方法
		return queryByCondition(sql, objs);
	}
	/*查询用户的总记录条数*/
	@Override
	public int queryUserCounts() throws SysException {
		//创建sql语句
		String sql="select count(id) count from tuser";
		//调用dbutil的查询方法，不需要有参数，所以直接参数为null
		List<Map<String, String>> list=db.execQuery(sql, null);
		//查询的count表格只有一列，为int类型，故转换取出
		int userCount=Integer.parseInt(list.get(0).get("count"));
		//返回结果
		return userCount;
	}
	
	/*新增用户*/
	@Override
	public int insertUser(UserBean user) throws SysException {
		//创建sql语句，新增到数据库的tuser表中
		String sql="insert into tuser values (null,?,?,?,?,?,?,?,?,?)";
		//给sql语句的参数赋值
		Object[] objs={user.getLoginname(),user.getPassword(),
				user.getRealname(),user.getSex(),user.getBirthday(),
				user.getEmail(),user.getDept(),user.getEnabled(),user.getCreatman()};
		//调用数据库增删改的方法，返回结果
		int result = db.execUpdate(sql, objs);
		return result;
	}
	/*根据用户名查询用户信息*/
	@Override
	public List<UserBean> queryByLoginname(String loginname) throws SysException {
		//创建sql语句，根据用户名查询用户信息
		String sql="select * from tuser where loginname=?";
		//给sql语句的参数赋值
		Object[] objs={loginname};
		//this指的是本类中的方法，返回查询结果
		return this.queryByCondition(sql, objs);
	}
	/*根据邮箱查询用户信息*/
	@Override
	public List<UserBean> queryByEmail(String email) throws SysException {
		//创建sql语句，根据邮箱查询用户信息
		String sql="select * from tuser where email=?";
		//给sql语句的参数赋值
		Object[] objs={email};
		//返回查询结果
		return this.queryByCondition(sql, objs);
	}
	
	/*根据id查询用户信息*/
	@Override
	public List<UserBean> queryUserById(int id) throws SysException {
		//创建sql语句
		String sql="select * from tuser where id=?";
		//给sql语句参数赋值
		Object[] objs={id};
		//返回查询结果
		return this.queryByCondition(sql, objs);
	}

	/*修改用户信息*/
	@Override
	public int updateUser(UserBean user) throws SysException {
		//创建sql语句
		String sql="update tuser set loginname=?,realname=?,sex=?,birthday=?,email=?,dept=?,enabled=? where id=?";
		//给sql语句参数赋值
		Object[] objs={user.getLoginname(),user.getRealname(),
				user.getSex(),user.getBirthday(),user.getEmail(),
				user.getDept(),user.getEnabled(),user.getId()};
		//调用查询用户信息的方法
		int result=db.execUpdate(sql, objs);
		//返回结果
		return result;
	}

	/*删除用户信息*/
	@Override
	public int deleteUser(int id) throws SysException {
		//创建sql语句
		String sql="delete from tuser where id=?";
		//给sql语句参数赋值
		Object[] objs={id};
		//调用删除用户信息的方法
		int result = db.execUpdate(sql, objs);
		return result;
	}
	

}
