package com.city.erp.model.dsj;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="promotion")
public class PromotionModel {

	@Id
	@Column(name="prid")
	private String prid=null;
	
	@Column(name="prtype")
	private String prtype=null;
	
	@Column(name="prprice")
	private double prprice;
	
	@Column(name="rank")
	private String rank = null;
	
	@Column(name="remarks")
	private String remarks = null;
	
	@JsonIgnore
	@OneToMany(mappedBy="promotion")
	private Set<ProductModel> product=null;
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<ProductModel> getProduct() {
		return product;
	}

	public void setProduct(Set<ProductModel> product) {
		this.product = product;
	}

	public String getPrid() {
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}

	public String getPrtype() {
		return prtype;
	}

	public void setPrtype(String prtype) {
		this.prtype = prtype;
	}

	public double getPrprice() {
		return prprice;
	}

	public void setPrprice(double prprice) {
		this.prprice = prprice;
	}
	
}
