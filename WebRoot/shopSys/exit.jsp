<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>

		
<% 
	session.removeAttribute("SHOP_ACCOUNT");//删除session域中登录用户
	session.removeAttribute("SHOP_ACCOUNT_ID");
	//然后跳转到列表展示页面
	response.sendRedirect("welcome.jsp");
%>
