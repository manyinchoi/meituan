package cn.itcast.user.entity;

import java.util.Date;

public class Orders {
	private int order_id;//订单id
	private int shop_id;//店铺id
	private int user_id;//用户id
	private double order_price;//订单总价
	private int order_pay;//支付状态，默认未支付
	private int order_deli;//发货状态，默认未发货
	private int order_get;//收货状态，默认未收货
	private Date order_time;//下单时间
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public int getOrder_pay() {
		return order_pay;
	}
	public void setOrder_pay(int order_pay) {
		this.order_pay = order_pay;
	}
	public int getOrder_deli() {
		return order_deli;
	}
	public void setOrder_deli(int order_deli) {
		this.order_deli = order_deli;
	}
	public int getOrder_get() {
		return order_get;
	}
	public void setOrder_get(int order_get) {
		this.order_get = order_get;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	
	

}
