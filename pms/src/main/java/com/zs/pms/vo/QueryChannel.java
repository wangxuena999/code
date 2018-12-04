package com.zs.pms.vo;

public class QueryChannel extends QueryPage {

	private String cname;
	private int isleaf;

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

}
