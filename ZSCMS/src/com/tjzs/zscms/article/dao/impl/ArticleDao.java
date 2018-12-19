package com.tjzs.zscms.article.dao.impl;

import java.util.List;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.user.bean.UserBean;

public interface ArticleDao {
/**
 * 对tarticle表进行crud(增删改查)操作,Dao包属于接口
 * @throws SysException 
 * */
	//根据条件查询文章表的信息
	public List<ArticleBean> queryByCondition(String condition,Object[] objs) throws SysException;
	//查询文章表的全部信息
	/**
	 * start:表示分页查询的起始值
	 * size：表示每页显示多少条
	 * */
	public List<ArticleBean> queryByPage(int start,int size) throws SysException;
	//查询文章的总记录条数
	public int queryArticleCounts() throws SysException;
	//文章新增,类型为int类型，可认为是如果新增成功则返回的是1，失败则为0，新增时需要传入参数
	public int insertArticle(ArticleBean article) throws SysException;
	//根据id查询文章信息
	public List<ArticleBean> queryArticleById(int id) throws SysException;
	//修改文章信息
	public int updateArticle(ArticleBean user) throws SysException;
	//删除文章信息
	public int deleteArticle(int id) throws SysException; 
}
