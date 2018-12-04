package com.zs.pms.po;

import java.io.Serializable;
/**
 * 部门表的PO
 * @author Administrator
 *
 */
public class TDept implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 576555799166420979L;

	private int id;
	private String dname;
	private int pid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	
}
