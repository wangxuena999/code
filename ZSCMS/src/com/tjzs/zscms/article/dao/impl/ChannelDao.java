package com.tjzs.zscms.article.dao.impl;

import java.util.List;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;

public interface ChannelDao {
	//查询所有栏目，返回所有栏目信息
	public List<ChannelBean> queryAllChannels() throws SysException;
}
