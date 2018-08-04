package cn.itcast.user.service.impl;

import java.util.List;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.dao.UserDao;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;
import cn.itcast.user.entity.Review;
import cn.itcast.user.entity.User;
import cn.itcast.user.service.UserService;

public class UserServiceImpl implements UserService {
	//产生一个dao对象
	private UserDao userDaoImpl=BeanFactory.getInstance("userDaoImpl", UserDao.class);

	@Override
	public User checkLogin(String username,String password) {
		return userDaoImpl.checkLogin(username,password);
	}

	@Override
	public void register(User user) {
		userDaoImpl.register(user);
	}

	@Override
	public User getUserMsg(int id) {
		return userDaoImpl.getUserMsg(id);
	}

	@Override
	public void updateUser(User user) {
		userDaoImpl.updateUser(user);
	}

	@Override
	public void getAll(PageBean<Shop> pageBean) {
		userDaoImpl.getAll(pageBean);
	}

	@Override
	public List<Food> getFood(int id) {
		return userDaoImpl.getFood(id);
	}

	@Override
	public List<Review> getReview(int id) {
		return userDaoImpl.getReview(id);
	}

	@Override
	public List<User> getUser() {
		return userDaoImpl.getUser();
	}

	@Override
	public Shop getShopMsg(int id) {
		return userDaoImpl.getShopMsg(id);
	}

	@Override
	public int getCount() {
		return userDaoImpl.getCount();
	}

	@Override
	public void add(Orders order) {
		userDaoImpl.add(order);
	}

	@Override
	public void add(OrderDetail detail) {
		userDaoImpl.add(detail);
	}

	@Override
	public void getOrder(PageBean<Orders> pageBean) {
		userDaoImpl.getOrder(pageBean);
		
	}

	@Override
	public List<Orders> getOrderByUserId(int user_id) {
		return userDaoImpl.getOrderByUserId(user_id);
	}

	@Override
	public void saveCommend(Review review) {
		userDaoImpl.saveCommend(review);
	}

	@Override
	public void admitGet(int orderId) {
		userDaoImpl.admitGet(orderId);
	}

}
