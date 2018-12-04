package com.zs.pms.po;

import java.io.Serializable;

public class TCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3720364104842861238L;

	private int id;
	private int cid;
	private String name;
	private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
