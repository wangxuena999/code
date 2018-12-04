package com.zs.pms.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zs.pms.utils.DateUtil;

/**
 * 用户表的PO
 * 
 * @author Administrator
 *
 */
public class TUser implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 5293214558214995122L;

	private int id;
	private String loginname;
	private String password;
	private String realname;
	private String sex;
	private Date birthday;
	private String email;
	private TDept dept;// 关联对象，一对一
	private int isenabled;
	private int creator;
	private Date creatime;
	private int updator;
	private Date updatetime;
	private String pic;
	private List<TPermission> permissions;// 关联 权限列表
	private List<TPermission> menu = new ArrayList<>();// 左侧菜单，由permission整理出来
	private String isenabledTxt;
	private String birthdayTxt;
	private String creatimeTxt;
	private String updatetimeTxt;

	public String getCreatimeTxt() {
		return DateUtil.getDateToString(creatime, "yyyy-MM-dd");
	}

	public String getUpdatetimeTxt() {
		if(updatetime!=null){
			return DateUtil.getDateToString(updatetime, "yyyy-MM-dd");
		}else{
			return "未修改";
		}
		
	}

	public String getBirthdayTxt() {

		return DateUtil.getDateToString(birthday, "yyyy-MM-dd");
	}

	public String getIsenabledTxt() {
		if (isenabled == 1) {
			return "可用";
		} else {
			return "不可用";
		}

	}

	/**
	 * 整理菜单
	 * 
	 * @return
	 */
	public List<TPermission> getMenu() {
		for (TPermission per1 : permissions) {
			// 一级菜单
			if (per1.getPid() == 0) {
				// 装载一级菜单下的二级菜单
				for (TPermission per2 : permissions) {
					// 一级菜单的id==二级菜单的pid
					// 说明该权限是一级菜单的子权限
					if (per1.getId() == per2.getPid()) {
						per1.getChildren().add(per2);
					}
				}
				// 将装载好的一级菜单放入菜单中
				menu.add(per1);
			}
		}

		return menu;
	}

	public List<TPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<TPermission> permissions) {
		this.permissions = permissions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TDept getDept() {
		return dept;
	}

	public void setDept(TDept dept) {
		this.dept = dept;
	}

	public int getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(int isenabled) {
		this.isenabled = isenabled;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Date getCreatime() {
		return creatime;
	}

	public void setCreatime(Date creatime) {
		this.creatime = creatime;
	}

	public int getUpdator() {
		return updator;
	}

	public void setUpdator(int updator) {
		this.updator = updator;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
