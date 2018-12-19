package com.tjzs.zscms.advert.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.advert.bean.AdvertBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.DeptBean;
import com.tjzs.zscms.user.bean.UserBean;
import com.tjzs.zscms.util.DButil;
/**
 * 查询广告表中所有的广告信息
 * */
public class AdvertDaoImpl implements AdvertDao {
	//连接数据库
	DButil db=new DButil();
	
	/* 根据条件查询广告信息 */
	@Override
	public List<AdvertBean> queryByCondition(String sql, Object[] objs)
			throws SysException {
		//调用DBUtil的查询方法，将信息存到集合中
		List<Map<String, String>> list = db.execQuery(sql, objs);
		// 创建list集合来存储advert对象
		List<AdvertBean> adverts = new ArrayList<AdvertBean>();
		// 遍历集合，将map集合中的数据取出，直接封装到AdvertBean中
		for (Map<String, String> map : list) {
			// 创建UserBean对象
			AdvertBean advertbean=new AdvertBean();
			// map集合是根据key来获取value值的
			advertbean.setId(Integer.parseInt(map.get("id")));
			advertbean.setTitle(map.get("title"));
			advertbean.setContent(map.get("content"));
			advertbean.setCrtime(map.get("crtime"));
			advertbean.setCrman(map.get("crman"));
			// 将AdvertBean对象加入集合中
			adverts.add(advertbean);
			}
				return adverts;
	}
	
	/*查询所有广告信息*/
	@Override
	public List<AdvertBean> queryByPage(int start,int size) throws SysException {
		//利用sql语句查询广告表中的所有信息
		String sql="select a.*,u.loginname from tadvert a left join tuser u on a.crman=u.loginname order by a.id desc limit ?,?";
		Object[] objs={start,size};
		//调用DBUtil的查询方法，执行查询，返回结果
		List<Map<String,String>> list = db.execQuery(sql, objs);
		//创建集合，存储广告信息
		List<AdvertBean> adverts=new ArrayList<AdvertBean>();
		//遍历list集合，将数据取出，然后封装到AdvertBean中
		for (Map<String, String> map:list) {
			//创建AdvertBean对象
			AdvertBean advert=new AdvertBean();
			advert.setId(Integer.parseInt(map.get("id")));
			advert.setTitle(map.get("title"));
			advert.setContent(map.get("content"));
			advert.setCrtime(map.get("crtime"));
			advert.setCrman(map.get("crman"));
			//添加到集合中
			adverts.add(advert);
		}
		
		return adverts;
	}
	/*查询用户的总记录条数*/
	@Override
	public int queryAdvertCounts() throws SysException {
		//创建sql语句
		String sql="select count(id) count from tadvert";
		//调用dbutil的查询方法，不需要有参数，所以直接参数为null
		List<Map<String, String>> list = db.execQuery(sql, null);
		//查询的count表格只有一列，为int类型，故转换取出
		int advertCount=Integer.parseInt(list.get(0).get("count"));
		//返回结果
		return advertCount;
	}
	
	/*新增广告*/
	@Override
	public int insertAdvert(AdvertBean advert) throws SysException {
		//创建sql语句，新增到数据库的tadvert表中
		String sql="insert into tadvert values(null,?,?,?,?)";
		//给sql语句的参数赋值
		Object[] objs={advert.getTitle(),advert.getContent(),
				advert.getCrtime(),advert.getCrman()};
		//调用数据库增删改的方法
		int result = db.execUpdate(sql, objs);
		//返回结果
		return result;
	}
	/*根据id查询广告信息*/
	@Override
	public List<AdvertBean> queryAdvertById(int id) throws SysException {
		//创建sql语句
		String sql="select * from tadvert where id=?";
		//给sql语句参数赋值
		Object[] objs={id};
		//返回查询结果
		return this.queryByCondition(sql, objs);
	}

	/*修改广告信息*/
	@Override
	public int updateAdvert(AdvertBean advert) throws SysException {
		//创建sql语句
		String sql="update tadvert set title=?,content=?,crtime=?,crman=? where id=?";
		//给sql语句参数赋值
		Object[] objs={advert.getTitle(),advert.getContent(),
				advert.getCrtime(),advert.getCrman(),advert.getId()};
		//调用查询广告信息的方法
		int result = db.execUpdate(sql, objs);
		//返回结果
		return result;
	}
	/*删除广告信息*/
	@Override
	public int deleteAdvert(int id) throws SysException {
		//创建sql语句
		String sql="delete from tadvert where id=?";
		//给sql语句参数赋值
		Object[] objs={id};
		//调用删除广告信息的方法
		int result = db.execUpdate(sql, objs);
		return result;
	}
	
	
}
