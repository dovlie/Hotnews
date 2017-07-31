package com.city.erp.model.myq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class CustomerModel {
	@Id
	@Column(name="cid")
	private String cid;
	
	@Column(name="cname")
	private String cname=null;
	
	@Column(name="ctype")
	private String ctype=null;
	
	@Column(name="cphone")
	private String cphone=null;
	
	@ManyToOne
	@JoinColumn(name = "Management")
	private vipModel vip = null;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public vipModel getVip() {
		return vip;
	}

	public void setVip(vipModel vip) {
		this.vip = vip;
	}

}
