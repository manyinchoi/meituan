package cn.itcast.shop.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.service.ShopAccountService;


public class ShopFilter implements Filter {
	//产生service对象
	private ShopAccountService shopAccountServiceImpl = BeanFactory.getInstance("shopAccountServiceImpl", ShopAccountService.class);


   
	public void destroy() {
		System.out.println("shopFilter 消亡");
	}

	
	public void doFilter(ServletRequest req, 
			ServletResponse res, 
			FilterChain chain) throws IOException, ServletException {
		//转换类型
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse)res;
		
		//获取请求路径
		String uri=request.getRequestURI();
		//获取session域中的账号信息
		String shopname = (String) request.getSession().getAttribute("SHOP_ACCOUNT");
		
		if(uri.contains("shopcenter/frame")){//拦截非登录的商家账号访问商家中心页面
			
			//判断shopname是否为空
			if(shopname !=null){
				//不空，说明已经登录了，放行
				chain.doFilter(request, response);
			}else{
				//没有登录，跳转到登录页面
				response.sendRedirect(request.getContextPath()+"/shopSys/login.jsp");
			}
		}else if(uri.contains("shop/frame")){//拦截没有成功开店的商家账号访问店铺页面
			
			//判断shopname是否为空
			if(shopname !=null){
				//不空，说明已经登录了,再判断是否有成功开店了
				//调用service方法查找登录账号信息
				int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
				ShopAccount shopAccount  = shopAccountServiceImpl.getShopAccMsg(id);
				
				//判断开店状态
				if(shopAccount.getShopacc_state() !=0){
					//不等于0，说明已经成功开店了，放行
					chain.doFilter(request, response);
				}else{
					//不然就给出提示，并跳转到商家中心页面
					response.sendRedirect(request.getContextPath()+"/shopSys/shopcenter/frame.jsp");
				}
			}else{
				//没有登录，跳转到登录页面
				response.sendRedirect(request.getContextPath()+"/shopSys/login.jsp");
			}
		}else{
			//暂时放行，还有其它的没做，待改
			chain.doFilter(request, response);
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("shopFilter 初始化");
	}

}
