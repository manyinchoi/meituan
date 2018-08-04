package cn.itcast.admin.service.impl;

import cn.itcast.admin.dao.AdminDao;
import cn.itcast.admin.entity.Admin;
import cn.itcast.admin.service.AdminService;
import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.entity.User;

public class AdminServiceImpl implements AdminService {
	
	private AdminDao adminDaoImpl = BeanFactory.getInstance("adminDaoImpl", AdminDao.class); 
	
	/**
	 * 管理员登录验证
	 */
	@Override
	public boolean checkLogin(Admin admin) {
		return adminDaoImpl.checkDao(admin);
	}
	
	/**
	 * 商家账户信息查询
	 */
	@Override
	public void getShop(PageBean<ShopAccount> pb) {
		adminDaoImpl.getShop(pb);
	}

	/**
	 * 用户账户信息查询
	 */
	@Override
	public void getUser(PageBean<User> pageBean) {
		adminDaoImpl.getUser(pageBean);
	}

	/**
	 * 商家店铺信息查询
	 */
	@Override
	public void getShopInfo(PageBean<Shop> pageBean) {
		adminDaoImpl.getShopInfo(pageBean);
	}
	
	/**
	 * 管理商家账号操作中的删除
	 */
	@Override
	public void deleteShop(int id) {
		adminDaoImpl.deleteShop(id);
	}
	
	/**
	 * 管理用户账号操作中的删除
	 */
	@Override
	public void deleteUser(int id) {
		adminDaoImpl.deleteUser(id);
	}
	
	/**
	 * 管理商家店铺操作中的删除
	 */
	@Override
	public void deleteShop2(int id) {
		adminDaoImpl.deleteShop2(id);
	}
	
	/**
	 * 查看店铺申请
	 */
	@Override
	public void getShopApply(PageBean<Shop> pageBean) {
		adminDaoImpl.getShopApply(pageBean);
	}
	
	/**
	 * 通过店铺申请
	 */
	@Override
	public void pass(int id) {
		adminDaoImpl.pass(id);
	}
	
	/**
	 * 通过店铺申请
	 */
	@Override
	public void notpass(int id) {
		adminDaoImpl.notpass(id);
	}
}
