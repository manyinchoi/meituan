package cn.itcast.shop.dao.impl;

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
import cn.itcast.shop.dao.ShopDao;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.shop.entity.Style;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;

public class ShopDaoImpl implements ShopDao {

	private QueryRunner qr = JdbcUtil.getQueryRunner();
	
	@Override
	public List<Style> getShopType() {
		//定义一个集合
		List<Style> list = null;
		
		// sql语句
		String sql="select * from style";
		
		try {
			list = qr.query(sql, new BeanListHandler<Style>(Style.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Shop getShopMsg(int id) {
		//sql语句
		String sql="select * from shop where shopacc_id = ?";
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
	public void updateShop(Shop shop) {
		// sql语句
		String sql="update shop set style_id=?,shop_name=?,shop_add=?,shop_ctc=?,shop_pic=?,shop_desc=? where shop_id=?";
		try {
			qr.update(sql,shop.getStyle_id(),shop.getShop_name(),shop.getShop_add(),shop.getShop_ctc(),shop.getShop_pic(),shop.getShop_desc(),shop.getShop_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 分页获取菜品
	 */
	public void getAll(PageBean<Food> pb) {
		
		//2. 查询总记录数;  设置到pb对象中
		int totalCount=0;
		totalCount = this.getTotalCount(pb);
		pb.setTotalCount(totalCount);
		
		List<Object> list = new ArrayList<Object>();
		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！
		 *              如果当前页为末页，再点下一页显示有问题！
		 * 解决：
		 * 	   1. 如果当前页 <= 0;       当前页设置当前页为1;
		 * 	   2. 如果当前页 > 最大页数；  当前页设置为最大页数
		 */
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
		//String sql = "select * from food limit ?,?";
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT");
		sb.append("     	food_id,");
		sb.append("     	shop_id,");
		sb.append("     	food_name,");
		sb.append("     	food_price,");
		sb.append("     	food_pic,");
		sb.append("     	food_desc");
		sb.append(" FROM ");
		sb.append("     	food ");
		
		//判断
		if(condition!=null){
			int shop_id = condition.getShop_id();
			if(shop_id !=0){
				sb.append(" where shop_id="+shop_id);
			}
			String foodName = condition.getFood_name();
			if(foodName!=null && !foodName.isEmpty()){
				sb.append("  and food_name LIKE '%"+foodName+"%'");
			}
		}
		sb.append(" limit ?,? ");
		list.add(index);
		list.add(count);
		System.out.println("totalCount:"+totalCount+";currentpage:"+pb.getCurrentPage());
		System.out.println("getAllFoodsql:"+sb.toString()+";list:"+list.toString());
		try {
			// 根据当前页，查询当前页数据(一页数据)
			if(index>=0){
				List<Food> pageData = qr.query(sb.toString(), new BeanListHandler<Food>(Food.class), list.toArray());
				// 设置到pb对象中
				pb.setPageData(pageData);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取数据库中相应商家店铺的菜品记录数(未完，待改)
	 */
	@Override
	public int getTotalCount(PageBean<Food> pb) {
		//创建StringBuilder对象来拼接查询语句
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT");
		sb.append("   count(*) ");
		sb.append(" FROM ");
		sb.append("     	food ");
		
		//获取条件对象
		Condition condition = pb.getCondition();
		//判断
		if(condition!=null){             //对condition进行判空，不然会出现空指针异常
			int shop_id = condition.getShop_id();
			if(shop_id !=0){
				sb.append("  where shop_id="+shop_id);
			}
			String foodName = condition.getFood_name();
			if(foodName!=null && !foodName.isEmpty()){
				sb.append("  AND food_name LIKE '%"+foodName+"%'");
			}
		}
		try {
			System.out.println("getTotalCountsql:"+sb.toString());
			// 执行查询， 返回结果的第一行的第一列
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Shop getShopByAccountId(int id) {
		
		// sql
		String sql="select * from shop where shopacc_id=?";
		
		Shop shop = new Shop();
		try {
			shop = qr.query(sql, new BeanHandler<Shop>(Shop.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
	}

	@Override
	public Food findById(int id) {
		// sql
		String sql="select * from food where food_id=?";
		Food food = new Food();
		try {
			food = qr.query(sql, new BeanHandler<Food>(Food.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return food;
	}

	@Override
	public void updata(Food food) {
		//sql
		String sql="update food set food_name=?,food_price=?,food_pic=?,food_desc=? where food_id=?";
		try {
			qr.update(sql, food.getFood_name(),food.getFood_price(),food.getFood_pic(),food.getFood_desc(),food.getFood_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void add(Food food) {
		//sql
		String sql="insert into food(shop_id,food_name,food_price,food_pic,food_desc) values(?,?,?,?,?)";
		try {
			qr.update(sql, food.getShop_id(),food.getFood_name(),food.getFood_price(),food.getFood_pic(),food.getFood_desc());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		// sql
		String sql="delete from food where food_id=?";
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAllOrders(PageBean<Orders> pb) {
		
		//2. 查询总记录数;  设置到pb对象中
		int totalCount = this.getOrderCount(pb);
		pb.setTotalCount(totalCount);
		
		// 判断
		if (pb.getCurrentPage() <=0) {
			pb.setCurrentPage(1);					    // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalPage()!=0)
		{//注意，当没有订单数据时，即页数为0，那么当前页1就大于0，所以会有错误！！所以加一个条件
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}

		//1. 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		
		//获取查询条件
		Condition condition =pb.getCondition();
		
		//3. 分页查询数据;  把查询到的数据设置到pb对象中
		//String sql = "select * from food limit ?,?";
		StringBuilder sb = new StringBuilder();
		//创建list对象来存储查询条件
		List<Object> list = new ArrayList<Object>();
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
		sb.append("     	uorder");
		
		//判断
		if(condition!=null){
			int shop_id = condition.getShop_id();
			if(shop_id !=0){
				sb.append(" WHERE 	shop_id=? ");
				list.add(shop_id);
			}
		}
		sb.append(" limit ?,? ");
		list.add(index);
		list.add(count);
		
		try {
			// 根据当前页，查询当前页数据(一页数据)
			List<Orders> pageData = qr.query(sb.toString(), new BeanListHandler<Orders>(Orders.class),list.toArray());
			// 设置到pb对象中
			pb.setPageData(pageData);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int getOrderCount(PageBean<Orders> pb) {
		//创建StringBuilder对象来拼接查询语句
		StringBuilder sb = new StringBuilder();
		//创建list对象来存储查询条件
		List<Object> list = new ArrayList<Object>();
		sb.append(" SELECT");
		sb.append("   count(*) ");
		sb.append(" FROM ");
		sb.append("     	uorder ");
		
		//获取条件对象
		Condition condition = pb.getCondition();
		//判断
		if(condition!=null){             //对condition进行判空，不然会出现空指针异常
			int shop_id = condition.getShop_id();
			if(shop_id !=0){
				sb.append(" where shop_id=?");
				list.add(shop_id);
			}
		}
		try {
			System.out.println("ordercount:"+sb.toString()+list.toString());
			// 执行查询， 返回结果的第一行的第一列
			Long count = qr.query(sb.toString(), new ScalarHandler<Long>(),list.toArray());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<OrderDetail> findByOrderId(int id) {
		try {
			String sql ="SELECT * FROM composition where order_id=?";
			return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> query(int shop_id) {
		try {
			String sql ="SELECT * FROM food where shop_id=?;";
			return qr.query(sql,new BeanListHandler<Food>(Food.class),shop_id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void confirmDeli(int orderId) {
		//sql
		String sql="update uorder set order_deli=? where order_id=?";
		try {
			qr.update(sql, 1,orderId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addShopApply(Shop shop) {
		//sql
		String sql="insert into shop(shopacc_id,style_id,shop_name,shop_add,shop_ctc,shop_pic,shop_desc) values(?,?,?,?,?,?,?)";
		try {
			qr.update(sql, shop.getShopacc_id(),shop.getStyle_id(),shop.getShop_name(),shop.getShop_add(),shop.getShop_ctc(),shop.getShop_pic(),shop.getShop_desc());
			System.out.println("提交店铺申请成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Orders getOrderByOrderId(String id) {
		String sql = "select * from uorder where order_id=?";
		Orders order=null;
		try {
			order = qr.query(sql, new BeanHandler<Orders>(Orders.class),id);
			System.out.println("查询订单详情成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

}
