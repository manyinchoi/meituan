package cn.itcast.user.entity;

public class User {
	private int user_id;
	private String user_name;
	private String user_pwd;
	private String user_pho;
	private String user_sex;
	private String user_add;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_pho() {
		return user_pho;
	}
	public void setUser_pho(String user_pho) {
		this.user_pho = user_pho;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_add() {
		return user_add;
	}
	public void setUser_add(String user_add) {
		this.user_add = user_add;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_pwd=" + user_pwd + ", user_pho="
				+ user_pho + ", user_sex=" + user_sex + ", user_add=" + user_add + "]";
	}
	
	
	
}
	
