package com.tjzs.zscms.article.bean;

public class ArticleBean {
	private int id;//文章id
	private String title;//文章标题
	private String content;//文章内容
	private String author;//文章作者
	private String crtime;//文章创建时间
	private int channel;//文章所属栏目
	private int isremod;//是否推荐
	private int ishot;//是否热点
	private String channelname;//栏目名称
	private String isremodTxt;//是否推荐
	private String ishotTxt;//是否推荐
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
	public String getCrtime() {
		return crtime;
	}
	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
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
	public String getChannelname() {
		return channelname;
	}
	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}
	public String getIsremodTxt() {
		return isremodTxt;
	}
	public void setIsremodTxt(String isremodTxt) {
		this.isremodTxt = isremodTxt;
	}
	public String getIshotTxt() {
		return ishotTxt;
	}
	public void setIshotTxt(String ishotTxt) {
		this.ishotTxt = ishotTxt;
	}
	
	
}
