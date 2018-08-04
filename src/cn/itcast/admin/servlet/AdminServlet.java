package cn.itcast.admin.servlet;

import cn.itcast.admin.entity.Admin;
import cn.itcast.admin.service.AdminService;
import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.servlet.BaseServlet;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.entity.Shop;
import cn.itcast.user.entity.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminServiceImpl = BeanFactory.getInstance("adminServiceImpl", AdminService.class);
	
	/**
	 * 登录验证方法
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取前端输入的数据
		String name=request.getParameter("adminName");
		String password=request.getParameter("password");

		//打包数据
		Admin admin=new Admin(name,password);

		System.out.println(admin.getAdmin_account()+admin.getAdmin_pwd());
		//通过AdminService判断登录信息
		if(adminServiceImpl.checkLogin(admin)) {
			//登录成功则添加session并进入管理员后台
			HttpSession session = request.getSession();  
			// 保存用户信息  
			session.setAttribute("name", name);
			uri = "/adminSys/frame.jsp";								
		}else {
			//登录失败则重新进行登录
			uri = "/adminSys/index.jsp";
		}
		
		//执行跳转
		return uri;
	}
	
	/**
	 * 查看商家账户信息方法
	 */
	public Object getShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取当前页参数
		String currPage = request.getParameter("currentPage");
		
		//判断
		if(currPage == null || "".equals(currPage.trim())) {
			currPage = "1";//第一次访问，设置当前页为1
		}
		//转化
		int currentPage = Integer.parseInt(currPage);
		
		PageBean<ShopAccount> pageBean = new PageBean<ShopAccount>();
		pageBean.setCurrentPage(currentPage);
		
		adminServiceImpl.getShop(pageBean);
		
		System.out.println("pageBean为"+pageBean.getPageData().size());
		
		request.setAttribute("pageBean", pageBean);
		
        //跳转
		uri = request.getRequestDispatcher("/adminSys/public/list/list.jsp");
		
		return uri;
	}
	
	/**
	 * 查看用户账户信息方法
	 */
	public Object getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取当前页参数
		String currPage = request.getParameter("currentPage");
		
		//判断
		if(currPage == null || "".equals(currPage.trim())) {
			currPage = "1";//第一次访问，设置当前页为1
		}
		//转化
		int currentPage = Integer.parseInt(currPage);
		
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setCurrentPage(currentPage);
		
		adminServiceImpl.getUser(pageBean);
		
		request.setAttribute("pageBean", pageBean);
		
        //跳转
		uri = request.getRequestDispatcher("/adminSys/public/list/list2.jsp");
		
		return uri;
	}
	
	/**
	 * 查看商家店铺信息方法
	 */
	public Object getShopinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取当前页参数
		String currPage = request.getParameter("currentPage");
		
		//判断
		if(currPage == null || "".equals(currPage.trim())) {
			currPage = "1";//第一次访问，设置当前页为1
		}
		//转化
		int currentPage = Integer.parseInt(currPage);
		
		PageBean<Shop> pageBean = new PageBean<Shop>();
		pageBean.setCurrentPage(currentPage);
		
		adminServiceImpl.getShopInfo(pageBean);
		
		request.setAttribute("pageBean", pageBean);
		
        //跳转
		uri = request.getRequestDispatcher("/adminSys/public/list/list3.jsp");
		
		return uri;
	}
	
	/**
	 * 管理商家账号中的删除
	 */
	public Object deleteShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取前端输入的数据
		String shopaccid=request.getParameter("shopaccid");
		int id = Integer.parseInt(shopaccid);
		
		//通过AdminService删除数据
		adminServiceImpl.deleteShop(id);
		
		//删除成功
		uri="/AdminServlet?method=getShop";
		return uri;

	}
	
	/**
	 * 管理用户账号中的删除
	 */
	public Object deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取前端输入的数据
		String userid=request.getParameter("userid");
		int id = Integer.parseInt(userid);
		
		//通过AdminService删除数据
		adminServiceImpl.deleteUser(id);
		
		//删除成功
		uri="/AdminServlet?method=getUser";
		return uri;

	}
	
	/**
	 * 管理商家店铺中的删除
	 */
	public Object deleteShop2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取前端输入的数据
		String shopid=request.getParameter("shopid");
		int id = Integer.parseInt(shopid);
		
		//通过AdminService删除数据
		adminServiceImpl.deleteShop2(id);
		
		//删除成功
		uri="/AdminServlet?method=getShopinfo";
		return uri;
	}
	
	/**
	 * 查看店铺申请信息方法
	 */
	public Object getShopApply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取当前页参数
		String currPage = request.getParameter("currentPage");
		
		//判断
		if(currPage == null || "".equals(currPage.trim())) {
			currPage = "1";//第一次访问，设置当前页为1
		}
		//转化
		int currentPage = Integer.parseInt(currPage);
		
		PageBean<Shop> pageBean = new PageBean<Shop>();
		pageBean.setCurrentPage(currentPage);
		
		adminServiceImpl.getShopApply(pageBean);
		
		request.setAttribute("pageBean", pageBean);
		
        //跳转
		uri = request.getRequestDispatcher("/adminSys/public/list/list4.jsp");
		
		return uri;
	}
	
	/**
	 * 通过店铺申请
	 */
	public Object pass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取前端输入的数据
		String shopaccid=request.getParameter("shopaccid");
		int id = Integer.parseInt(shopaccid);
		
		//通过AdminService改变数据
		adminServiceImpl.pass(id);
		
		//改变成功
		uri="/AdminServlet?method=getShopApply";
		return uri;
	}
	
	/**
	 * 拒绝店铺申请
	 */
	public Object notpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取前端输入的数据
		String shopaccid=request.getParameter("shopaccid");
		int id = Integer.parseInt(shopaccid);
		
		//通过AdminService删除数据
		adminServiceImpl.notpass(id);
		
		//删除成功
		uri="/AdminServlet?method=getShopApply";
		return uri;
	}
}