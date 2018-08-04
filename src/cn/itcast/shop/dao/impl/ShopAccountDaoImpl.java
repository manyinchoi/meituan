package cn.itcast.shop.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.core.util.JdbcUtil;
import cn.itcast.shop.dao.ShopAccountDao;
import cn.itcast.shop.entity.ShopAccount;

public class ShopAccountDaoImpl implements ShopAccountDao {

	private QueryRunner qr = JdbcUtil.getQueryRunner();
	@Override
	public ShopAccount checkLogin(String username,String password) {
		//创建一个对象
		ShopAccount shopAccount = null;
		//sql语句
		String sql="select *  from shopaccount "
				+ "where shopacc_acc='"+username+"' "
						+ "and shopacc_pwd='"+password+"'";
		try {
			//
			//执行sql语句，返回一个user对象
			shopAccount=qr.query(sql, new BeanHandler<ShopAccount>(ShopAccount.class));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopAccount;
	}

	@Override
	public void register(ShopAccount shopAccount) {
		// sql语句
		String sql="insert into shopaccount(shopacc_acc,shopacc_pwd,shopacc_state) values(?,?,?)";
		try {
			//注册时，默认开店状态为0，即没有开店
			qr.update(sql,shopAccount.getShopacc_acc(),shopAccount.getShopacc_pwd(),0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ShopAccount getShopAccMsg(int id) {
		//sql语句
		String sql="select * from shopaccount where shopacc_id = ?";
		ShopAccount shopAccount=null;
		try {
			shopAccount = qr.query(sql, new BeanHandler<ShopAccount>(ShopAccount.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shopAccount;
	}

	@Override
	public void updateShopAcc(ShopAccount shopAccount) {
		//sql
		String sql="update shopaccount set shopacc_acc=?,shopacc_pwd=? where shopacc_id=?";
		try {
			qr.update(sql, shopAccount.getShopacc_acc(),shopAccount.getShopacc_pwd(),shopAccount.getShopacc_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
