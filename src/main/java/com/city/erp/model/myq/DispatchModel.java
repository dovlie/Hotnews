package com.city.erp.model.myq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dispatch")
public class DispatchModel {
	@Id
	@Column(name="did")
	private String did;
	
	@Column(name="dname")
	private String dname=null;

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}
}
