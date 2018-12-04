package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TChannel;
import com.zs.pms.vo.QueryChannel;
import com.zs.pms.vo.QueryPage;

public interface ChannelDao {
	//根据上级栏目id获取栏目列表
	public List<TChannel> queryByPid(int pid);
	//新增
	public int insert(TChannel channel);
	//修改
	public void update(TChannel channel);
	//根据主键删除一条
	public void delete(int id);
	//删除多条
	public void deleteByIds(int[] ids);
	//通过主键查询
	public TChannel queryById(int id);
	//通过条件查询
	public List<TChannel> queryByCon(QueryChannel query);
	//查分页
	public List<TChannel> queryByPage(QueryChannel query);
	//查询总页数
	public int queryCount(QueryChannel query);
	
	
}
