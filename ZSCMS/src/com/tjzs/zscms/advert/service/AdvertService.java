package com.tjzs.zscms.advert.service;

import java.util.List;

import com.tjzs.zscms.advert.bean.AdvertBean;
import com.tjzs.zscms.advert.dao.impl.AdvertDao;
import com.tjzs.zscms.advert.dao.impl.AdvertDaoImpl;
import com.tjzs.zscms.exception.SysException;

/**
 * 广告的业务逻辑处理层
 * */
public class AdvertService {
	// 采用多态的形式
	AdvertDao ad = new AdvertDaoImpl();

	// 查询所有广告信息
	/**
	 * page表示查询的当前页数
	 * size：条数
	 * */
	public List<AdvertBean> queryByPage(int page,int size) throws SysException {
		//查询的每一页的m值：m=(page-1)*n
		int start=(page-1)*size;
		//返回每一页的数据条数
		return ad.queryByPage(start,size);

	}
	/*查询总页数*/
	public int queryPageCount(int size) throws SysException{
		//将用户的总记录转换为总页数
		int advertCount=ad.queryAdvertCounts();
		if(advertCount%size==0){//表示总页数能够整除每页查询的数据量
			//总页数
			return advertCount/size;
		}else{
			return advertCount/size+1;
		}
	}

	/* 新增广告信息 */
	public int insertAdvert(AdvertBean advert) throws SysException {
		// 新增成功则返回结果
		return ad.insertAdvert(advert);

	}

	/* 根据id查询用户信息 */
	public AdvertBean queryAdvertById(int id) throws SysException {
		// 将查询到的信息存入集合
		List<AdvertBean> adverts = ad.queryAdvertById(id);
		// 返回第一条信息
		return adverts.get(0);

	}

	/* 修改广告信息 */
	public int updateAdvert(AdvertBean advert) throws SysException {
		// 调用修改用户的方法
		int advert1 = ad.updateAdvert(advert);
		// 返回结果
		return advert1;

	}
	
	/* 删除广告信息 */
	public int deleteAdvert(int id) throws SysException{
		//调用删除广告信息的方法
		int advert=ad.deleteAdvert(id);
		//返回结果
		return advert;
	}

}
