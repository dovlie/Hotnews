package com.city.erp.model.zqy;


	import javax.persistence.Column;
	import javax.persistence.Entity;
    import javax.persistence.Id;
    import javax.persistence.Table;



	@Entity
	@Table(name = "users")
	public class AdminidtratorModel {
		@Id
		@Column(name="uid")
		private String uid=null;//uid 用户编号
		
		@Column(name="pw")
		private String pw=null;//user 密码

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getPw() {
			return pw;
		}

		public void setPw(String pw) {
			this.pw = pw;
		}

		

		
	}
		
