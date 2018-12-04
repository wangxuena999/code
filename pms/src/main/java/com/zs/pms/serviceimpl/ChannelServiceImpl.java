package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ArticleDao;
import com.zs.pms.dao.ChannelDao;
import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TArticle;
import com.zs.pms.po.TChannel;
import com.zs.pms.service.ChannelService;
import com.zs.pms.utils.Constants;
import com.zs.pms.vo.QueryChannel;
import com.zs.pms.vo.QueryPage;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Autowired
	ChannelDao cd;
	@Autowired
	ArticleDao ad;

	@Override
	public List<TChannel> queryByPid(int pid) {
		// TODO Auto-generated method stub
		return cd.queryByPid(pid);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 只要有异常就回滚，没有则提交
	public int insert(TChannel channel) throws BusinessException {
		// TODO Auto-generated method stub
		return cd.insert(channel);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 只要有异常就回滚，没有则提交
	public void update(TChannel channel) throws BusinessException {
		// TODO Auto-generated method stub
		cd.update(channel);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 只要有异常就回滚，没有则提交
	public void delete(int id) throws BusinessException {
		/**
		 * 通过文章标的所属栏目查询文章，
		 * 文章表的所属栏目编号等于栏目表的id
		 * 通过栏目表的上级栏目查询栏目信息，上级栏目编号等于所属栏目的id
		 * 如果这两个集合中有一个不是0就不能删除该栏目
		 */
		List<TArticle> article=ad.queryByChannel(id);
		List<TChannel> channel=cd.queryByPid(id);
		if(article.size()!=0||channel.size()!=0){
			throw new BusinessException(200, "不能删除该栏目");
		}
		cd.delete(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class) // 只要有异常就回滚，没有则提交
	public void deleteByIds(int[] ids) throws BusinessException {
		// TODO Auto-generated method stub
		cd.deleteByIds(ids);
	}

	@Override
	public TChannel queryById(int id) {
		// TODO Auto-generated method stub
		return cd.queryById(id);
	}

	@Override
	public List<TChannel> queryByCon(QueryChannel query) {
		// TODO Auto-generated method stub
		return cd.queryByCon(query);
	}

	@Override
	public List<TChannel> queryByPage(QueryChannel query, int page) {
		// TODO Auto-generated method stub
		// 将当前页设置到条件中
		query.setPage(page);
		return cd.queryByPage(query);
	}

	@Override
	public int queryPageCount(QueryChannel query) {
		// TODO Auto-generated method stub

		// 获得总条数
		int count = cd.queryCount(query);
		// 如果能整除，得整数页
		if (count % Constants.PAGECOUNT == 0) {
			return count / Constants.PAGECOUNT;
		} else {
			// 不能整除，让页数+1
			return count / Constants.PAGECOUNT + 1;
		}
	}

}
