package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TDept;

/**
 * 部门表
 * @author Administrator
 *
 */
public interface DeptDao {
	//根据上级id取部门列表
	public List<TDept> queryByPid(int pid);
}
