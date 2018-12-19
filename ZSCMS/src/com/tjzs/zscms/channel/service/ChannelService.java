package com.tjzs.zscms.channel.service;

import java.util.List;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.channel.dao.impl.ChannelDao;
import com.tjzs.zscms.channel.dao.impl.ChannelDaoImpl;
import com.tjzs.zscms.exception.SysException;

public class ChannelService {
	// 使用多态的形式，创建栏目dao层对象
	ChannelDao cd = new ChannelDaoImpl();

	/* 查询全部栏目信息 */
	/**
	 * page表示查询的当前页数
	 * size：条数
	 * */
	public List<ChannelBean> queryByPage(int page,int size) throws SysException {
		//查询的每一页的m值：m=(page-1)*n
		int start=(page-1)*size;
		//返回每一页的数据条数
		return cd.queryByPage(start,size);

	}
	/*查询总页数*/
	public int queryPageCount(int size) throws SysException{
		//将用户的总记录转换为总页数
		int channelCount=cd.queryChannelCounts();
		if(channelCount%size==0){//表示总页数能够整除每页查询的数据量
			//总页数
			return channelCount/size;
		}else{
			//不能整除，就多加一页
			return channelCount/size+1;
		}
	}

	/* 新增栏目 */
	public int insertChannel(ChannelBean channel) throws SysException {
		// 调用栏目dao层新增栏目方法，返回新增结果
		return cd.insertChannel(channel);

	}

	/* 根据栏目Id查询栏目信息 */
	public ChannelBean queryChannelById(int id) throws SysException {
		// 调用栏目dao层根据栏目id查询栏目信息的方法
		List<ChannelBean> channel = cd.queryChannelById(id);
		// 因为根据id查询只有一条信息，所以返回的是集合的第一个元素，也是唯一一个元素
		return channel.get(0);

	}

	/* 修改栏目 */
	public int updateChannel(ChannelBean channel) throws SysException {
		// 调用栏目dao层修改栏目方法
		int result = cd.updateChannel(channel);
		// 返回修改栏目结果
		return result;
	}

	/* 删除栏目 */
	public int deleteChannel(int id) throws SysException {
		// 调用栏目dao层删除栏目方法
		int result = cd.deleteChannel(id);
		// 返回删除栏目的结果
		return result;

	}
}
