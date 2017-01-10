package com.canoe.bean;

/**
 * 员工
 * 
 * @author CANOE
 * 
 */
public class Employee {

	int emplId;// 员工id
	
	String emplName;// 员工姓名

	Integer emplSex; // 员工姓名 0 男 1 女
	
	int emplAge; //员工年龄

	String emplNo; // 员工编号
	
	String emplUsername; //员工用户名

	String emplPwd;// 员工密码

	String emplRemark;// 备注
	
	Integer emplStatus; //员工状态 0 离职 1 在职
	
	public int getEmplId() {
		return emplId;
	}

	public void setEmplId(int emplId) {
		this.emplId = emplId;
	}

	public String getEmplName() {
		return emplName;
	}

	public void setEmplName(String emplName) {
		this.emplName = emplName;
	}

	public Integer getEmplSex() {
		return emplSex;
	}

	public void setEmplSex(Integer emplSex) {
		this.emplSex = emplSex;
	}
	
	public String getEmplSexText(){
		
		return emplSex == 0 ? "男" : "女";
	}
	public String getEmplNo() {
		return emplNo;
	}

	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}

	public String getEmplPwd() {
		return emplPwd;
	}

	public void setEmplPwd(String emplPwd) {
		this.emplPwd = emplPwd;
	}

	public String getEmplRemark() {
		return emplRemark;
	}

	public void setEmplRemark(String emplRemark) {
		this.emplRemark = emplRemark;
	}

	public String getEmplUsername() {
		return emplUsername;
	}

	public void setEmplUsername(String emplUsername) {
		this.emplUsername = emplUsername;
	}

	public int getEmplAge() {
		return emplAge;
	}

	public void setEmplAge(int emplAge) {
		this.emplAge = emplAge;
	}

	public Integer getEmplStatus() {
		return emplStatus;
	}

	public void setEmplStatus(Integer emplStatus) {
		this.emplStatus = emplStatus;
	}
	
	public String getEmplStatusText() {
		
		return emplStatus == 0 ? "离职" : "在职";
	}

}
