package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.DeptDao;
import com.zs.pms.po.TDept;
import com.zs.pms.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	DeptDao dd;
	@Override
	public List<TDept> queryByPid(int pid) {
		// TODO Auto-generated method stub
		return dd.queryByPid(pid);
	}

}
