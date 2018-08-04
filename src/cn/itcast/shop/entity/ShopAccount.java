package cn.itcast.shop.entity;

public class ShopAccount {
	private int shopacc_id;
	private String shopacc_acc; //商家账号
	private String shopacc_pwd; //密码
	private int shopacc_state; //开店状态
	public int getShopacc_id() {
		return shopacc_id;
	}
	public void setShopacc_id(int shopacc_id) {
		this.shopacc_id = shopacc_id;
	}
	public String getShopacc_acc() {
		return shopacc_acc;
	}
	public void setShopacc_acc(String shopacc_acc) {
		this.shopacc_acc = shopacc_acc;
	}
	public String getShopacc_pwd() {
		return shopacc_pwd;
	}
	public void setShopacc_pwd(String shopacc_pwd) {
		this.shopacc_pwd = shopacc_pwd;
	}
	public int getShopacc_state() {
		return shopacc_state;
	}
	public void setShopacc_state(int shopacc_state) {
		this.shopacc_state = shopacc_state;
	}
	
	

}
