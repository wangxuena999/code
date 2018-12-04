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
import com.zs.pms.service.ChannelService;
import com.zs.pms.vo.QueryChannel;

@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("classpath:applicationcontext.xml")//引入配置文件
public class TestChannel {

	@Autowired
	ChannelService cs;
	
	
	public void testQuery(){
		QueryChannel query=new QueryChannel();
		query.setCname("CCTV-1");
		System.out.println(cs.queryByCon(query).size());
	}
	
	
	public void testAdd(){
		TChannel channel1=new TChannel();
		channel1.setCname("娱乐");
		
		TChannel channel2=new TChannel();
		channel2.setId(2);
		
		//channel1.setPid(channel1);
		channel1.setLev(2);
		channel1.setIsleaf(0);
		
		try {
			cs.insert(channel1);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	public void testPage(){
		QueryChannel query=new QueryChannel();
		List<TChannel> queryByPage = cs.queryByPage(query, 1);
		for (TChannel channel : queryByPage) {
			System.out.println(channel.getCname());
		}
	}
	
	@Test
	public void testCount(){
		QueryChannel query=new QueryChannel();
		System.out.println("当前总页数为"+cs.queryPageCount(query));
	}
	
}
