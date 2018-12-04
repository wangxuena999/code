package com.zs.pms.service;

import java.util.List;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TArticle;
import com.zs.pms.vo.QueryArticle;

public interface ArticleService {

	// 新增
	public int insert(TArticle article) throws BusinessException;

	// 修改
	public void update(TArticle article) throws BusinessException;

	// 删除一条
	public void delete(int id) throws BusinessException;

	// 批量删除
	public void deleteByIds(int[] ids) throws BusinessException;

	// 获得一条
	public TArticle queryById(int id);

	// 按条件查询
	public List<TArticle> queryByCon(QueryArticle query);

	// 查分页
	public List<TArticle> queryByPage(QueryArticle query, int page);

	// 获得总页数
	public int queryPageCount(QueryArticle query);
	
	//通过栏目查询文章，带有文章的栏目不能删除
	public List<TArticle> queryByChannel(int id);

}
