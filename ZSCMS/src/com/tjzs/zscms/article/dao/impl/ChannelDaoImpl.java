package com.tjzs.zscms.article.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;

/**
 * 查询栏目表中所有的栏目信息
 * */
public class ChannelDaoImpl implements ChannelDao {
	//连接数据库
	DButil db=new DButil();

	@Override
	public List<ChannelBean> queryAllChannels() throws SysException {
		//利用sql语句查询栏目表中的所有信息
		String sql="select * from tchannel";
		//调用DBUtil的查询方法，执行查询，返回结果
		List<Map<String,String>> list = db.execQuery(sql, null);
		//创建集合，存储栏目信息
		List<ChannelBean> channels=new ArrayList<ChannelBean>();
		//遍历list集合，将数据取出，然后封装到ChannelBean中
		for (Map<String, String> map : list) {
			//创建channelbean对象
			ChannelBean channel=new ChannelBean();
			//将信息封装
			channel.setId(Integer.parseInt(map.get("id")));
			channel.setCname(map.get("cname"));
			//将封装后的栏目信息加入集合
			channels.add(channel);
		}
		return channels;
	}
	
	
	
}
