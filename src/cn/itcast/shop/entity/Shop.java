package cn.itcast.shop.entity;

public class Shop {
	private int shop_id;
	private int shopacc_id; //商家账号id
	private int style_id; //商家类型id
	private String shop_name; //店名
	private String shop_add; //地址
	private String shop_ctc; //联系电话
	private String shop_pic; //图片路径
	private String shop_desc;//商家简介
	
	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public int getShopacc_id() {
		return shopacc_id;
	}

	public void setShopacc_id(int shopacc_id) {
		this.shopacc_id = shopacc_id;
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

	public String getShop_add() {
		return shop_add;
	}

	public void setShop_add(String shop_add) {
		this.shop_add = shop_add;
	}

	public String getShop_ctc() {
		return shop_ctc;
	}

	public void setShop_ctc(String shop_ctc) {
		this.shop_ctc = shop_ctc;
	}

	public String getShop_pic() {
		return shop_pic;
	}

	public void setShop_pic(String shop_pic) {
		this.shop_pic = shop_pic;
	}

	public String getShop_desc() {
		return shop_desc;
	}

	public void setShop_desc(String shop_desc) {
		this.shop_desc = shop_desc;
	}

	@Override
	public String toString() {
		return "Shop [shop_id=" + shop_id + ", shopacc_id=" + shopacc_id + ", style_id=" + style_id + ", shop_name="
				+ shop_name + ", shop_add=" + shop_add + ", shop_ctc=" + shop_ctc + ", shop_pic=" + shop_pic
				+ ", shop_desc=" + shop_desc + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shop_add == null) ? 0 : shop_add.hashCode());
		result = prime * result + ((shop_ctc == null) ? 0 : shop_ctc.hashCode());
		result = prime * result + ((shop_desc == null) ? 0 : shop_desc.hashCode());
		result = prime * result + shop_id;
		result = prime * result + ((shop_name == null) ? 0 : shop_name.hashCode());
		result = prime * result + ((shop_pic == null) ? 0 : shop_pic.hashCode());
		result = prime * result + shopacc_id;
		result = prime * result + style_id;
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
		Shop other = (Shop) obj;
		if (shop_add == null) {
			if (other.shop_add != null)
				return false;
		} else if (!shop_add.equals(other.shop_add))
			return false;
		if (shop_ctc == null) {
			if (other.shop_ctc != null)
				return false;
		} else if (!shop_ctc.equals(other.shop_ctc))
			return false;
		if (shop_desc == null) {
			if (other.shop_desc != null)
				return false;
		} else if (!shop_desc.equals(other.shop_desc))
			return false;
		if (shop_id != other.shop_id)
			return false;
		if (shop_name == null) {
			if (other.shop_name != null)
				return false;
		} else if (!shop_name.equals(other.shop_name))
			return false;
		if (shop_pic == null) {
			if (other.shop_pic != null)
				return false;
		} else if (!shop_pic.equals(other.shop_pic))
			return false;
		if (shopacc_id != other.shopacc_id)
			return false;
		if (style_id != other.style_id)
			return false;
		return true;
	}
	

}
