package cn.itcast.shop.servlet;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.servlet.BaseServlet;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.service.ShopAccountService;
import cn.itcast.user.entity.User;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ShopAccountServlet")
public class ShopAccountServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	//产生service对象
	private ShopAccountService shopAccountServiceImpl = BeanFactory.getInstance("shopAccountServiceImpl", ShopAccountService.class);

  
	/**
	 * 登录
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取登录参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//创建一个对象
		ShopAccount shopAccount =  new ShopAccount();
		
		//调用service层方法，判断
		if((shopAccount=shopAccountServiceImpl.checkLogin(username,password)) !=null){
			//为真，登录成功，保存商家账号的id和账号到session域中
			request.getSession().setAttribute("SHOP_ACCOUNT", username);
			request.getSession().setAttribute("SHOP_ACCOUNT_ID",shopAccount.getShopacc_id());
			//设置跳转路径，到商家中心去
			uri="/shopSys/shopcenter/frame.jsp";
		}else{
			//失败
			uri="/shopSys/login.jsp";
		}
		return uri;
	}
	
	/**.
	 * 注册
	 */
	public Object register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取登录参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//封装成user对象
		ShopAccount shopAccount = new ShopAccount();
		shopAccount.setShopacc_acc(username);
		shopAccount.setShopacc_pwd(password);
		
		
		//调用service注册方法
		shopAccountServiceImpl.register(shopAccount);
		
		//跳转路径
		uri="/shopSys/login.jsp";
		return uri;
	}
	/**
	 * 获取商家账号信息
	 */
	public Object getShopAccMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		HttpSession session = request.getSession();
		
		//获取当前登录商家的id
		int id = (int) session.getAttribute("SHOP_ACCOUNT_ID");
		
		//调用service方法查找用户信息
		ShopAccount shopAccount  = shopAccountServiceImpl.getShopAccMsg(id);
		
		//判断
		if(shopAccount !=null){
			//把对象保存在request域中
			request.setAttribute("SHOPACCOUNT_MSG", shopAccount);
		}
		//跳转路径，这里必须用转发，重定向会把request域数据清空的
		uri = request.getRequestDispatcher("/shopSys/shopcenter/shopAccount.jsp");
		return uri;
	}
	/**
	 * 更新商家账号信息(暂时没用到）
	 */
	public Object updateShopAcc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取登录参数
		String id =request.getParameter("shopacc_id");
		String username =  URLDecoder.decode(request.getParameter("shopacc_acc"), "UTF-8");
		String password =  URLDecoder.decode(request.getParameter("shopacc_pwd"), "UTF-8");
		
		//封装成对象
		ShopAccount shopAccount = new ShopAccount();
		shopAccount.setShopacc_id(Integer.parseInt(id));
		shopAccount.setShopacc_acc(username);
		shopAccount.setShopacc_pwd(password);
		
		//调用service方法
		shopAccountServiceImpl.updateShopAcc(shopAccount);
		//跳转路径
		uri="/shopSys/exit.jsp";
		return uri;
	}


}
