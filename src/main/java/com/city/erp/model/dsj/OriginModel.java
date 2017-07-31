package com.city.erp.model.dsj;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="origin")
public class OriginModel {

	@Id
	@Column(name="oid")
	private String oid=null;
	
	@Column(name="ocountry")
	private String ocountry=null;
	
	@Column(name="province")
	private String province=null;
	
	@Column(name="city")
	private String city=null;
	
	@Column(name="country")
	private String country=null;
	
	@JsonIgnore
	@OneToMany(mappedBy="origin")
	private Set<ProductModel> product=null;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOcountry() {
		return ocountry;
	}

	public void setOcountry(String ocountry) {
		this.ocountry = ocountry;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<ProductModel> getProduct() {
		return product;
	}

	public void setProduct(Set<ProductModel> product) {
		this.product = product;
	}
	
	
}
