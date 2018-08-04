package cn.itcast.user.entity;

public class OrderDetail {
	private  int  comp_id; //  -- 主键
	private int	order_id;//   -- 外键：引入的是订单表的主键
	private  int food_id ;//-- 外键：引用的是菜信息表的主键
	private int  food_count; //-- 菜的数量
	public int getComp_id() {
		return comp_id;
	}
	public void setComp_id(int comp_id) {
		this.comp_id = comp_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getFood_count() {
		return food_count;
	}
	public void setFood_count(int food_count) {
		this.food_count = food_count;
	}
	

}
