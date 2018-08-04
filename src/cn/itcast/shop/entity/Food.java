package cn.itcast.shop.entity;

import java.io.Serializable;

public class Food implements Serializable{
	
	private static final long serialVersionUID = 6580344012895398345L;
	
	private int food_id;
	private int shop_id;
	private String food_name;
	private double food_price;
	private String food_pic;
	private String food_desc;
	
	public int getFood_id() {
		return food_id;
	}

	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}

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

	public double getFood_price() {
		return food_price;
	}

	public void setFood_price(double food_price) {
		this.food_price = food_price;
	}

	public String getFood_pic() {
		return food_pic;
	}

	public void setFood_pic(String food_pic) {
		this.food_pic = food_pic;
	}

	public String getFood_desc() {
		return food_desc;
	}

	public void setFood_desc(String food_desc) {
		this.food_desc = food_desc;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food_desc == null) ? 0 : food_desc.hashCode());
		result = prime * result + food_id;
		result = prime * result + ((food_name == null) ? 0 : food_name.hashCode());
		result = prime * result + ((food_pic == null) ? 0 : food_pic.hashCode());
		long temp;
		temp = Double.doubleToLongBits(food_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + shop_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (food_desc == null) {
			if (other.food_desc != null)
				return false;
		} else if (!food_desc.equals(other.food_desc))
			return false;
		if (food_id != other.food_id)
			return false;
		if (food_name == null) {
			if (other.food_name != null)
				return false;
		} else if (!food_name.equals(other.food_name))
			return false;
		if (food_pic == null) {
			if (other.food_pic != null)
				return false;
		} else if (!food_pic.equals(other.food_pic))
			return false;
		if (Double.doubleToLongBits(food_price) != Double.doubleToLongBits(other.food_price))
			return false;
		if (shop_id != other.shop_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Food [food_id=" + food_id + ", shop_id=" + shop_id + ", food_name=" + food_name + ", food_price="
				+ food_price + ", food_pic=" + food_pic + ", food_desc=" + food_desc + "]";
	}
	

}
