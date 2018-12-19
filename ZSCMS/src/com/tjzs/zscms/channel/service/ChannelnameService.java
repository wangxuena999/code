package com.tjzs.zscms.channel.service;

import java.util.List;

import com.tjzs.zscms.channel.bean.ChannelnameBean;
import com.tjzs.zscms.channel.dao.impl.ChannelnameDao;
import com.tjzs.zscms.channel.dao.impl.ChannelnameDaoImpl;
import com.tjzs.zscms.exception.SysException;

public class ChannelnameService {
	//采用多态形式
	ChannelnameDao cnd=new ChannelnameDaoImpl();
	//查询所有栏目信息的方法
	public List<ChannelnameBean> queryAll() throws SysException{
		return cnd.queryAllChannelnames();
	}
}
