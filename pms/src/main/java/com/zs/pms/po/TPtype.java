package com.zs.pms.po;

import java.io.Serializable;

public class TPtype implements Serializable {

	private static final long serialVersionUID = -8013816980362899183L;

	private int id;
	private String tname;//类名名称
	private int pid;
	private String remark;//备注

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
