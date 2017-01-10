package com.canoe.bean;

import java.util.List;

import com.canoe.bean.GoodsType;

/**
 * 商品分类
 * @author CANOE
 *
 */
public class GoodsType {
	
	String typeName;

	String typeCode;

	int parentId; // 默认值为0是一级分类

	Integer typeSort;

	int typeStatus;
	
	int typeId;
	
	GoodsType parent;
	
	List<GoodsType> children;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public Integer getTypeSort() {
		return typeSort;
	}

	public void setTypeSort(Integer typeSort) {
		this.typeSort = typeSort;
	}

	public int getTypeStatus() {
		return typeStatus;
	}

	public void setTypeStatus(int typeStatus) {
		this.typeStatus = typeStatus;
	}
	
	public String getTypeStatusText(){
		
		return typeStatus == 0 ? "禁用" : "启用";
		
	}

	public GoodsType getParent() {
		return parent;
	}

	public void setParent(GoodsType parent) {
		this.parent = parent;
	}

	public List<GoodsType> getChildren() {
		return children;
	}

	public void setChildren(List<GoodsType> children) {
		this.children = children;
	}

}
