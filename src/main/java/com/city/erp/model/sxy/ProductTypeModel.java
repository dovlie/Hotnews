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
@Table(name = "ProductType")
//产品类型
public class ProductTypeModel {
	@Id
	@Column(name="ProductTypeId")
	private String ptid=null;//产品类型编号
		
	@Column(name="ProductTypeName")
	private String ptname=null;// 产品类型名称
	
	@JsonIgnore
	@OneToMany(mappedBy="producttype")
	private Set<ProductModel> products = null;
	
	public String getPtid() {
		return ptid;
	}

	public void setPtid(String ptid) {
		this.ptid = ptid;
	}

	public String getPtname() {
		return ptname;
	}

	public void setPtname(String ptname) {
		this.ptname = ptname;
	}

	public Set<ProductModel> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductModel> products) {
		this.products = products;
	}
	
	
	

}
