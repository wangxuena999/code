package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ArticleDao;
import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TArticle;
import com.zs.pms.service.ArticleService;
import com.zs.pms.utils.Constants;
import com.zs.pms.vo.QueryArticle;


@Service //业务实现
public class ArticleServiceImpl implements ArticleService{
	@Autowired //接口注入
	private ArticleDao ad;
	
	@Override
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public int insert(TArticle article) throws BusinessException {
		// TODO Auto-generated method stub
		return ad.insert(article);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public void update(TArticle article) throws BusinessException {
		// TODO Auto-generated method stub
		ad.update(article);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public void delete(int id) throws BusinessException {
		// TODO Auto-generated method stub
		ad.delete(id);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public void deleteByIds(int[] ids) throws BusinessException {
		// TODO Auto-generated method stub
		ad.deleteByIds(ids);
	}

	@Override
	public TArticle queryById(int id) {
		// TODO Auto-generated method stub
		return ad.queryById(id);
	}

	@Override
	public List<TArticle> queryByCon(QueryArticle query) {
		// TODO Auto-generated method stub
		return ad.queryByCon(query);
	}

	@Override
	/**
	 * query:条件    page：当前页
	 */
	public List<TArticle> queryByPage(QueryArticle query, int page) {
		// TODO Auto-generated method stub
		//将当前页设置到条件中
		query.setPage(page);
		return ad.queryByPage(query);
	}

	@Override
	/**
	 * 计算总页数
	 */
	public int queryPageCount(QueryArticle query) {
		// TODO Auto-generated method stub
		//获得总条数
		int count=ad.queryCount(query);
		if(count%Constants.PAGECOUNT==0){
			return count/Constants.PAGECOUNT;
		}else{
			return count/Constants.PAGECOUNT+1;
		}
		
	}

	@Override
	public List<TArticle> queryByChannel(int id) {
		// TODO Auto-generated method stub
		return ad.queryByChannel(id);
	}

}
