package cn.itcast.user.dao;

import java.util.List;

import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;
import cn.itcast.user.entity.Review;
import cn.itcast.user.entity.User;

public interface UserDao {
	/**
	 * 登录验证
	 */
	public User checkLogin(String username,String password);
	
	/**
	 * 用户注册
	 */
	public void register(User user);

	/**
	 * 根据id查找用户
	 */
	public User getUserMsg(int id);

	/**
	 * 更新用户信息
	 */
	public void updateUser(User user);
	/**
	 * 分页获取店铺
	 */
	public void getAll(PageBean<Shop> pageBean);
	public int getTotalCount(PageBean<Shop> pb);
	/**
	 * 通过店铺id查找菜品
	 */
	public List<Food> getFood(int id);

	/**
	 * 通过店铺id查找评论
	 */
	public List<Review> getReview(int id);

	/**
	 * 查找所有用户
	 */
	public List<User> getUser();
	/**
	 * 根据店铺id查找店铺信息
	 */
	public Shop getShopMsg(int id);
	/**
	 * 获取订单数量
	 */
	public int getCount();

	/**
	 * 添加一个新的订单
	 */
	public void add(Orders order);

	/**
	 * 添加一个新的订单详情
	 */
	public void add(OrderDetail detail);
	/**
	 * 分页获取用户订单
	 */
	public void getOrder(PageBean<Orders> pageBean);

	/**
	 * 获取用户订单的总记录数
	 */
	public int getOrderCount(PageBean<Orders> pb);
	/**
	 * 根据用户id查找订单
	 */
	public List<Orders> getOrderByUserId(int user_id);
	/**
	 * 保存用户评论
	 */
	public void saveCommend(Review review);
	/**
	 * 用户确认收货，改变订单的收货状态
	 */
	public void admitGet(int orderId);

}
