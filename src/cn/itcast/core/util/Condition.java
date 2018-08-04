package cn.itcast.core.util;

public class Condition {

	//菜品所属商家店铺
	private int shop_id;
	//菜品名称
	private String food_name;
	//店铺类型
	private int style_id;
	//店铺名称
	private String shop_name;
	//用户id
	private int user_id;
	
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getFood_name() {
		return food_name;
	}
	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}
	public int getStyle_id() {
		return style_id;
	}
	public void setStyle_id(int style_id) {
		this.style_id = style_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	

}
