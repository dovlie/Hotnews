package com.city.erp.model.sxy;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ProcurementProgram")
//采购计划
public class ProcurementProgramModel { 
	@Id
	@Column(name="PurchaseId")
	private String pid=null;//采购编号
	
	@Column(name="PurchaseDate")
	@JsonFormat(pattern = "yyyy-MM-dd") 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date pdate=null;//采购日期
	
	@Column(name="PurchaseProductName")
	private String ppname=null;// 采购产品名称
	
	@Column(name="PurchaseQuantity")
	private String pquantity=null;//采购数量
	
	@Column(name="StewardId")
	private String sid=null;//负责人编号

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getPpname() {
		return ppname;
	}

	public void setPpname(String ppname) {
		this.ppname = ppname;
	}

	public String getPquantity() {
		return pquantity;
	}

	public void setPquantity(String pquantity) {
		this.pquantity = pquantity;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
	
	
}
