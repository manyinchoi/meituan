package cn.itcast.core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 用于禁止未登录的非法用户访问管理员中心
 *
 */
@WebFilter("/adminSys/frame.jsp")
public class AdminFilter implements Filter {

	public void destroy() {
		System.out.println(this+"死了");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1	进行request类型的强转，以便获得更多的信息
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//2	
		HttpSession session = req.getSession();
		String name = (String)session.getAttribute("name");
		System.out.println(name);
		
		if(name != null) {
		//3	如果为合法用户，进入成功
			chain.doFilter(request, response);
		}else {
		//	如果为非法用户，进行干预
			request.getRequestDispatcher("/adminSys/filter.html").forward(req,resp);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println(this+"生了");
	}

}
