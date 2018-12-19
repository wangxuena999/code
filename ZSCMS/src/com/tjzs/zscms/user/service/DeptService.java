package com.tjzs.zscms.user.service;

import java.util.List;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.user.dao.impl.DeptDao;
import com.tjzs.zscms.user.dao.impl.DeptDaoImpl;

/**
 * 部门信息的业务逻辑层
 * */
public class DeptService {
	//采用多态的形式
	DeptDao dt=new DeptDaoImpl();
	//查询所有部门信息的方法
	public List<DeptBean> queryAll() throws SysException{
		return dt.queryAllDepts();
		
	}
}
