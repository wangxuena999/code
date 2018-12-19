package com.tjzs.zscms.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.util.DButil;
/**
 * 查询部门表中所有的部门信息
 * */
public class DeptDaoImpl implements DeptDao {
	//连接数据库
	DButil db=new DButil();
	@Override
	public List<DeptBean> queryAllDepts() throws SysException {
		//利用sql语句查询部门表中的所有信息
		String sql="select * from tdept";
		//调用DBUtil的查询方法，执行查询，返回结果
		List<Map<String,String>> list = db.execQuery(sql, null);
		//创建集合，存储部门信息
		List<DeptBean> depts=new ArrayList<DeptBean>();
		//遍历list集合，将数据取出，然后封装到DeptBean中
		for (Map<String, String> map:list) {
			//创建deptbean对象
			DeptBean dept=new DeptBean();
			dept.setId(Integer.parseInt(map.get("id")));
			dept.setDname(map.get("dname"));
			//添加到集合中
			depts.add(dept);
		}
		
		return depts;
	}

}
