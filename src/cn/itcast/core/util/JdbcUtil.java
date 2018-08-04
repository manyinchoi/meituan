package cn.itcast.core.util;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {

	//初始化连接池
	private static DataSource datasource;
	//数据库连接池应该只被初始化一次
	static{
		datasource=new ComboPooledDataSource();
	}
	public static DataSource getDataSource(){
		return datasource;
	}
	/**
	 * 创建Dbutil常用工具类对象
	 */
	public static QueryRunner getQueryRunner(){
		return new QueryRunner(datasource);
	}
}
