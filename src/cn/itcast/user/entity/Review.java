package cn.itcast.user.entity;

import java.util.Date;

public class Review {
	private int rev_id;
	private int shop_id;
	private int user_id;
	private Date rev_time;
	private String rev_desc;
	public int getRev_id() {
		return rev_id;
	}
	public void setRev_id(int rev_id) {
		this.rev_id = rev_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getRev_time() {
		return rev_time;
	}
	public void setRev_time(Date rev_time) {
		this.rev_time = rev_time;
	}
	public String getRev_desc() {
		return rev_desc;
	}
	public void setRev_desc(String rev_desc) {
		this.rev_desc = rev_desc;
	}
	
	

}
