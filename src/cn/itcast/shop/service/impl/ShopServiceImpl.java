package cn.itcast.shop.service.impl;

import java.util.List;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.dao.ShopDao;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.shop.entity.Style;
import cn.itcast.shop.service.ShopService;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;

public class ShopServiceImpl implements ShopService {

	//定义一个dao
	private ShopDao shopDaoImpl = BeanFactory.getInstance("shopDaoImpl", ShopDao.class);
	
	@Override
	public List<Style> getShopType() {
		return shopDaoImpl.getShopType();
	}

	@Override
	public Shop getShopMsg(int id) {
		return shopDaoImpl.getShopMsg(id);
	}

	@Override
	public void updateShop(Shop shop) {
		shopDaoImpl.updateShop(shop);
	}

	@Override
	public void getAll(PageBean<Food> pageBean) {
		shopDaoImpl.getAll(pageBean);
	}

	@Override
	public Shop getShopByAccountId(int id) {
		return shopDaoImpl.getShopByAccountId(id);
	}

	@Override
	public Food findById(int id) {
		return shopDaoImpl.findById(id);
	}

	@Override
	public void updata(Food food) {
		shopDaoImpl.updata(food);
	}

	@Override
	public void add(Food food) {
		shopDaoImpl.add(food);
	}

	@Override
	public void delete(int id) {
		shopDaoImpl.delete(id);
	}

	@Override
	public void getAllOrders(PageBean<Orders> pageBean) {
		shopDaoImpl.getAllOrders(pageBean);
	}

	@Override
	public List<OrderDetail> findByOrderId(int id) {
		return shopDaoImpl.findByOrderId(id);
	}

	@Override
	public List<Food> query(int shop_id) {
		return shopDaoImpl.query(shop_id);
	}

	@Override
	public void confirmDeli(int orderId) {
		shopDaoImpl.confirmDeli(orderId);
	}

	@Override
	public void addShopApply(Shop shop) {
		shopDaoImpl.addShopApply(shop);
	}

	@Override
	public Orders getOrderByOrderId(String id) {
		return shopDaoImpl.getOrderByOrderId(id);
	}

}
