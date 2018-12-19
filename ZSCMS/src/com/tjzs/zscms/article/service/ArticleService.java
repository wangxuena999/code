package com.tjzs.zscms.article.service;

import java.util.List;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.article.dao.impl.ArticleDao;
import com.tjzs.zscms.article.dao.impl.ArticleDaoImpl;
import com.tjzs.zscms.exception.SysException;

/**
 * 文章的业务逻辑处理层
 * */
public class ArticleService {
	//采用多态的形式
	ArticleDao ad=new ArticleDaoImpl();
	/*查询所有文章的信息*/
	/**
	 * page表示查询的当前页数
	 * size：条数
	 * */
	public List<ArticleBean> queryByPage(int page,int size) throws SysException{
		//查询的每一页的m值：m=(page-1)*n
		int start=(page-1)*size;
		//返回每一页的数据条数
		return ad.queryByPage(start,size);
	}
	/*查询总页数*/
	public int queryPageCount(int size) throws SysException{
		//将用户的总记录转换为总页数
		int articleCount=ad.queryArticleCounts();
		if(articleCount%size==0){//表示总页数能够整除每页查询的数据量
			//总页数
			return articleCount/size;
		}else{
			//不能整除，就多加一页
			return articleCount/size+1;
		}
	}
	
	/*新增文章*/
	public int insertArticle(ArticleBean article) throws SysException{
		//若新增成功则返回结果
		return ad.insertArticle(article);
		
	}
	/*根据文章id查询文章信息*/
	public ArticleBean queryArticleById(int id) throws SysException{
		//调用文章dao层根据文章id查询文章信息的方法，获取文章信息
		List<ArticleBean> articles=ad.queryArticleById(id);
		//因为根据id查询只能查到一条信息，那么集合中只有一个元素，所以返回第一个元素
		return articles.get(0);
	}
	
	/*修改文章*/
	public int updateArticle(ArticleBean article) throws SysException{
		//调用文章dao层修改文章的方法
		int article1=ad.updateArticle(article);
		//返回修改文章的结果
		return article1;
		
	}
	
	/*删除文章*/
	public int deleteArticle(int id) throws SysException{
		//调用文章dao层删除文章的方法
		int article=ad.deleteArticle(id);
		//返回删除文章的结果
		return article;
		
	}
	
	
}
