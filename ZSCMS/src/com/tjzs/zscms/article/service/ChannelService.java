package com.tjzs.zscms.article.service;

import java.util.List;

import com.tjzs.zscms.article.dao.impl.ChannelDao;
import com.tjzs.zscms.article.dao.impl.ChannelDaoImpl;
import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;

/**
 * 栏目信息的业务逻辑层
 * */
public class ChannelService {
	//采用多态形式
	ChannelDao cd=new ChannelDaoImpl();
	//查询所有栏目信息的方法
	public List<ChannelBean> queryAll() throws SysException{
		return cd.queryAllChannels();
	}
}
	
