package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TChannel;
import com.zs.pms.vo.QueryChannel;
import com.zs.pms.vo.QueryPage;

public interface ChannelService {
	//通过上级栏目查询栏目列表
	public List<TChannel> queryByPid(int pid);
	//新增
	public int insert(TChannel channel) throws BusinessException;
	//修改
	public void update(TChannel channel) throws BusinessException;
	//通过主键删除一条
	public void delete(int id) throws BusinessException;
	//删除多条
	public void deleteByIds(int[] ids) throws BusinessException;
	//按主键查询，获得一条
	public TChannel queryById(int id);
	//通过条件查询
	public List<TChannel> queryByCon(QueryChannel query);
	//查分页
	public List<TChannel> queryByPage(QueryChannel query,int page);
	//查询总页数
	public int queryPageCount(QueryChannel query);
	
}
