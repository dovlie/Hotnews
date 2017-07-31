package com.city.erp.model.myq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vip")
public class vipModel {
	@Id
	@Column(name="Management")
	private int management;
	
	@Column(name="MemType")
	private String memType=null;
	
	@Column(name="DepRate")
	private Double depRa=null;

	public int getManagement() {
		return management;
	}

	public void setManagement(int management) {
		this.management = management;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public Double getDepRa() {
		return depRa;
	}

	public void setDepRa(Double depRa) {
		this.depRa = depRa;
	}
	
	
}
