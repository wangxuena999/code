package com.tjzs.zscms.advert.dao.impl;

import java.util.List;

import com.tjzs.zscms.advert.bean.AdvertBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;

/**
 * 对tadvert表进行crud(增删改查)操作,Dao包属于接口
 * */
public interface AdvertDao {
	//根据条件查询广告信息，参数为查询条件，返回的是所有广告信息的集合
	public List<AdvertBean> queryByCondition(String condition,Object[] objs) throws SysException;
	//查询广告表的全部信息
	/**
	 * start:表示分页查询的起始值
	 * size：表示每页显示多少条
	 * */
	public List<AdvertBean> queryByPage(int start,int size) throws SysException;
	//查询广告的总记录条数
	public int queryAdvertCounts() throws SysException;
	//广告新增,类型为int类型，可认为是如果新增成功则返回的是1，失败则为0，新增时需要传入参数
	public int insertAdvert(AdvertBean advert) throws SysException;
	//根据id查询广告信息
	public List<AdvertBean> queryAdvertById(int id) throws SysException;
	//修改广告信息
	public int updateAdvert(AdvertBean advert) throws SysException;
	//删除广告信息
	public int deleteAdvert(int id) throws SysException; 
}
