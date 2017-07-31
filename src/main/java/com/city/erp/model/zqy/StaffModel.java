package com.city.erp.model.zqy;


	import javax.persistence.Column;
	import javax.persistence.Entity;
    import javax.persistence.Id;
    import javax.persistence.Table;



	@Entity
	@Table(name = "staff")
	public class StaffModel {
		@Id
		@Column(name="sid")
		private String  sid=null;//sid员工编号
		
		@Column(name="sname")
		private String sname=null;//sname员工姓名
		
		@Column(name="sphone")
		private String sphone=null;//sphone员工手机
		
		@Column(name="sbranch")
		private String sbranch=null;//sbranch员工部门

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
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

		public String getSbranch() {
			return sbranch;
		}

		public void setSbranch(String sbranch) {
			this.sbranch = sbranch;
		}

		
	}