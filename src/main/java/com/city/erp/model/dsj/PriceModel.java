package com.city.erp.model.dsj;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="price")
public class PriceModel {

	@Id
	@Column(name="pid")
	private String pid=null;
	
	@Column(name="price")
	private double price;
	
	@Column(name="ptype")
	private String ptype=null;
	
	@Column(name="prank")
	private String prank=null;
	
	@Column(name="premarks")
	private String premarks=null;
	
	@JsonIgnore
	@OneToMany(mappedBy="price")
	private Set<ProductModel> product=null;

	
	
	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPrank() {
		return prank;
	}

	public void setPrank(String prank) {
		this.prank = prank;
	}

	public String getPremarks() {
		return premarks;
	}

	public void setPremarks(String premarks) {
		this.premarks = premarks;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<ProductModel> getProduct() {
		return product;
	}

	public void setProduct(Set<ProductModel> product) {
		this.product = product;
	}
	
	
	
}
