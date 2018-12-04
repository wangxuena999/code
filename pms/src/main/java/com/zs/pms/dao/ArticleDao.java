package com.zs.pms.dao;

import java.util.List;

import com.zs.pms.po.TArticle;
import com.zs.pms.vo.QueryArticle;

public interface ArticleDao {

	// 新增
	public int insert(TArticle article);

	// 修改
	public void update(TArticle article);

	// 根据主键删除一条
	public void delete(int id);

	// 批量删除
	public void deleteByIds(int[] ids);

	// 通过主键查询
	public TArticle queryById(int id);

	// 通过条件查询，返回的有可能会是一个集合，多个参数时，封装到对象里
	public List<TArticle> queryByCon(QueryArticle query);

	// 查分页
	public List<TArticle> queryByPage(QueryArticle query);

	// 获得总条数
	public int queryCount(QueryArticle query);
	
	//通过栏目查询文章，带有文章的栏目不能删除
	public List<TArticle> queryByChannel(int id);
}
