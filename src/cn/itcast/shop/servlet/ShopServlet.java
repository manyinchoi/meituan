package cn.itcast.shop.servlet;

import cn.itcast.core.factory.BeanFactory;
import cn.itcast.core.servlet.BaseServlet;
import cn.itcast.core.util.Condition;
import cn.itcast.core.util.PageBean;
import cn.itcast.shop.entity.Food;
import cn.itcast.shop.entity.Shop;
import cn.itcast.shop.entity.ShopAccount;
import cn.itcast.shop.entity.Style;
import cn.itcast.shop.service.ShopAccountService;
import cn.itcast.shop.service.ShopService;
import cn.itcast.user.entity.OrderDetail;
import cn.itcast.user.entity.Orders;
import cn.itcast.user.entity.User;
import cn.itcast.user.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/ShopServlet")
public class ShopServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//定义一个service
	private ShopService shopServiceImpl = BeanFactory.getInstance("shopServiceImpl", ShopService.class);
	private ShopAccountService shopAccountServiceImpl = BeanFactory.getInstance("shopAccountServiceImpl", ShopAccountService.class);
	private UserService userServiceImpl = BeanFactory.getInstance("userServiceImpl", UserService.class);
	
    /**
     * 商家申请开店
     */
	public Object apply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//定义跳转资源
		Object uri = null;
		
		HttpSession session = request.getSession();
		
		//获取当前登录商家的id
		int id = (int) session.getAttribute("SHOP_ACCOUNT_ID");
		
		//创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//文件上传核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置大小限制参数
		upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
		upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
		upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

		if(upload.isMultipartContent(request)) {

			//new一个店铺,用来装数据
			Shop shop =new Shop();
			shop.setShopacc_id(id);
		
			//把请求数据转换为一个个FileItem对象，用集合装起来
			List<FileItem> list = upload.parseRequest(request);
			//遍历得到每一个上传的数据
			for (FileItem item : list) {

				if (item.isFormField()) {// 如果是普通本文内容
					//获取表单元素名称
					String name = item.getFieldName();
					// 获取value值
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"),
							"UTF-8");
					//为店铺的属性赋值
					BeanUtils.setProperty(shop, name, value);
					
				} else {// 如果是上传文件内容
					
					//获取表单上传文件的元素名称
					String fieldName = item.getFieldName();
					
					//获取项目下面的upload文件路径
					//下句结果，path=D:\JDK\apache-tomcat-8.5.29\webapps\meituan\
					String path = request.getSession().getServletContext().getRealPath("/");
					//获取到tomcat服务器的web apps目录，在此目录下建一个upload文件夹存放上传的图片
					path=path.substring(0, path.indexOf("meituan"));
					
					//判断当前目录下有没有此路径文件（upload），如果没有就创建
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					
					// 获取上传的文件名
					String name = item.getName();

					BeanUtils.setProperty(shop, fieldName, "upload/" + name);
					
					// a2. 拼接文件名，取得全路径文件名
					File file = new File(path, "upload/"+name);
					// d. 上传
					if(!file.isDirectory()){
						item.write(file);
					}
					item.delete(); // 删除组件运行时产生的临时文件
				}
			}
			//测试
			System.out.println("shopservlet:"+shop);
			//调用service方法，把申请表shop添加到数据库中
			shopServiceImpl.addShopApply(shop);
			
		} 
		//定义并设置回显信息
		request.setAttribute("message", "1");
		//转发
		uri = request.getRequestDispatcher("/shopSys/shopcenter/openShop.jsp");
		return uri;
	}
	/**
	 * 获取所有菜系
	 */
	public Object getShopType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri = null;
		//获取当前登录商家的id
		int id = (int)request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		
		//调用service方法，查找所有的菜系
		List<Style> list = shopServiceImpl.getShopType();
		//查看shop表，检查是否已经申请过开店
		Shop shop= shopServiceImpl.getShopByAccountId(id);
		//判断
		if(shop != null){
			request.setAttribute("message", "2");
		}
		//把数据保存到request域中
		request.setAttribute("SHOPTYPE", list);
		
		//通过转发跳转
		uri = request.getRequestDispatcher("/shopSys/shopcenter/openShop.jsp");
		return uri;
	}
	/**
	 * 获取通知信息
	 */
	public Object getInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri = null;
		HttpSession session = request.getSession();
		
		//获取当前登录商家的id
		int id = (int) session.getAttribute("SHOP_ACCOUNT_ID");
		//查看shop表，检查是否已经申请过开店
		Shop shop= shopServiceImpl.getShopByAccountId(id);
		//判断
		if(shop != null){
			request.setAttribute("INFORM", "1");
			//去查找并回显商家账号信息
			ShopAccount shopAccount = shopAccountServiceImpl.getShopAccMsg(id);
			//把shopAccount保存到request域中
			request.setAttribute("SHOPACCOUNT", shopAccount);
		}
		
		//通过转发跳转
		uri = request.getRequestDispatcher("/shopSys/shopcenter/inform.jsp");
		return uri;
	}
	/**
	 * 获取店铺信息
	 */
	public Object getShopMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri=null;
		
		//从session域中获取当前登录的id
		int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		//调用service方法查找用户信息
		Shop shop  = shopServiceImpl.getShopMsg(id);
		
		//既然是显示商家店铺信息，那么还要把所有的商家所属菜系查找出来
		List<Style> list = shopServiceImpl.getShopType();
		
		//判断
		if(shop !=null){
			//把对象保存在request域中
			request.setAttribute("SHOP_MSG", shop);
			//把菜系保存到request域中
			request.setAttribute("shopStyle", list);
		}
		//跳转路径，这里必须用转发，重定向会把request域数据清空的
		uri = request.getRequestDispatcher("/shopSys/shop/shopMessage.jsp");
		return uri;
	}
	/**
	 * 更新店铺信息
	 */
	public Object updateShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//定义跳转资源
		Object uri=null;
		
		//从session域中获取当前登录的id
		int shopacc_id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		
		//创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//文件上传核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置大小限制参数
		upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
		upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
		upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

		//判断当前表单是否为文件上传表单
		if (upload.isMultipartContent(request)) {

			Shop shop = new Shop();
			
			//把请求数据转换为一个个FileItem对象，用集合装起来
			List<FileItem> list = upload.parseRequest(request);
			//遍历得到每一个上传的数据
			for (FileItem item : list) {
				if (item.isFormField()) {//若是普通本文内容
					//获取表单元素名称
					String name = item.getFieldName();
					//获取获取相应值
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"),
							"UTF-8");
					//为食物的属性赋值
					BeanUtils.setProperty(shop, name, value);
				} else {// 上传内容
					//获取表单元素名称
					String fieldName = item.getFieldName();
					//获取上传路径
					String path = request.getSession().getServletContext().getRealPath("/");
					//获取到tomcat服务器的web apps目录，在此目录下建一个upload文件夹存放上传的图片
					path=path.substring(0, path.indexOf("meituan"));

					//测试
					System.out.println("ceshiservlet:"+path);
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					//获取文件名
					String name = item.getName();
					if(name!=null && !"".equals(name.trim())){
						//为食物属性赋值
						BeanUtils.setProperty(shop, fieldName,"upload/" + name);

						// a2. 拼接文件名
						File file = new File(path, "upload/"+name);
						// d. 上传
						if (!file.isDirectory()) {
							item.write(file);//用google以外的浏览器，这里就会出错，怎么办？
						}
						item.delete(); // 删除组件运行时产生的临时文件
					}else{
						//通过id查找店铺图片
						String shop_pic =shopServiceImpl.getShopByAccountId(shopacc_id).getShop_pic();
						BeanUtils.setProperty(shop, "shop_pic",shop_pic);
					}
				}
			}
			//测试
			System.out.println("updateshop:"+shop);
			//调用service方法查找用户信息
			shopServiceImpl.updateShop(shop);
		} 
		//跳转路径
		uri="/shopSys/shop/public/right.jsp";
		return uri;
	}
	/**
	 * 获取所有菜品
	 */
	public Object getAllFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		PageBean<Food> pageBean = new PageBean<Food>();
		pageBean.setCurrentPage(currentPage);

		//生成一个查询条件
		Condition condition =new Condition();
		//获取当前登录商家账号，作为查询条件，只查属于当前账号申请的店铺的菜品
		int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		if(id !=0){
			//通过当前登陆账号，查找他的店铺的id
			Shop shop = shopServiceImpl.getShopByAccountId(id);
			if(shop !=null){
				condition.setShop_id(shop.getShop_id());
			}
			if(condition !=null){
				pageBean.setCondition(condition);
			}
		}
		
		// 3. 调用service
		shopServiceImpl.getAll(pageBean); 
		// 【pageBean已经被dao填充了数据】
		
		// 4. 保存pageBean对象，到request域中
		List<Food> list = pageBean.getPageData();
		
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("FOODLIST", list);
		uri = request.getRequestDispatcher("/shopSys/shop/foodList.jsp");
		return uri;

	}
	/**
	 * 添加菜品
	 */
	public Object addFood(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		//定义跳转资源
		Object uri=null;
		//从session域中获取当前登录的id
		int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		
		//创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//文件上传核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
		upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
		upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

		if (upload.isMultipartContent(request)) {

			Food food = new Food();
			//把请求数据转换为一个个FileItem对象，用集合装起来
			List<FileItem> list = upload.parseRequest(request);
			//遍历得到每一个上传的数据
			for (FileItem item : list) {

				if (item.isFormField()) {// 普通本文内容
					//获取表单元素名称
					String name = item.getFieldName();
					// 获取值
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"),
							"UTF-8");
					//为食物的属性赋值
					BeanUtils.setProperty(food, name, value);
				} else {// 上传内容
					//获取表单元素名称
					String fieldName = item.getFieldName();
					//获取项目下面的upload文件路径
					//下句结果，path=D:\JDK\apache-tomcat-8.5.29\webapps\meituan\
					String path = request.getSession().getServletContext().getRealPath("/");
					//获取到tomcat服务器的web apps目录，在此目录下建一个upload文件夹存放上传的图片
					path=path.substring(0, path.indexOf("meituan"));
					
					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					// 获取文件名
					String name = item.getName();
					//为食物属性赋值
					BeanUtils.setProperty(food, fieldName, "upload/" + name);

					// a2. 拼接文件名
					File file = new File(path, "upload/"+name);
					// d. 上传
					if(!file.isDirectory()){
						item.write(file);
					}
					item.delete(); // 删除组件运行时产生的临时文件
				}
			}
			//通过当前登陆账号查找店铺
			Shop shop = shopServiceImpl.getShopByAccountId(id);
			//给食物设置店铺id
			food.setShop_id(shop.getShop_id());
			shopServiceImpl.add(food);
		} 
		uri="/ShopServlet?method=getAllFood";
		return uri;
	}
	/**
	 * 更新菜品
	 */
	public Object update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, Exception {
		Object uri=null;
		//从session域中获取当前登录的id
		int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		
		//创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//文件上传核心工具类
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置大小限制参数
		upload.setFileSizeMax(10 * 1024 * 1024); // 单个文件大小限制
		upload.setSizeMax(50 * 1024 * 1024); // 总文件大小限制
		upload.setHeaderEncoding("UTF-8"); // 对中文文件编码处理

		//判断当前表单是否为文件上传表单
		if (upload.isMultipartContent(request)) {

			Food food = new Food();
			//通过当前登陆账号查找店铺
			Shop shop = shopServiceImpl.getShopByAccountId(id);
			//先为食物设置店铺id
			food.setShop_id(shop.getShop_id());
			
			//把请求数据转换为一个个FileItem对象，用集合装起来
			List<FileItem> list = upload.parseRequest(request);
			//遍历得到每一个上传的数据
			for (FileItem item : list) {
				if (item.isFormField()) {//若是普通本文内容
					//获取表单元素名称
					String name = item.getFieldName();
					//获取获取相应值
					String value = item.getString();
					value = new String(value.getBytes("ISO-8859-1"),
							"UTF-8");
					//为食物的属性赋值
					BeanUtils.setProperty(food, name, value);
				} else {// 上传内容
					//获取表单元素名称
					String fieldName = item.getFieldName();
					//获取项目下面的upload文件路径
					//下句结果，path=D:\JDK\apache-tomcat-8.5.29\webapps\meituan\
					String path = request.getSession().getServletContext().getRealPath("/");
					//获取到tomcat服务器的web apps目录，在此目录下建一个upload文件夹存放上传的图片
					path=path.substring(0, path.indexOf("meituan"));

					File f = new File(path);
					if (!f.exists()) {
						f.mkdir();
					}
					//获取文件名
					String name = item.getName();
					if(name!=null && !"".equals(name.trim())){
						//为食物属性赋值
						BeanUtils.setProperty(food, fieldName,"upload/" + name);

						// a2. 拼接文件名
						File file = new File(path, "upload/"+name);
						// d. 上传
						if (!file.isDirectory()) {
							item.write(file);
						}
						item.delete(); // 删除组件运行时产生的临时文件
					}else{
						//通过id查找食物图片
						String food_pic =shopServiceImpl.findById(food.getFood_id()).getFood_pic();
						BeanUtils.setProperty(food, "food_pic",food_pic);
					}
				}
			}
			shopServiceImpl.updata(food);
		} 
		uri="/ShopServlet?method=getAllFood";
		return uri;
	}
	/**
	 * 根据id查找食物，并回显食物信息在页面
	 */
	public Object show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri=null;
		
		//获取当前食物的所有信息
		String id = request.getParameter("id");
		Food food = shopServiceImpl.findById(Integer.parseInt(id));

		request.setAttribute("FOOD", food);
		//必须通过转发，才能把数据带到jsp页面，重定向会丢失数据
		uri = request.getRequestDispatcher("/shopSys/shop/updateFood.jsp");
		return uri;
	}
	/**
	 * 根据id删除食物
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object uri=null;
		String id = request.getParameter("id");
		shopServiceImpl.delete(Integer.parseInt(id));
		//跳转
		uri="/ShopServlet?method=getAllFood";
		return uri;
		
	}
	/**
	 * 根据关键字查找食物(未能实现我要的，待改)
	 */
	public Object search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		PageBean<Food> pageBean = new PageBean<Food>();
		pageBean.setCurrentPage(currentPage);

		//生成一个查询条件
		Condition condition =new Condition();
		//获取当前登录商家账号，作为查询条件，只查属于当前账号申请的店铺的菜品
		int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		if(id !=0){
			//通过当前登陆账号，查找店铺的id
			Shop shop = shopServiceImpl.getShopByAccountId(id);
			String keyword = request.getParameter("keyword");
			if (keyword != null) {
				condition.setFood_name(keyword);
			}
			if(shop !=null){
				condition.setShop_id(shop.getShop_id());
			}
			if(condition !=null){
				pageBean.setCondition(condition);
			}
		}
		
		// 3. 调用service
		shopServiceImpl.getAll(pageBean); 
		// 【pageBean已经被dao填充了数据】
		
		// 4. 保存pageBean对象，到request域中
		List<Food> list = pageBean.getPageData();
		
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("FOODLIST", list);
		uri = request.getRequestDispatcher("/shopSys/shop/foodList.jsp");
		return uri;
		
	}
	/**
	 * 分页获取订单
	 */
	public Object getOrderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//定义跳转资源
		Object uri = null;
				
		// 1. 获取“当前页”参数； (第一次访问当前页为null)
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
		//获取当前登录商家账号，作为查询条件，只查属于当前账号申请的店铺的订单
		int id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		if(id !=0){
			//通过当前登陆账号，查找店铺的id
			Shop shop = shopServiceImpl.getShopByAccountId(id);
			if(shop !=null){
				condition.setShop_id(shop.getShop_id());
			}
			if(condition !=null){
				pageBean.setCondition(condition);
			}
		}
		
		// 3. 调用service
		shopServiceImpl.getAllOrders(pageBean); // 【pageBean已经被dao填充了数据】，但是当没有订单数据时，此处会报错invocationtargetexception(调用目标异常)，点解？？
		// 4. 保存pageBean对象，到request域中
		request.setAttribute("pageBean", pageBean);

		// 5. 跳转
		uri = request.getRequestDispatcher("/shopSys/shop/orderList.jsp");

		return uri;

	}
	/**
	 * 获取订单详情
	 */
	public Object getOrderDetail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		String id = request.getParameter("orderId");
		
		//获取当前登录商家账号，作为查询条件，只查属于当前账号申请的店铺的订单
		int shopacc_id = (int) request.getSession().getAttribute("SHOP_ACCOUNT_ID");
		//通过当前账号查找店铺,以获得店铺id
		Shop shop = shopServiceImpl.getShopByAccountId(shopacc_id);
		//根据订单id，获取订单信息，以获取用户信息
		Orders order = shopServiceImpl.getOrderByOrderId(id);
		//根据用户id获取用户信息
		User user = userServiceImpl.getUserMsg(order.getUser_id());
		List<OrderDetail> list = null;
		if (id != null && !id.isEmpty()) {
			list = shopServiceImpl.findByOrderId(Integer.parseInt(id));
		}
		List<Food> food=shopServiceImpl.query(shop.getShop_id());//查询当前账号的店铺的所有菜品
		request.setAttribute("USER_MSG", user);
		request.setAttribute("foods", food);//保存数据，以便在jsp中获取数据
		request.setAttribute("orderDetail", list);

		uri = request.getRequestDispatcher("/shopSys/shop/orderDetail.jsp");
		return uri;
	}
	/**
	 * 确认已配送
	 */
	public Object confirmDeli(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uri = null;
		//获取店铺id
		String orderid = request.getParameter("orderId");
		//根据订单id，改变订单的收货状态
		shopServiceImpl.confirmDeli(Integer.parseInt(orderid));

		uri = "/ShopServlet?method=getOrderList";
		return uri;
	}

}
