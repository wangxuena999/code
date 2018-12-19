package com.tjzs.zscms.channel.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.channel.bean.ChannelBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;

/**
 * 对tchannel表进行crud(增删改查)操作,Dao包属于接口
 * */
public class ChannelDaoImpl implements ChannelDao {
	// 创建连接数据库对象
	DButil db = new DButil();

	/* 根据条件查询栏目信息 */
	@Override
	public List<ChannelBean> queryByCondition(String sql, Object[] objs)
			throws SysException {
		// 调用DBUtil的查询方法
		List<Map<String, String>> list = db.execQuery(sql, objs);
		// 创建集合，存放栏目信息
		List<ChannelBean> channels = new ArrayList<ChannelBean>();
		// 遍历list集合
		for (Map<String, String> map : list) {
			// 创建ChannelBean对象，用于封装栏目信息
			ChannelBean channelbean = new ChannelBean();
			// 将从数据库中获取的栏目信息封装到channelbean中
			channelbean.setId(Integer.parseInt(map.get("id")));
			channelbean.setCname(map.get("cname"));
			channelbean.setPid(Integer.parseInt(map.get("pid")));
			channelbean.setLev(Integer.parseInt(map.get("lev")));
			channelbean.setIsleaf(Integer.parseInt(map.get("isleaf")));
			channelbean.setChannelpidname(map.get("pname"));
			channelbean.setSort(Integer.parseInt(map.get("sort")));
			//从数据库中获取栏目表中级别判断，1代表一级，2代表二级
			if(map.get("lev").equals("1")){
				channelbean.setLevname("一级");
			}
			if(map.get("lev").equals("2")){
				channelbean.setLevname("二级");
			}
			// 判断栏目表中是否叶子字段，1为是叶子，0为不是叶子
			if (channelbean.getIsleaf() == 1) {
				// 栏目是叶子，将是叶子信息封装到bean中
				channelbean.setIsleafTxt("是");
			} else {
				// 栏目不是叶子，将不是叶子信息封装到bean中
				channelbean.setIsleafTxt("否");
			}
			
			// 将封装好的栏目信息存放到集合中
			channels.add(channelbean);
		}
		// 返回栏目集合
		return channels;
	}

	/* 查询所有栏目信息 */
	@Override
	public List<ChannelBean> queryByPage(int start,int size) throws SysException {
		// 创建sql语句
		//String sql="select * from tchannel order by id desc";
		String sql="SELECT t1.*,t2.pname FROM tchannel t1 LEFT JOIN (SELECT id,cname as pname from tchannel) t2 on t1.pid=t2.id order by t1.id desc limit ?,?";
		//给参数赋值
		Object[] objs={start,size};
		// 调用当前类的根据条件查询方法，返回结果
		return queryByCondition(sql, objs);
	}
	/*查询栏目的总记录条数*/
	@Override
	public int queryChannelCounts() throws SysException {
		//创建sql语句
		String sql="select count(id) count from tchannel";
		//调用dbutil的查询方法，不需要有参数，所以直接参数为null
		List<Map<String, String>> list=db.execQuery(sql, null);
		//查询的count表格只有一列，为int类型，故转换取出
		int channelCount=Integer.parseInt(list.get(0).get("count"));
		//返回结果
		return channelCount;
	}
	

	/* 新增栏目 */
	@Override
	public int insertChannel(ChannelBean channel) throws SysException {
		// 创建sql语句
		String sql = "insert into tchannel values(null,?,?,?,?,?)";
		// 创建参数数组
		Object[] objs = { channel.getCname(), channel.getPid(),
				channel.getLev(), channel.getIsleaf(),channel.getSort() };
		// 调用DBUtil的新增方法
		int result = db.execUpdate(sql, objs);
		// 返回新增栏目结果
		return result;
	}

	/* 根据栏目id查询栏目信息 */
	@Override
	public List<ChannelBean> queryChannelById(int id) throws SysException {
		// 创建sql语句
		String sql = "select * from tchannel where id=?";
		// 创建参数数组
		Object[] objs = { id };
		// 调用当前类的根据条件查询方法，返回栏目信息
		return this.queryByCondition(sql, objs);
	}

	/* 修改栏目 */
	@Override
	public int updateChannel(ChannelBean channel) throws SysException {
		// 创建sql语句
		String sql = "update tchannel set cname=?,pid=?,lev=?,isleaf=?,sort=? where id=?";
		// 创建参数数组
		Object[] objs = { channel.getCname(), channel.getPid(),
				channel.getLev(), channel.getIsleaf(),channel.getSort(),
				channel.getId() };
		// 调用DBUtil修改方法
		int result = db.execUpdate(sql, objs);
		// 返回修改栏目结果
		return result;
	}

	/* 删除栏目 */
	@Override
	public int deleteChannel(int id) throws SysException {
		// 创建sql语句
		String sql = "delete from tchannel where id=?";
		// 创建参数数组
		Object[] objs = { id };
		// 调用DBUtil删除方法
		int result = db.execUpdate(sql, objs);
		// 返回删除栏目结果
		return result;
	}

}
