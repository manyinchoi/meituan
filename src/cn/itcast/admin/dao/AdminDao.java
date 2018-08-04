package cn.itcast.admin.dao;

import cn.itcast.admin.entity.Admin;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.entity.User;

public interface AdminDao {
	public boolean checkDao(Admin admin);
	public void getShop(PageBean<ShopAccount> pb);
	public void getUser(PageBean<User> pb);
	public void getShopInfo(PageBean<Shop> pb);
	public void getShopApply(PageBean<Shop> pb);
	public int getShopAccCount();
	public int getUserTC();
	public int getShopInfoCount();
	public int getShopApplyCount();
	public void deleteShop(int id);
	public void deleteUser(int id);
	public void deleteShop2(int id);
	public void pass(int id);
	public void notpass(int id);
}
