package com.city.erp.model.zqy;


	import javax.persistence.Column;
	import javax.persistence.Entity;
    import javax.persistence.Id;
    import javax.persistence.Table;



	@Entity
	@Table(name = "finance")
	public class FinanceModel {
		@Id
		@Column(name="fid")
		private String  fid=null;//fid财务编号
		
		@Column(name="fleixing")
		private String fleixing=null;//fleixing财务类型
		
		@Column(name="fmoney")
		private String fmoney=null;//fmoney财务金额

		public String getFid() {
			return fid;
		}

		public void setFid(String fid) {
			this.fid = fid;
		}

		public String getFleixing() {
			return fleixing;
		}

		public void setFleixing(String fleixing) {
			this.fleixing = fleixing;
		}

		public String getFmoney() {
			return fmoney;
		}

		public void setFmoney(String fmoney) {
			this.fmoney = fmoney;
		}

		
	}