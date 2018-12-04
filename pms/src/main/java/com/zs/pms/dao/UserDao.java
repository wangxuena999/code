package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TUser;
import com.zs.pms.vo.QueryUser;

public interface UserDao {

	//新增
	public int insert(TUser user);
	//修改
	public void update(TUser user);
	//根据主键删除一条
	public void delete(int id);
	//批量删除
	public void deleteByIds(int[] ids);
	//通过主键查询
	public TUser queryById(int id);
	//通过条件查询，返回的有可能会是一个集合，多个参数时，封装到对象里
	public List<TUser> queryByCon(QueryUser query);
	//查分页
	public List<TUser> queryByPage(QueryUser query);
	//获得总条数
	public int queryCount(QueryUser query);
}
