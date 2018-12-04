package com.zs.pms.po;

import java.io.Serializable;
/**
 * 文章表的PO
 * @author Administrator
 *
 */
import java.util.Date;

import com.zs.pms.utils.DateUtil;
public class TArticle implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 379814413321609472L;

	private int id;
	private String title;
	private String content;
	private String author;
	private TChannel channel;
	private int isremod;
	private int ishot;
	private int creator;
	private Date creatime;
	private int updator;
	private Date updatime;
	private String isremodTxt;
	private String ishotTxt;
	private String creatimeTxt;
	private String updatimeTxt;
	

	public String getCreatimeTxt() {
		return DateUtil.getDateToString(creatime, "yyyy-MM-dd");
	}
	public String getUpdatimeTxt() {
		if(updatime!=null){
			return DateUtil.getDateToString(updatime, "yyyy-MM-dd");
		}else{
			return "未修改";
		}
	}
	
	public String getIsremodTxt() {
		if(isremod==1){
			return "推荐";
		}else{
			return "不推荐";
		}
		
	}
	public String getIshotTxt() {
		if(ishot==1){
			return "热点";
		}else{
			return "非热点";
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public TChannel getChannel() {
		return channel;
	}
	public void setChannel(TChannel channel) {
		this.channel = channel;
	}
	public int getIsremod() {
		return isremod;
	}
	public void setIsremod(int isremod) {
		this.isremod = isremod;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
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
	public Date getUpdatime() {
		return updatime;
	}
	public void setUpdatime(Date updatime) {
		this.updatime = updatime;
	}
	
	
}
