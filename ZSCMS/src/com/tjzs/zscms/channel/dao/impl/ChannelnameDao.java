package com.tjzs.zscms.channel.dao.impl;

import java.util.List;


import com.tjzs.zscms.channel.bean.ChannelnameBean;
import com.tjzs.zscms.exception.SysException;

public interface ChannelnameDao {
//查询栏目表所有信息
public List<ChannelnameBean> queryAllChannelnames() throws SysException;
}
