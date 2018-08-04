package cn.itcast.shop.service;

import cn.itcast.shop.entity.ShopAccount;

public interface ShopAccountService {
	/**
	 * 登录验证
	 */
	public ShopAccount checkLogin(String username,String password);
	
	/**
	 * 用户注册
	 */
	public void register(ShopAccount shopAccount);
	
	/**
	 * 根据id获取商家信息
	 */
	public ShopAccount getShopAccMsg(int id);

	/**
	 * 更新商家账号信息
	 */
	public void updateShopAcc(ShopAccount shopAccount);

}
