package com.zs.pms.service;

import java.util.List;

import com.zs.pms.po.TDept;

public interface DeptService {

	//根据上级id取部门列表
	public List<TDept> queryByPid(int pid);
}
