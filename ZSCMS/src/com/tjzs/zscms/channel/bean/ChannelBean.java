package com.tjzs.zscms.channel.bean;

public class ChannelBean {
	private int id;//栏目id
	private String cname;//栏目名称
	private int pid;//上级栏目表
	private String channelpidname;//上级栏目名称
	private int lev;//栏目级别表
	private String levname;//栏目级别名称
	private int isleaf;//是否叶子
	private String isleafTxt;//是否叶子
	private int sort;//顺序
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public String getIsleafTxt() {
		return isleafTxt;
	}
	public void setIsleafTxt(String isleafTxt) {
		this.isleafTxt = isleafTxt;
	}
	public String getChannelpidname() {
		return channelpidname;
	}
	public void setChannelpidname(String channelpidname) {
		this.channelpidname = channelpidname;
	}
	public String getLevname() {
		return levname;
	}
	public void setLevname(String levname) {
		this.levname = levname;
	}
	
	
}
