package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.UserDao;
import com.zs.pms.exception.BusinessException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.UserService;
import com.zs.pms.utils.Constants;
import com.zs.pms.utils.MD5;
import com.zs.pms.vo.QueryUser;
/**
 * 用户的登录实现
 * @author Administrator
 *
 */

@Service //业务实现
public class UserServiceImpl implements UserService {
	@Autowired //按类型自动装配
	private UserDao ud;

	@Override
	public TUser chkLogin(QueryUser query) throws BusinessException {
//		//将明码变成密码
//		MD5 md5=new MD5();
//		//加密后的密码
//		String p1=md5.getMD5ofStr(query.getPassword());
//		//将密码放到query中
//		query.setPassword(p1);
		
		
		//调用dao层条件查询的方法进行登录
		List<TUser> list=ud.queryByCon(query);
		//判空，如果没有用户
		if(list==null||list.size()!=1){
			//抛出异常
			throw new BusinessException(1000, "用户名或密码输入有误，请重新输入");
		}
		//获得第一条信息
		TUser user=list.get(0);
		//关联权限列表返回，有权限的人登陆后能显示左侧菜单，不同的人拥有不同的权限
		return ud.queryById(user.getId());
		
	}

	@Override
	public List<TUser> queryByCon(QueryUser query) {
		// TODO Auto-generated method stub
		return ud.queryByCon(query);
	}

	@Override
	
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public void deleteByIds(int[] ids) throws BusinessException {
		// TODO Auto-generated method stub
		
		ud.deleteByIds(ids);
	}

	@Override
	
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public void update(TUser user) throws BusinessException {
		//不可用用户
		if(user.getIsenabled()==-1){
			throw new BusinessException(1009, "不能修改不可用的用户");
		}
		//获得原来的用户
		TUser ouser=ud.queryById(user.getId());
		//原密码不等于新密码，才加密
		if(user.getPassword()!=null&&(!"".equals(user.getPassword()))&&!user.getPassword().equals(ouser.getPassword())){
			//密码加密
			MD5 md5=new MD5();
			user.setPassword(md5.getMD5ofStr(user.getPassword()));
		}
		ud.update(user);
	}

	@Override
	
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public int insert(TUser user) throws BusinessException {
		// TODO Auto-generated method stub
		//ud.insert(user);//新增两次的话，若有异常前后都回滚，都不会新增成功
		//int a=1/0;//抛出异常
//		if(user.getPassword()!=null&&(!"".equals(user.getPassword()))){
//			//密码加密
//			MD5 md5=new MD5();
//			user.setPassword(md5.getMD5ofStr(user.getPassword()));
//		}
		//登录名为admin时，抛异常
		if("admin".equals(user.getLoginname())){
			throw new BusinessException(1008, "登录名不能为admin");
		}
		return ud.insert(user);
	}

	@Override
	
	@Transactional(rollbackFor=Exception.class)//只要有异常就回滚，没有则提交
	public void delete(int id) throws BusinessException {
		// TODO Auto-generated method stub
		ud.delete(id);
	}

	@Override
	public TUser queryById(int id) {
		// TODO Auto-generated method stub
		return ud.queryById(id);
	}

	@Override
	/**
	 * query:条件    page：当前页
	 */
	public List<TUser> queryByPage(QueryUser query, int page) {
		// TODO Auto-generated method stub
		//将当前页设置到条件中
		query.setPage(page);
		return ud.queryByPage(query);
	}

	@Override
	/**
	 * 计算总页数
	 */
	public int queryPageCount(QueryUser query) {
		// TODO Auto-generated method stub
		//获得总条数
		int count=ud.queryCount(query);
		//如果能整除，得整数页
		if(count%Constants.PAGECOUNT==0){
			return count/Constants.PAGECOUNT;
		}else{
			//不能整除，让页数+1
			return count/Constants.PAGECOUNT+1;
		}
		
	}
	
	

}
