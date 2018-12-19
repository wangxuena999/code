package com.tjzs.zscms.user.bean;

public class UserBean {
	private int id;//用户的id
	private String loginname;//用户名
	private String password;//密码
	private String realname;//真实姓名
	private String sex;//性别
	private String birthday;//生日
	private int dept;//部门编号
	private String email;//用户邮箱
	private int enabled;//是否可用
	private int creatman;//创建人
	private String dname;//部门名称
	private String enabledTxt;//是否可用
	//给所有属性加上setter,getter
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getCreatman() {
		return creatman;
	}
	public void setCreatman(int creatman) {
		this.creatman = creatman;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getEnabledTxt() {
		return enabledTxt;
	}
	public void setEnabledTxt(String enabledTxt) {
		this.enabledTxt = enabledTxt;
	}
	
	
	
}
