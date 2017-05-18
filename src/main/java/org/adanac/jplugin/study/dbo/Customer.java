package org.adanac.jplugin.study.dbo;

import java.sql.Date;

public class Customer {
	//可以提到枚举或者常量类中
	public static final String STATUS_NORMAL = "N";
	public static final String STATUS_FROZEN = "Z";
	public static final String STATUS_DELETED = "D";

	private Long custId;
	private String custName;
	private String custAddress;
	private String sex;
	private String status;
	private long custLevel;
	private Date createDate;
	
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAddress() {
		return custAddress;
	}
	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public long getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(long custLevel) {
		this.custLevel = custLevel;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
