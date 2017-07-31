package com.city.erp.model.myq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.city.erp.model.dsj.ProductModel;
import com.city.erp.model.sxy.ProductTypeModel;

@Entity
@Table(name = "orders")
public class OrdersModel {
	@Id
	@Column(name="oid")
	private String oid = null;
	
	@ManyToOne
	@JoinColumn(name="pid")
	private ProductModel product=null;
	
	@ManyToOne
	@JoinColumn(name="did")
	private DispatchModel dispatch=null;
	
	@ManyToOne
	@JoinColumn(name="cid")
	private CustomerModel customer=null;

	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public ProductModel getProduct() {
		return product;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public DispatchModel getDispatch() {
		return dispatch;
	}
	public void setDispatch(DispatchModel dispatch) {
		this.dispatch = dispatch;
	}
	public CustomerModel getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	
}
