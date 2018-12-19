package com.tjzs.zscms.channel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.channel.bean.ChannelnameBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;

public class ChannelnameDaoImpl implements ChannelnameDao {

	//创建连接数据库对象
	DButil db=new DButil();
	
	@Override
	public List<ChannelnameBean> queryAllChannelnames() throws SysException {
		//创建查询栏目表sql语句
		String sql="SELECT DISTINCT t2.id,t2.pname FROM tchannel t1 LEFT JOIN (SELECT id,cname as pname from tchannel) t2 on t1.pid=t2.id";
		//调用DBUtil的查询方法，参数数组为null，表示DBUtil的查询sql语句没有查询条件
				List<Map<String,String>> list=db.execQuery(sql, null);
				//创建集合，用于存放查询部门表的结果
				List<ChannelnameBean> channelnames=new ArrayList<ChannelnameBean>();
				//遍历list集合
				for(Map<String,String> map:list){
					//创建DeptBean对象，用于封装数据
					ChannelnameBean channelname=new ChannelnameBean();
					//将从数据库查询的部门表信息封装到DeptBean中
					channelname.setId(Integer.parseInt(map.get("id")));
					channelname.setChannelpidname(map.get("pname"));
					//将封装好的deptbean存放到集合中
					channelnames.add(channelname);
				}
				//返回查询部门信息
				return channelnames;
		
	}

}
