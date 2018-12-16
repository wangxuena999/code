package com.zs.pms.po;

import java.io.Serializable;
import java.util.Date;

public class TSKU implements Serializable {

	private static final long serialVersionUID = -1747937811456313089L;
	private int id;
	private int pid;// 商品ID
	private int color;
	private int siz;// 尺码
	private int feature;// 材质
	private double trancost;// 运费
	private double sellcost;// 售价
	private int recont;// 库存数
	private int limi;// 购买限制
	private String wpos;// 仓库号
	private String skupic;// SKU图片
	private String skuname;// SKU名称
	private double price;
	private int sellcont;// 销量
	private int creator;
	private Date creatime;
	private int updator;
	private Date updatime;
	private int safcont;// 安全库存
	// 用于显示
	private String colorTxt;
	private String sizTxt;
	private String featureTxt;

	public String getColorTxt() {
		return colorTxt;
	}

	public void setColorTxt(String colorTxt) {
		this.colorTxt = colorTxt;
	}

	

	public String getSizTxt() {
		return sizTxt;
	}

	public void setSizTxt(String sizTxt) {
		this.sizTxt = sizTxt;
	}

	public String getFeatureTxt() {
		return featureTxt;
	}

	public void setFeatureTxt(String featureTxt) {
		this.featureTxt = featureTxt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getFeature() {
		return feature;
	}

	public void setFeature(int feature) {
		this.feature = feature;
	}

	public double getTrancost() {
		return trancost;
	}

	public void setTrancost(double trancost) {
		this.trancost = trancost;
	}

	public double getSellcost() {
		return sellcost;
	}

	public void setSellcost(double sellcost) {
		this.sellcost = sellcost;
	}

	public int getRecont() {
		return recont;
	}

	public void setRecont(int recont) {
		this.recont = recont;
	}

	public int getSiz() {
		return siz;
	}

	public void setSiz(int siz) {
		this.siz = siz;
	}

	public int getLimi() {
		return limi;
	}

	public void setLimi(int limi) {
		this.limi = limi;
	}

	public String getWpos() {
		return wpos;
	}

	public void setWpos(String wpos) {
		this.wpos = wpos;
	}

	public String getSkupic() {
		return skupic;
	}

	public void setSkupic(String skupic) {
		this.skupic = skupic;
	}

	public String getSkuname() {
		return skuname;
	}

	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSellcont() {
		return sellcont;
	}

	public void setSellcont(int sellcont) {
		this.sellcont = sellcont;
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

	public int getSafcont() {
		return safcont;
	}

	public void setSafcont(int safcont) {
		this.safcont = safcont;
	}

}
