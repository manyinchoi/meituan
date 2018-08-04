package cn.itcast.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.core.util.Condition;
import cn.itcast.core.util.JdbcUtil;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.dao.UserDao;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;
import cn.itcast.user.entity.Review;
import cn.itcast.user.entity.User;

public class UserDaoImpl implements UserDao {

	private QueryRunner qr = JdbcUtil.getQueryRunner();
	@Override
	public User checkLogin(String username,String password) {
		//创建一个对象
		User user = null;
		//sql语句
		String sql="select *  from userinfo "
				+ "where user_name='"+username+"' "
						+ "and user_pwd='"+password+"'";
		try {
			//
			//执行sql语句，返回一个user对象
			user=qr.query(sql, new BeanHandler<User>(User.class));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void register(User user) {
		// sql语句
		String sql="insert into userinfo(user_name,user_pwd,user_pho) values(?,?,?)";
		try {
			qr.update(sql, user.getUser_name(),user.getUser_pwd(),user.getUser_pho());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserMsg(int id) {
		//sql语句
		String sql="select * from userinfo where user_id = ?";
		//创建一个user对象
		User user = null;
		try {
			//执行sql语句，返回一个user对象
			user = qr.query(sql, new BeanHandler<User>(User.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		// sql语句
		String sql="update userinfo set user_name=?,user_pwd=?,user_pho=?,user_sex=?,user_add=? where user_id = ?";
		try {
			qr.update(sql, user.getUser_name(),user.getUser_pwd(),user.getUser_pho(),user.getUser_sex(),user.getUser_add(),user.getUser_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 分页获取店铺
	 */
	public void getAll(PageBean<Shop> pb) {
		
		//2. 查询总记录数;  设置到pb对象中
		int totalCount=0;
		totalCount = this.getTotalCount(pb);
		pb.setTotalCount(totalCount);
		
		List<Object> list = new ArrayList<Object>();
		
		// 判断
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalPage() !=0){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		//1. 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		//获取查询条件
		Condition condition = pb.getCondition();
		
		//3. 分页查询数据;  把查询到的数据设置到pb对象中
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append("     	shop_id,");
		sb.append("     	shopacc_id,");
		sb.append("     	style_id,");
		sb.append("     	shop_name,");
		sb.append("     	shop_add,");
		sb.append("     	shop_ctc,");
		sb.append("     	shop_pic,");
		sb.append("     	shop_desc");
		sb.append(" FROM ");
		sb.append("     	shop ");
		
		//判断
		if(condition!=null){             //对condition进行判空，不然会出现空指针异常
			int style_id = condition.getStyle_id();
			String shopname = condition.getShop_name();
			if(style_id !=0 && shopname !=null){
				sb.append("  where style_id="+style_id+"  AND shop_name LIKE '%"+shopname+"%'");
			}else if(style_id !=0){
				//类型不空
				sb.append("  where style_id="+style_id);
			}else if(shopname!=null && !shopname.isEmpty()){
				//关键字不空
				sb.append("  where shop_name LIKE '%"+shopname+"%'");
			}
		}
		sb.append(" limit ?,? ");
		list.add(index);
		list.add(count);
		try {
			// 根据当前页，查询当前页数据(一页数据)
			if(index>=0){
				//测试
				System.out.println("getallshopsql:"+sb.toString());
				List<Shop> pageData = qr.query(sb.toString(), new BeanListHandler<Shop>(Shop.class), list.toArray());
				//测试
				System.out.println("userdaoimplpagedate:"+pageData.size());
				// 设置到pb对象中
				pb.setPageData(pageData);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取数据库中相应商家店铺的菜品记录数
	 */
	@Override
	public int getTotalCount(PageBean<Shop> pb) {
		//创建StringBuilder对象来拼接查询语句
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT");
		sb.append("   count(*) ");
		sb.append(" FROM ");
		sb.append("     	shop ");
		
		//获取条件对象
		Condition condition = pb.getCondition();
		//判断
		if(condition!=null){            
			int style_id = condition.getStyle_id();
			String shopname = condition.getShop_name();
			if(style_id !=0 && shopname !=null){
				sb.append("  where style_id="+style_id+"  AND shop_name LIKE '%"+shopname+"%'");
			}else if(style_id !=0){
				//类型不空
				sb.append("  where style_id="+style_id);
			}else if(shopname!=null && !shopname.isEmpty()){
				//关键字不空
				sb.append("  where shop_name LIKE '%"+shopname+"%'");
			}
		}
		try {
			//测试
			System.out.println("userdaoimpl:totalcount:"+sb.toString());
			// 执行查询， 返回结果的第一行的第一列
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> getFood(int id) {
		// sql
		String sql="select * from food where shop_id=?";
		List<Food> list=null;
		try {
			list=qr.query(sql,new BeanListHandler<Food>(Food.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Review> getReview(int id) {
		// sql
		String sql="select * from review where shop_id=?";
		List<Review> list=null;
		try {
			list=qr.query(sql,new BeanListHandler<Review>(Review.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<User> getUser() {
		// sql
		String sql="select * from userinfo";
		List<User> list=null;
		try {
			list=qr.query(sql,new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Shop getShopMsg(int id) {
		//sql语句
		String sql="select * from shop where shop_id = ?";
		//创建一个shop对象
		Shop shop = null;
		try {
			//执行sql语句，返回一个shop对象
			shop = qr.query(sql, new BeanHandler<Shop>(Shop.class), id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
	}

	@Override
	public int getCount() {
		String sql ="select count(*) from uorder";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void add(Orders order) {
		//sql
		String sql="insert into uorder(order_id,shop_id,user_id,order_price,order_pay,order_deli,order_get,order_time) "
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql, order.getOrder_id(),order.getShop_id(),order.getUser_id(),order.getOrder_price(),1,0,0,order.getOrder_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void add(OrderDetail detail) {
		//sql
		String sql="insert into composition(order_id,food_id,food_count) values(?,?,?)";
		try {
			qr.update(sql, detail.getOrder_id(),detail.getFood_id(),detail.getFood_count());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void getOrder(PageBean<Orders> pb) {
		//2. 查询总记录数;  设置到pb对象中
		int totalCount=0;
		totalCount = this.getOrderCount(pb);
		pb.setTotalCount(totalCount);
		
		List<Object> list = new ArrayList<Object>();
		
		// 判断
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalPage() !=0){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		//1. 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		//获取查询条件
		Condition condition = pb.getCondition();
		
		//3. 分页查询数据;  把查询到的数据设置到pb对象中
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append("     	order_id,");
		sb.append("     	shop_id,");
		sb.append("     	user_id,");
		sb.append("     	order_price,");
		sb.append("     	order_pay,");
		sb.append("     	order_deli,");
		sb.append("     	order_get,");
		sb.append("     	order_time");
		sb.append(" FROM ");
		sb.append("     	uorder ");
		
		//判断
		if(condition!=null){         
			int user_id = condition.getUser_id();
			if(user_id !=0){
				sb.append(" where user_id="+user_id);
			}
		}
		sb.append(" limit ?,? ");
		list.add(index);
		list.add(count);
		try {
			// 根据当前页，查询当前页数据(一页数据)
			if(index>=0){
				List<Orders> pageData = qr.query(sb.toString(), new BeanListHandler<Orders>(Orders.class), list.toArray());
				// 设置到pb对象中
				pb.setPageData(pageData);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取数据库中相应用户的订单总记录数
	 */
	@Override
	public int getOrderCount(PageBean<Orders> pb) {
		//创建StringBuilder对象来拼接查询语句
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT");
		sb.append("   count(*) ");
		sb.append(" FROM ");
		sb.append("     	uorder ");
		
		//获取条件对象
		Condition condition = pb.getCondition();
		//判断
		if(condition!=null){
			int user_id = condition.getUser_id();
			if(user_id !=0){
				sb.append(" where user_id="+user_id);
			}
		}
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Orders> getOrderByUserId(int user_id) {
		//sql
		String sql="select * from uorder where user_id=?";
		List<Orders> order = null;
		try {
			order = qr.query(sql, new BeanListHandler<Orders>(Orders.class),user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void saveCommend(Review review) {
		//sql
		String sql="insert into review(shop_id,user_id,rev_time,rev_desc) values(?,?,?,?)";
		try {
			qr.update(sql, review.getShop_id(),review.getUser_id(),review.getRev_time(),review.getRev_desc());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void admitGet(int orderId) {
		//sql
		String sql="update uorder set order_get=? where order_id=?";
		try {
			qr.update(sql, 1,orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
