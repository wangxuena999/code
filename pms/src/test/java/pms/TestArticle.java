package pms;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TArticle;
import com.zs.pms.po.TChannel;
import com.zs.pms.service.ArticleService;
import com.zs.pms.vo.QueryArticle;

@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TestArticle {
	
	@Autowired
	ArticleService as;
	
	
	public void testQuery(){

		QueryArticle query=new QueryArticle();
		query.setAuthor("林俊杰");
		System.out.println(as.queryByCon(query).size());
		
	}
	
	
	public void testAdd(){
		TArticle article=new TArticle();
		article.setAuthor("王雪娜");
		
		TChannel channel=new TChannel();
		channel.setId(1);
		article.setChannel(channel);;
		
		article.setContent("让我的爱伴着你直到永远");
		article.setCreator(1001);
		article.setTitle("知心爱人");
		
		try {
			as.insert(article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testUpdate(){
		TArticle article=new TArticle();
		article.setId(2026);
		article.setAuthor("王雪娜1");
		TChannel channel=new TChannel();
		channel.setId(2);
		article.setChannel(channel);
		
		article.setContent("让我的爱伴着你直到永远啊");
		article.setUpdator(1002);
		article.setTitle("知心爱人");
		
		try {
			as.update(article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testDel(){
		try {
			as.delete(2024);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void testDels(){
		int[] ids={2023,2025};
		try {
			as.deleteByIds(ids);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void testId(){
		TArticle article = as.queryById(1);
		System.out.println(article.getContent());
	}
	
	@Test
	public void testPage(){
		QueryArticle query=new QueryArticle();
		List<TArticle> queryByPage = as.queryByPage(query, 2);
		for (TArticle tArticle : queryByPage) {
			System.out.println(tArticle.getAuthor()+":"+tArticle.getContent());
		}
	}
	
	
	public void testCount(){
		QueryArticle query=new QueryArticle();
		System.out.println("当前总页数为"+as.queryPageCount(query)); 
		
	}
}
