package com.city.erp.model.dsj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="publicity")
public class PublicityModel {

	@Id
	@Column(name="puid")
	private String puid=null;
	
	@Column(name="puname")
	private String puname=null;
	
	@Column(name="puway")
	private String puway=null;
	
	@Column(name="pucost")
	private String pucost=null;
	
	@Column(name="puremarks")
	private String puremarks=null;

	public String getPuid() {
		return puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public String getPuname() {
		return puname;
	}

	public void setPuname(String puname) {
		this.puname = puname;
	}

	public String getPuway() {
		return puway;
	}

	public void setPuway(String puway) {
		this.puway = puway;
	}

	public String getPucost() {
		return pucost;
	}

	public void setPucost(String pucost) {
		this.pucost = pucost;
	}

	public String getPuremarks() {
		return puremarks;
	}

	public void setPuremarks(String puremarks) {
		this.puremarks = puremarks;
	}
	
}
