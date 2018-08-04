package cn.itcast.admin.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.admin.dao.AdminDao;
import cn.itcast.admin.entity.Admin;
import cn.itcast.core.util.JdbcUtil;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.entity.User;

public class AdminDaoImpl implements AdminDao {
	
	/**
	 * 管理员登录验证
	 */
	@Override
	public boolean checkDao(Admin admin) {
		boolean flag=false;
		
		String sql = "select * from administrator "
				+ "where admin_account='"+admin.getAdmin_account()+"' "
						+ "and admin_pwd='"+admin.getAdmin_pwd()+"'";
		
		try {
			//把数据库查询结果放入list
			List<Admin> list=JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Admin>(Admin.class));
			System.out.println("集合长度为"+list.size());
			if(list.size() != 0) {
				flag= true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 商家账户信息分页查询
	 */
	@Override
	public void getShop(PageBean<ShopAccount> pb) {
		//查询总记录数;设置到pb对象中
		int totalCount=0;
		//this.getTotalCount(pb)表示调用本类的getTotalCount(pb)方法
	 	totalCount = this.getShopAccCount();
	 	
	 	//测试有没有得到总记录数
	 	System.out.println("getShop的totalcount:"+totalCount);
		
	 	pb.setTotalCount(totalCount);
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
		} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalCount() != 0){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		//获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		
		//sql
		String sql="select shopacc_id,shopacc_acc,shopacc_pwd,shopacc_state from shopaccount limit "+index+","+count;
		
		try {
			List<ShopAccount> pageData = JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<ShopAccount>(ShopAccount.class));
			// 设置到pb对象中
			pb.setPageData(pageData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 用户账户信息分页查询
	 */
	@Override
	public void getUser(PageBean<User> pb) {
		//查询总记录数;设置到pb对象中
				int totalCount=0;
				//this.getTotalCount(pb)表示调用本类的getTotalCount(pb)方法
			 	totalCount = this.getUserTC();
			 	
			 	//测试有没有得到总记录数
			 	System.out.println("UserTC的totalcount:"+totalCount);
				
			 	pb.setTotalCount(totalCount);
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
				} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalCount() != 0){
					pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
				}
				
				//获取当前页： 计算查询的起始行、返回的行数
				int currentPage = pb.getCurrentPage();
				int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
				int count = pb.getPageCount();							// 查询返回的行数
				
				//sql
				String sql="select user_id,user_name, user_pwd,user_pho,user_sex,user_add from userinfo limit "+index+","+count;
				
				try {
					List<User> pageData = JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<User>(User.class));
					// 设置到pb对象中
					pb.setPageData(pageData);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
	
	/**
	 * 商家店铺信息分页查询
	 */
	@Override
	public void getShopInfo(PageBean<Shop> pb) {
		//查询总记录数;设置到pb对象中
		int totalCount=0;
		//this.getTotalCount(pb)表示调用本类的getTotalCount(pb)方法
	 	totalCount = this.getShopInfoCount();
	 	
	 	//测试有没有得到总记录数
	 	System.out.println("getShopInfo的totalcount:"+totalCount);
		
	 	pb.setTotalCount(totalCount);
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
		} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalCount() != 0){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		//获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		
		//sql
		String sql="select shop_id,shopacc_id,style_id,shop_name,shop_add,shop_ctc,shop_pic,shop_desc from shop where shopacc_id not in ( select shopacc_id from shopaccount where shopacc_state=0 ) limit "+index+","+count;
		
		try {
			List<Shop> pageData = JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Shop>(Shop.class));
			// 设置到pb对象中
			pb.setPageData(pageData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 商家账户信息完整查询
	 */
	@Override
	public int getShopAccCount() {
		//sql
		String sql="select count(*) from shopaccount ";
		
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = JdbcUtil.getQueryRunner().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 用户账户信息完整查询
	 */
	public int getUserTC() {
		//sql
		String sql="select count(*) from userinfo ";
		
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = JdbcUtil.getQueryRunner().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 商家店铺信息完整查询
	 */
	public int getShopInfoCount() {
		//sql
		String sql="select count(*) from shop where shopacc_id not in ( select shopacc_id from shopaccount where shopacc_state=0 )";
		
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = JdbcUtil.getQueryRunner().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除商家帐户
	 */
	public void deleteShop(int id) {
		try {
			String sql ="DELETE FROM shopaccount WHERE shopacc_id=?";
			System.out.println(id);
			JdbcUtil.getQueryRunner().update(sql, id);
			System.out.println("删除商家账户成功");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除用户帐户
	 */
	public void deleteUser(int id) {
		try {
			String sql ="DELETE FROM userinfo WHERE user_id=?";
			System.out.println(id);
			JdbcUtil.getQueryRunner().update(sql, id);
			System.out.println("删除用户账户成功");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除商家店铺
	 */
	public void deleteShop2(int id) {
		try {
			//修改商家账户的开店状态
			String sql = "update shopaccount set shopacc_state=0 where shopacc_id=?";
			//删除店铺
			String sql2 = "delete from shop where shopacc_id=?";
			
			System.out.println(id);
			JdbcUtil.getQueryRunner().update(sql, id);
			System.out.println("修改开店状态成功");
			JdbcUtil.getQueryRunner().update(sql2, id);
			System.out.println("删除店铺成功");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 店铺申请分页查询
	 */
	@Override
	public void getShopApply(PageBean<Shop> pb) {
		//查询总记录数;设置到pb对象中
		int totalCount=0;
		//this.getTotalCount(pb)表示调用本类的getTotalCount(pb)方法
	 	totalCount = this.getShopApplyCount();
	 	
	 	//测试有没有得到总记录数
	 	System.out.println("getShopApply的totalcount:"+totalCount);
		
	 	pb.setTotalCount(totalCount);
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
		} else if (pb.getCurrentPage() > pb.getTotalPage() && pb.getTotalCount() != 0){
			pb.setCurrentPage(pb.getTotalPage());		// 把当前页设置为最大页数
		}
		
		//获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage -1 ) * pb.getPageCount();		// 查询的起始行
		int count = pb.getPageCount();							// 查询返回的行数
		
		//sql
		String sql="select shop_id,shopacc_id,style_id,shop_name,shop_add,shop_ctc,shop_pic,shop_desc from shop  where shopacc_id not in ( select shopacc_id from shopaccount where shopacc_state=1 ) limit "+index+","+count;
		
		try {
			List<Shop> pageData = JdbcUtil.getQueryRunner().query(sql, new BeanListHandler<Shop>(Shop.class));
			// 设置到pb对象中
			pb.setPageData(pageData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 店铺申请完整查询
	 */
	@Override
	public int getShopApplyCount() {
		//sql
		String sql="select count(*) from shop where shopacc_id not in ( select shopacc_id from shopaccount where shopacc_state=1 )";
		
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = JdbcUtil.getQueryRunner().query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 店铺申请通过
	 */
	@Override
	public void pass(int id) {
		try {
			String sql = "update shopaccount set shopacc_state=1 where shopacc_id=?";
			
			System.out.println(id);
			JdbcUtil.getQueryRunner().update(sql, id);
			System.out.println("通过店铺申请成功");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 店铺申请拒绝
	 */
	@Override
	public void notpass(int id) {
		try {
			String sql = "delete from shop where shopacc_id=?";
			
			System.out.println(id);
			JdbcUtil.getQueryRunner().update(sql, id);
			System.out.println("拒绝店铺申请成功");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
