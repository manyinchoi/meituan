package cn.itcast.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	public void destroy() {
		System.out.println("encodingFilter 消亡");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		
		HttpServletRequest req = (HttpServletRequest) request;

		if(req.getMethod().equals("GET")) {
			//如果是get请求方式，则进行编码转换
			EncodingRequest er = new EncodingRequest(req);
			//放行
			chain.doFilter(er, response);
		} else if(req.getMethod().equals("POST")) {
			//post请求方式，直接放行
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("encodingFilter 初始化");
	}

}
