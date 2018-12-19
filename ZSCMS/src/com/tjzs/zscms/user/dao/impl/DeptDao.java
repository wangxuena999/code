package com.tjzs.zscms.user.dao.impl;

import java.util.List;

import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;

/**
 * 部门的数据======对tdept表的操作
 * */
public interface DeptDao {
	//查询所有部门，返回所有部门信息
	public List<DeptBean> queryAllDepts() throws SysException;
}
