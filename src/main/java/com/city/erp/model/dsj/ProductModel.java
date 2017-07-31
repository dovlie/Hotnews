package com.city.erp.model.dsj;

import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.city.erp.model.sxy.ProductTypeModel;
import com.city.erp.model.sxy.WarehouseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class ProductModel {

	@Id
	@Column(name="pid")
	private String pid = null;
	@Column(name="pname")
	private String pname = null;
	@Column(name="photo")
	@JsonIgnore
	private Blob photo=null;
	private String photoFileName=null;
	private String photoContentType=null;
	@ManyToOne
	@JoinColumn(name="ProductTypeId")
	private ProductTypeModel producttype=null;
	@ManyToOne
	@JoinColumn(name="WarehoseId")
	private WarehouseModel warehose = null;
	@ManyToOne
	@JoinColumn(name="ppid")
	private PriceModel price = null;
	@ManyToOne
	@JoinColumn(name="oid")
	private OriginModel origin = null;
	@ManyToOne
	@JoinColumn(name="prid")
	private PromotionModel promotion = null;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	public ProductTypeModel getProducttype() {
		return producttype;
	}
	public void setProducttype(ProductTypeModel producttype) {
		this.producttype = producttype;
	}
	public WarehouseModel getWarehose() {
		return warehose;
	}
	public void setWarehose(WarehouseModel warehose) {
		this.warehose = warehose;
	}
	public PriceModel getPrice() {
		return price;
	}
	public void setPrice(PriceModel price) {
		this.price = price;
	}
	public OriginModel getOrigin() {
		return origin;
	}
	public void setOrigin(OriginModel origin) {
		this.origin = origin;
	}
	public PromotionModel getPromotion() {
		return promotion;
	}
	public void setPromotion(PromotionModel promotion) {
		this.promotion = promotion;
	}
}