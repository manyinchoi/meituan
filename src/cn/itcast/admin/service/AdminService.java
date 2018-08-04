package cn.itcast.admin.service;

import cn.itcast.admin.entity.Admin;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Shop;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.user.entity.User;

public interface AdminService {
	public boolean checkLogin(Admin admin);
	public void getShop(PageBean<ShopAccount> pageBean);
	public void getUser(PageBean<User> pageBean);
	public void getShopInfo(PageBean<Shop> pageBean);
	public void getShopApply(PageBean<Shop> pageBean);
	public void deleteShop(int id);
	public void deleteUser(int id);
	public void deleteShop2(int id);
	public void pass(int id);
	public void notpass(int id);
}
