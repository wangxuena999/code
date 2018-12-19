package com.tjzs.zscms.exception;

public class SysException extends Exception {
	//异常码
	private int errCode;
	//异常信息
	private String errMsg;
	/**
	 * 带参构造函数,初始化实例变量
	 * @param errCode
	 * @param errMsg
	 */
	public SysException(int errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	//给所有属性setter和getter
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

}
