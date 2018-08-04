package cn.itcast.admin.entity;

public class Admin {
	private String admin_account;
	private String admin_pwd;
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_pwd() {
		return admin_pwd;
	}
	public void setAdmin_pwd(String admin_pwd) {
		this.admin_pwd = admin_pwd;
	}
	public Admin(String admin_account, String admin_pwd) {
		this.admin_account = admin_account;
		this.admin_pwd = admin_pwd;
	}
	public Admin() {
	}

	
}
