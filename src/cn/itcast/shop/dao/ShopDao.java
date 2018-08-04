package cn.itcast.shop.dao;

import java.util.List;

import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.shop.entity.Style;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;

public interface ShopDao {
	/**
	 * 获取所有菜系
	 */
	public List<Style> getShopType();
	/**
	 * 根据id获取店铺信息
	 */
	public Shop getShopMsg(int id);
	/**
	 * 更新店铺信息
	 */
	public void updateShop(Shop shop);
	/**
	 * 分页查找菜品
	 */
	public void getAll(PageBean<Food> pageBean);
	/**
	 * 获取菜品的总记录数
	 */
	public int getTotalCount(PageBean<Food> pb);
	/**
	 * 通过账号id查找店铺
	 */
	public Shop getShopByAccountId(int id);
	/**
	 * 根据id查找食物
	 */
	public Food findById(int id);
	/**
	 * 更新食物信息
	 */
	public void updata(Food food);
	/**
	 * 添加一个菜品
	 */
	public void add(Food food);
	/**
	 * 根据id删除食物
	 */
	public void delete(int id);
	/**
	 * 获取所有订单
	 */
	public void getAllOrders(PageBean<Orders> pageBean);
	public int getOrderCount(PageBean<Orders> pb);
	/**
	 * 根据订单id查找订单详细，返回一个list集合
	 */
	public List<OrderDetail> findByOrderId(int id);
	/**
	 * 根据店铺id查找其所有菜品
	 */
	public List<Food> query(int shop_id);
	/**
	 * 确认已配送
	 */
	public void confirmDeli(int orderId);
	/**
	 * 把申请表shop的信息添加到数据库中
	 */
	public void addShopApply(Shop shop);
	/**
	 * 
	 *根据订单id获取订单详情
	 */
	public Orders getOrderByOrderId(String id);
}
