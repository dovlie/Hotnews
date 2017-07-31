package com.city.erp.model.sxy;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.city.erp.model.dsj.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "Warehouse")
//仓库管理
public class WarehouseModel {
	@Id
	@Column(name="WarehouseId")
	private String wid=null;// 仓库编号
	
	@Column(name="WarehouseAddress")
	private String waddress=null;// 仓库地址
	
	@Column(name="StewardName")
	private String sname=null;// 负责人姓名
	
	@Column(name="StewardPhone")
	private String sphone=null;// 负责人电话
	
	@JsonIgnore
	@OneToMany(mappedBy="warehose")
	private Set<ProductModel> products = null;
	
	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getWaddress() {
		return waddress;
	}

	public void setWaddress(String waddress) {
		this.waddress = waddress;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public Set<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductModel> products) {
		this.products = products;
	}
	
	

}
