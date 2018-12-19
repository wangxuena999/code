package com.tjzs.zscms.channel.dao.impl;

import java.util.List;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;

public interface ChannelDao {
	//根据条件查询栏目表
	public List<ChannelBean> queryByCondition(String condition,Object[] objs) throws SysException;
	//查询栏目表全部信息
	/**
	 * start:表示分页查询的起始值
	 * size：表示每页显示多少条
	 * */
	public List<ChannelBean> queryByPage(int start,int size) throws SysException;
	//查询用户的总记录条数
	public int queryChannelCounts() throws SysException;
	//新增栏目
	public int insertChannel(ChannelBean channel) throws SysException;
	//根据栏目Id查询栏目信息
	public List<ChannelBean> queryChannelById(int id) throws SysException;
	//修改栏目
	public int updateChannel(ChannelBean channel) throws SysException;
	//删除栏目
	public int deleteChannel(int id) throws SysException;
}
