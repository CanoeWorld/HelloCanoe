package com.canoe.bean;

import java.util.Date;

/**
 * 
 * 订单类
 * 
 * @author Administrator
 * 
 */
public class Order {

	int orderId;

	String orderNo;// 订单编号

	int userId;// 下单人

	String orderReciver;// 收货人

	String orderAddress; // 收货人地址

	String orderPhone;// 收货人电话

	int orderStatus; // 订单状态 -2删除 -1取消 0购物车 1等待付款 2已付款 3待处理 4配送中 5完成  

	int orderPayment; // 付款方式

	Date orderDate; // 下单时间

	int orderPrice; // 订单总价

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderReciver() {
		return orderReciver;
	}

	public void setOrderReciver(String orderReciver) {
		this.orderReciver = orderReciver;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderPhone() {
		return orderPhone;
	}

	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderPayment() {
		return orderPayment;
	}

	public void setOrderPayment(int orderPayment) {
		this.orderPayment = orderPayment;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
}
