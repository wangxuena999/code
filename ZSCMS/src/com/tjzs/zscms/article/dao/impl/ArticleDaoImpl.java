package com.tjzs.zscms.article.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tjzs.zscms.article.bean.ArticleBean;
import com.tjzs.zscms.exception.SysException;
import com.tjzs.zscms.util.DButil;
/**
 * 对tarticle表进行crud（增删改查）操作
 * */
/*根据条件查询文章信息*/
public class ArticleDaoImpl implements ArticleDao{
	//连接数据库
	DButil db=new DButil();
	@Override
	public List<ArticleBean> queryByCondition(String sql, Object[] objs) throws SysException {
		//调用dbutil的查询的方法，将信息存入集合
		List<Map<String,String>> list = db.execQuery(sql, objs);
		//创建list集合来存储articlebean对象
		List<ArticleBean> articles=new ArrayList<ArticleBean>();
		//遍历集合，将map集合的元素取出，封装到articlebean中
		for (Map<String, String> map : list) {
			//创建articlebean对象
			ArticleBean articlebean=new ArticleBean();
			//map集合是根据key来获取value值的
			articlebean.setId(Integer.parseInt(map.get("id")));
			articlebean.setTitle(map.get("title"));
			articlebean.setContent(map.get("content"));
			articlebean.setAuthor(map.get("author"));
			articlebean.setCrtime(map.get("crtime"));
			articlebean.setChannel(Integer.parseInt(map.get("channel")));
			articlebean.setIsremod(Integer.parseInt(map.get("isremod")));
			articlebean.setIshot(Integer.parseInt(map.get("ishot")));
			//从数据库中获取栏目表中栏目名称判断，1代表有栏目名称，0代表没有栏目名称
			if(map.get("cname")!=null){
				articlebean.setChannelname(map.get("cname"));
			}
			//判断文章表中是否推荐字段，1为可用，0为不可用
			if(articlebean.getIsremod()==1){
				//可推荐，将推荐信息封装到bean中
				articlebean.setIsremodTxt("推荐");
			}else{
				//不可推荐，将推荐信息封装到bean中
				articlebean.setIsremodTxt("不推荐");
			}
			//判断文章表中是否热点字段，1为可用，0为不可用
			if(articlebean.getIshot()==1){
				//可推荐，将推荐信息封装到bean中
				articlebean.setIshotTxt("热点");
			}else{
				//不可推荐，将推荐信息封装到bean中
				articlebean.setIshotTxt("非热点");
			}
			//将articlebean对象加入集合
			articles.add(articlebean);
		}
		//返回结果
		return articles;
	}
	
	/*查询文章的全部信息*/
	@Override
	public List<ArticleBean> queryByPage(int start,int size) throws SysException {
		//创建sql语句
		String sql="select a.*,c.cname from tarticle a left join tchannel c on a.channel=c.id order by a.id desc limit ?,?";
		//给参数赋值
		Object[] objs={start,size};
		//调用根据sql语句查询文章信息方法
		return queryByCondition(sql, objs);
	}
	
	/*查询文章的总记录条数*/
	@Override
	public int queryArticleCounts() throws SysException {
		//创建sql语句
		String sql="select count(id) count from tarticle";
		//调用dbutil的查询方法，不需要有参数，所以直接参数为null
		List<Map<String, String>> list=db.execQuery(sql, null);
		//查询的count表格只有一列，为int类型，故转换取出
		int articleCount=Integer.parseInt(list.get(0).get("count"));
		//返回结果
		return articleCount;
	}
	

	/*新增文章信息*/
	@Override
	public int insertArticle(ArticleBean article) throws SysException {
		//创建sql语句
		String sql="insert into tarticle values(null,?,?,?,?,?,?,?)";
		//创建参数数组
		Object[] objs={article.getTitle(),article.getContent(),
				article.getAuthor(),article.getCrtime(),
				article.getChannel(),article.getIsremod(),
				article.getIshot()};
		//调用dbutil的新增方法
		int result = db.execUpdate(sql, objs);
		return result;
	}

	/*根据文章id查询文章信息*/
	@Override
	public List<ArticleBean> queryArticleById(int id) throws SysException {
		//创建sql语句
		String sql="select * from tarticle where id=?";
		//创建参数数组
		Object[] objs={id};
		return this.queryByCondition(sql, objs);
	}

	/*修改文章*/
	@Override
	public int updateArticle(ArticleBean article) throws SysException {
		//创建修改文章sql语句
		String sql="update tarticle set title=?,content=?,author=?,crtime=?,channel=?,isremod=?,ishot=? where id=?";
		//创建参数数组
		Object[] objs={article.getTitle(),article.getContent(),article.getAuthor(),article.getCrtime(),article.getChannel(),article.getIsremod(),article.getIshot(),article.getId()};
		//调用DBUtil的修改方法
		int result=db.execUpdate(sql, objs);
		//返回修改文章结果
		return result;
	}

	/*删除文章*/
	@Override
	public int deleteArticle(int id) throws SysException {
		//创建sql语句
		String sql="delete from tarticle where id=?";
		//创建参数数组
		Object[] objs={id};
		//调用DBUtil的删除方法
		int result=db.execUpdate(sql, objs);
		//返回删除文章结果
		return result;
	}

}
