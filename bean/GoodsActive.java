package com.canoe.bean;
/**
 * 商品促销类
 * @author CANOE
 *
 */
public class GoodsActive {

	int activeId;
	String activeName;
	String activeCode;
	Integer activeSort;
	int activeStatus;
	String activestartdate;
	String activeenddate;

	public int getActiveId() {
		return activeId;
	}

	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}

	public String getActiveName() {
		return activeName;
	}

	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public Integer getActiveSort() {
		return activeSort;
	}

	public void setActiveSort(Integer activeSort) {
		this.activeSort = activeSort;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	public String getActiveStatusText(){
		return activeStatus == 0 ? "禁用" : "启用";
	}
	public String getActivestartdate() {
		return activestartdate;
	}

	public void setActivestartdate(String activeStartDate) {
		this.activestartdate = activeStartDate;
	}

	public String getActiveenddate() {
		return activeenddate;
	}

	public void setActiveenddate(String activeEndDate) {
		this.activeenddate = activeEndDate;
	}

}
