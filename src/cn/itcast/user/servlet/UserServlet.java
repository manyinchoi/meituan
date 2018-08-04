package cn.itcast.user.servlet;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.servlet.BaseServlet;
import cn.itcast.core.util.Condition;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.shop.entity.Style;
import cn.itcast.shop.service.ShopService;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;
import cn.itcast.user.entity.Review;
import cn.itcast.user.entity.User;
import cn.itcast.user.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//产生service对象
	private UserService userServiceImpl = BeanFactory.getInstance("userServiceImpl", UserService.class);
	private ShopService shopServiceImpl = BeanFactory.getInstance("shopServiceImpl", ShopService.class);

  
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
		User user = new User();
		
		//调用service层方法，判断
		if((user=userServiceImpl.checkLogin(username,password)) !=null){
			//为真，登录成功，保存用户的id和用户名到session域中
			request.getSession().setAttribute("USER", username);
			request.getSession().setAttribute("USERID",user.getUser_id());
			//设置跳转路径
			uri="/UserServlet";
		}else{
			//失败
			uri="/sys/public/log2register/login.jsp";
		}
		return uri;
	}
	
	/**
	 * 注册
	 */
	public Object register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取登录参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone =request.getParameter("phone");
		//封装成user对象
		User user = new User();
		user.setUser_name(username);;
		user.setUser_pwd(password);;
		user.setUser_pho(phone);
		
		//调用service注册方法
		userServiceImpl.register(user);
		
		//跳转路径
		uri="/sys/public/log2register/login.jsp";
		return uri;
	}

	/**
	 * 获取用户信息
	 */
	public Object getUserMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取登录id
		//这里会报错，真的是奇怪，java.lang.ClassCastException: java.io.ObjectStreamClass cannot be cast to java.lang.String
		//String id =  (String) request.getSession().getAttribute("USERID");
		//获取当前登录用户的id
		int user_id = (int) request.getSession().getAttribute("USERID");
		
		//调用service方法查找用户信息
		User user = userServiceImpl.getUserMsg(user_id);
		
		//判断
		if(user !=null){
			//把对象保存在request域中
			request.setAttribute("USER_MSG", user);
		}
		//跳转路径，这里必须用转发，重定向会把request域数据清空的
		uri = request.getRequestDispatcher("/sys/public/user/user.jsp");
		return uri;
	}
	/**
	 * 更新用户信息
	 */
	public Object updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取登录参数
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String sex = request.getParameter("sex");
		
		//封装成对象
		User user = new User();
		user.setUser_id(Integer.parseInt(id));
		user.setUser_name(username);;
		user.setUser_pwd(password);
		user.setUser_pho(phone);
		user.setUser_add(address);
		user.setUser_sex(sex);
		
		//调用service方法查找用户信息
		userServiceImpl.updateUser(user);
		
		//跳转路径
		uri="/UserServlet";
		return uri;
	}
	/**
	 * 在首页分页列出所有的店铺
	 */
	public Object search(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 保存跳转资源(转发/重定向)
		Object uri=null;
		
		// 1. 获取“当前页”参数； (第一次访问,当前页为null)
		String currPage = request.getParameter("currentPage");
		// 判断
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "1"; // 第一次访问，设置当前页为1;
		}
		// 转换
		int currentPage = Integer.parseInt(currPage);

		// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
		PageBean<Shop> pageBean = new PageBean<Shop>();
		pageBean.setCurrentPage(currentPage);

		//生成一个查询条件
		Condition condition =new Condition();
		
		//获取搜索的关键字，和店铺类型id
		String keyword = request.getParameter("keyword");
		String style_id = request.getParameter("style_id");
		//测试
		System.out.println("keyword:"+keyword);
		if(keyword !=null){
			condition.setShop_name(keyword);
		}
		if(style_id !=null){
			condition.setStyle_id(Integer.parseInt(style_id));
		}
		if(condition !=null){
			pageBean.setCondition(condition);
		}
		
		// 3. 调用service
		userServiceImpl.getAll(pageBean); 
		// 【pageBean已经被dao填充了数据】
		
		//测试
		for(Shop shop:pageBean.getPageData()){
			System.out.println("店铺："+shop.getShop_id()+";"+shop.getShop_name());
		}
		
		//还要把店铺类型查找出来，并在左边显示出来
		List<Style> list = shopServiceImpl.getShopType();
		//把数据保存到request域中
		request.setAttribute("TYPES", list);
		request.setAttribute("pageBean", pageBean);
		//转发
		uri = request.getRequestDispatcher("/sys/userIndex.jsp");
		return uri;
		
	}
	/**
	 * 获取店铺信息，这个方法需要把店铺信息、店铺的所有菜品、和用户反馈的评论查找出来
	 */
	public Object getShopDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		HttpSession session = request.getSession();
		// 获取session里的值
		Object obj = session.getAttribute("SHOP_ID");
		
		//获取参数
		String shop_id = request.getParameter("shop_id");
		if (shop_id != null) {
			//点击进入不同的店铺，设置店铺id，会覆盖这个shop_id值吗？
			session.setAttribute("SHOP_ID", Integer.parseInt(shop_id));// 存店铺id以备订单用
			System.out.println("测试点击不同店铺后，会不会覆盖值："+obj+";新值："+session.getAttribute("SHOP_ID"));
		}
		
		
		//调用shopserviceimpl方法查找店铺信息
		Shop shop = userServiceImpl.getShopMsg(Integer.parseInt(shop_id));
		//通过店铺id查找菜品,不分页
		List<Food> foods=userServiceImpl.getFood(Integer.parseInt(shop_id));
		//通过店铺id查找评论
		List<Review> reviews = userServiceImpl.getReview(Integer.parseInt(shop_id));
		//查找所有用户
		List<User> users= userServiceImpl.getUser();
		
		//判断
		if(shop !=null){
			//把店铺信息保存到session域中，因为有很多个页面用到了这个店铺信息
			session.setAttribute("SHOP_MSG", shop);
		}
		if(foods !=null){
			request.setAttribute("FOODS", foods);
		}
		if(reviews !=null){
			request.setAttribute("REVIEWS", reviews);
		}
		if(users !=null){
			request.setAttribute("USERS",users );
		}
		//跳转路径，这里必须用转发，重定向会把request域数据清空的
		uri = request.getRequestDispatcher("/sys/public/shop/listMenu.jsp");
		return uri;
	}
	/**
	 * 获取菜品详情
	 */
	public Object getFoodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//获取参数
		String food_id = request.getParameter("food_id");
		
		//通过食物id查找食物信息
		Food food = shopServiceImpl.findById(Integer.parseInt(food_id));
		
		
		//判断
		if(food !=null){
			request.setAttribute("FOOD_MSG", food);
		}
	
		//跳转路径，这里必须用转发，重定向会把request域数据清空的
		uri = request.getRequestDispatcher("/sys/public/shop/foodDetail.jsp");
		return uri;
	}
	//放入购物车
	public Object putInCar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object uri = null;
		Map<Food, Integer> map = new LinkedHashMap<Food, Integer>();//新生成一个购物单

		// 获取食物id
		String id = request.getParameter("food_id");
		//根据id查找食物
		Food food = shopServiceImpl.findById(Integer.parseInt(id));
		
		//用于存储订单数据
		Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");

		if(m!=null){//非空，说明已经有一个购物单了，那就直接把菜品添加到此购物单中
			int i=1;
			Set<Map.Entry<Food, Integer>> entrySet = m.entrySet();
			for (Entry<Food, Integer> entry : entrySet) {
				Food fd = entry.getKey();
				//遍历购物单，如果是已经存在的菜品，那就让菜品的数量加一
				if(fd.equals(food)){//这个判断条件无效，为什么？,因为在food实体类里面没有重写hash code和equal方法
					Integer  count=entry.getValue();
					count++;
					m.put(fd,count);
					i=0;
				}
			}
			if(i==1){//如果是购物单没有的菜品，那就添加一个菜品，数量默认为一
				m.put(food, 1);
			}
			}else{//为空，说明是一个新的购物单，还没有点菜，那就在新的购物单添加一个菜品，数量默认为一
				map.put(food, 1);
		}
		
		//保存数据到session域中
		if (m != null) {
			session.setAttribute("foods", m);
		} else {
			session.setAttribute("foods", map);
		}
		//加入餐车成功后，跳到当前商家店铺的页面
		uri = "/UserServlet?method=getShopDetail&shop_id="+food.getShop_id();

		return uri;
	}
	//删除订单
	public Object removeOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		String id = request.getParameter("gid");
		Food food = shopServiceImpl.findById(Integer.parseInt(id));
		HttpSession session = request.getSession();

		//获取加入餐车时的食物数据
		Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");
		if(m!=null){
			Set<Map.Entry<Food, Integer>> entrySet = m.entrySet();
			for (Entry<Food, Integer> entry : entrySet) {
				Food fd = entry.getKey();
				if(fd.equals(food)){
					m.remove(fd);
				}
			}
		}
		session.setAttribute("foods", m);
		uri = "/sys/public/cart/cartMenu.jsp";
		return uri;
	}

	public Object alterOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		String id = request.getParameter("gid");
		Food food = shopServiceImpl.findById(Integer.parseInt(id));

		// 获取修改过的数量
		String num = request.getParameter("snumber");
		HttpSession session = request.getSession();
		Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");
		
		if(m!=null){
			Set<Map.Entry<Food, Integer>> entrySet = m.entrySet();
			for (Entry<Food, Integer> entry : entrySet) {
				Food fd = entry.getKey();
				if(fd.equals(food)){
					m.put(fd, Integer.parseInt(num));
				}
			}
		}
		session.setAttribute("foods", m);
		uri = "/sys/public/cart/cartMenu.jsp";
		return uri;
	}

	//下单的方法
	public Object takeOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Map<Food, Integer> m = (Map<Food, Integer>) session.getAttribute("foods");

		// 新建订单对象
		Orders order = new Orders();
		//获取当前登录用户id，和店铺的id
		int shop_id =  (int) session.getAttribute("SHOP_ID");
		int user_id =  (int) session.getAttribute("USERID");
		//设置订单的店铺和用户id
		order.setUser_id(user_id);
		order.setShop_id(shop_id);
		
		Set<Entry<Food, Integer>> entrySet = m.entrySet();
		// 创建订单详细对象
		OrderDetail detail = new OrderDetail();

		// 定义总价钱
		int sum = 0;
		int orderId = userServiceImpl.getCount()+1;
		order.setOrder_id(orderId);

		for (Entry<Food, Integer> entry : entrySet) {
			Food food = entry.getKey();
			Integer count = entry.getValue();
			//把属于相应店铺的菜品加入订单中
			if(food.getShop_id() == shop_id){
				sum += food.getFood_price() * count;
				order.setOrder_time(new Date());
			}
		}

		order.setOrder_price(sum);
		userServiceImpl.add(order);

		for (Entry<Food, Integer> entry : entrySet) {
			Food food = entry.getKey();
			Integer count = entry.getValue();
			if(food.getShop_id() == shop_id){
				detail.setFood_id(food.getFood_id());
				detail.setOrder_id(order.getOrder_id());
				detail.setFood_count(count);
				userServiceImpl.add(detail);
			}
		}

		//下单付款成功后，需要把session域中存储食物的map集合（即foods）remove掉
		//因为如果用户下了一单之后，在session域有效期内，他又去点餐，此时用户的上一单的菜品还在的
		//所以要remove掉，这样才能重新new一个订单
		session.removeAttribute("foods");
		//跳转到用户首页，此时用户可以在我的订单查看刚刚下的订单
		uri = "/UserServlet";
		return uri;
	}

	/**
	 * 分页列出我的的订单
	 */
	public Object getMyOrder(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 保存跳转资源(转发/重定向)
		Object uri=null;
		
		// 1. 获取“当前页”参数； (第一次访问,当前页为null)
		String currPage = request.getParameter("currentPage");
		// 判断
		if (currPage == null || "".equals(currPage.trim())) {
			currPage = "1"; // 第一次访问，设置当前页为1;
		}
		// 转换
		int currentPage = Integer.parseInt(currPage);

		// 2. 创建PageBean对象，设置当前页参数； 传入service方法参数
		PageBean<Orders> pageBean = new PageBean<Orders>();
		pageBean.setCurrentPage(currentPage);

		//生成一个查询条件
		Condition condition =new Condition();
		//获取当前登录用户的id
		int user_id = (int) request.getSession().getAttribute("USERID");
		
		if(user_id !=0){
			condition.setUser_id(user_id);
		}
		if(condition !=null){
			pageBean.setCondition(condition);
		}
		userServiceImpl.getOrder(pageBean); 
		// 【pageBean已经被dao填充了数据】
		
		//用set集合来存储shop，不会重复
		Set<Shop> shopSet = new HashSet<Shop>();
		//遍历order获取每一个订单对应的店铺，以便获得店铺名称
		if(pageBean.getPageData() !=null){
			for(Orders o:pageBean.getPageData()){
				//查找出来的 店铺，添加到set集合里
				shopSet.add(userServiceImpl.getShopMsg(o.getShop_id()));
			}
		}
		//测试
		for(Shop sp:shopSet){
			System.out.println("name:"+sp.getShop_name());
		}
		
		//把数据保存到request域中
		request.setAttribute("SHOPS", shopSet);
		request.setAttribute("pageBean", pageBean);
		//转发
		uri = request.getRequestDispatcher("/sys/public/history/historyMenu.jsp");
		return uri;
		
	}
	/**
	 * 获取订单详情
	 */
	public Object getOrderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		//获取订单id
		String id = request.getParameter("orderId");
		//根据订单id查找订单详细
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		List<Food> food=new ArrayList<Food>();
		if (id != null && !id.isEmpty()) {
			list = shopServiceImpl.findByOrderId(Integer.parseInt(id));
		}
		if(list !=null){
			//遍历每一个订单，根据订单的食物id查找食物
			for(OrderDetail o:list){
				food.add(shopServiceImpl.findById(o.getFood_id()));
			}
		}
		request.setAttribute("foods", food);//保存数据，以便在jsp中获取数据
		request.setAttribute("orderDetail", list);

		uri = request.getRequestDispatcher("/sys/public/order/foodList.jsp");
		return uri;
	}
	/**
	 * 对店铺进行评论
	 */
	public Object writeCommend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		//获取店铺id
		String shopid = request.getParameter("shopId");
		//根据店铺id查找店铺详情
		Shop shop = userServiceImpl.getShopMsg(Integer.parseInt(shopid));
		request.getSession().setAttribute("SHOP_MSG", shop);

		uri = request.getRequestDispatcher("/sys/public/history/writeCommend.jsp");
		return uri;
	}
	/**
	 * 保存用户的评论
	 */
	public Object saveCommend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		//获取店铺id
		String shopid = request.getParameter("shopId");
		//获取用户id
		int userid = (int) request.getSession().getAttribute("USERID");
		//获取评论信息
		String commend = request.getParameter("commend");
		
		//实例化一个评论对象
		Review review = new Review();
		review.setUser_id(userid);
		review.setShop_id(Integer.parseInt(shopid));
		review.setRev_desc(commend);
		review.setRev_time(new Date());
		
		//调用service方法，保存评论
		userServiceImpl.saveCommend(review);
		uri = "/UserServlet";
		return uri;
	}
	/**
	 * 用户确认收货
	 */
	public Object admitGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		//获取店铺id
		String orderid = request.getParameter("orderId");
		//根据订单id，改变订单的收货状态
		userServiceImpl.admitGet(Integer.parseInt(orderid));

		uri = "/UserServlet";
		return uri;
	}

}
