package cn.itcast.core.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.core.util.WebUtil;

/**
 * 定义一个基本servlet类，所有的servlet类都继承它，用于处理servlet如何调用方法，和返回值
 * 在子类servlet中，只需写相应的方法即可，每个方法都有一个跳转路径的返回值
 * @author pc
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// (保存跳转的资源)  方法返回值
		Object returnValue=null;
		
		//获取操作类型,【约定 > 俗成： 操作类型的值，必须对应servlet中的方法名称】
		String methodName=request.getParameter("method");
		if(methodName==null){
			methodName="search";
		}
		//测试
		System.out.println("baseservlet-method:"+methodName);
		try {
			// 1. 获取当前运行类的字节码
			Class clazz=this.getClass();
			// 2. 获取当前执行的方法的Method类型
			Method method=clazz.getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			// 3. 执行方法
			returnValue=method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			returnValue="/error/error.jsp";
		} 
		//跳转
		WebUtil.goTo(request, response, returnValue);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
