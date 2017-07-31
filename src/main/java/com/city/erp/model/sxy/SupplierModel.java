package com.city.erp.model.sxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Supplier")
//供应商
public class SupplierModel {
	@Id
	@Column(name="SupplierId")
	private String sid=null;//SupplierId 供应商编号
	
	@Column(name="SupplierName")
	private String sname=null;//SupplierName 供应商名称
	
	@Column(name="SupplierAddress")
	private String saddress=null;//SupplierAddress 供应商地址
	
	@Column(name="LinkmanName")
	private String lmname=null;//LinkmanName 联系人姓名
	
	@Column(name="LinkmanPhone")
	private String lmphone=null;//LinkmanPhone 联系人电话

	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getLmname() {
		return lmname;
	}
	public void setLmname(String lmname) {
		this.lmname = lmname;
	}
	public String getLmphone() {
		return lmphone;
	}
	public void setLmphone(String lmphone) {
		this.lmphone = lmphone;
	}
	
	
}
