<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>

		
<% 
	session.removeAttribute("USER");//删除session域中登录用户
	session.removeAttribute("USERID");
	//然后跳转到列表展示页面
	String uri=request.getContextPath();
	response.sendRedirect(uri+"/UserServlet");
%>
