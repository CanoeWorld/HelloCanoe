package com.canoe.bean;

import com.canoe.bean.GoodsType;

/**
 * 商品分类
 * @author Administrator
 *
 */
public class Goods {

	int goodsId;
	String goodsName;
	String goodsCode;
	String goodsOutdate;
	String goodsStoreday;
	int goodsStorenum;
	double goodsPrice;
	String goodsRemark;
	int typeId;	// 商品分类
	int parentId; //商品分类的父分类
	Integer activeId;// 参与促销
	String goodsPic1;
	String goodsPic2;
	String goodsPic3;
	String goodsPic4;
	String goodsPic5;
	
	Integer goodsStatus;//0 下架 1上架
	
	GoodsActive goodsactive; //促销
	
	GoodsType goodstype; //商品分类
	
	
	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsOutdate() {
		return goodsOutdate;
	}

	public void setGoodsOutdate(String goodsOutdate) {
		this.goodsOutdate = goodsOutdate;
	}

	public String getGoodsStoreday() {
		return goodsStoreday;
	}

	public void setGoodsStoreday(String goodsStoreday) {
		this.goodsStoreday = goodsStoreday;
	}

	public int getGoodsStorenum() {
		return goodsStorenum;
	}

	public void setGoodsStorenum(int goodsStorenum) {
		this.goodsStorenum = goodsStorenum;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsRemark() {
		return goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Integer getActiveId() {
		return activeId;
	}

	public void setActiveId(Integer activeId) {
		this.activeId = activeId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getGoodsPic1() {
		return goodsPic1;
	}

	public void setGoodsPic1(String goodsPic1) {
		this.goodsPic1 = goodsPic1;
	}

	public String getGoodsPic2() {
		return goodsPic2;
	}

	public void setGoodsPic2(String goodsPic2) {
		this.goodsPic2 = goodsPic2;
	}

	public String getGoodsPic3() {
		return goodsPic3;
	}

	public void setGoodsPic3(String goodsPic3) {
		this.goodsPic3 = goodsPic3;
	}

	public String getGoodsPic4() {
		return goodsPic4;
	}

	public void setGoodsPic4(String goodsPic4) {
		this.goodsPic4 = goodsPic4;
	}

	public String getGoodsPic5() {
		return goodsPic5;
	}

	public GoodsActive getGoodsactive() {
		return goodsactive;
	}

	public void setGoodsactive(GoodsActive goodsActive) {
		this.goodsactive = goodsActive;
	}

	public void setGoodsPic5(String goodsPic5) {
		this.goodsPic5 = goodsPic5;
	}
	
	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	
	public String getGoodsStatusText() {
		return goodsStatus == 0 ? "下架" : "上架";
	}
	
	public GoodsType getGoodstype() {
		return goodstype;
	}
	
	public void setGoodstype(GoodsType goodsType) {
		this.goodstype = goodsType;
	}
}
