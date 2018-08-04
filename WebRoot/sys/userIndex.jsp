<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <title>欢迎来到美团外卖</title>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${pageContext.request.contextPath }/sys/style/css/userindex1.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			body{
				background-image:url('${pageContext.request.contextPath }/sys/style/images/back.jpg');
				background-size:100%100%;
			}
		</style>
		<script type="text/javascript">
			function exitEnsure(){
				if(confirm("确认要退出登录吗")){
					window.parent.location.href="${pageContext.request.contextPath }/sys/public/log2register/exit.jsp";
				}
			}
			function log(){
				window.parent.location.href="${pageContext.request.contextPath }/sys/public/log2register/login.jsp";
			}
			function register(){
				window.parent.location.href="${pageContext.request.contextPath }/sys/public/log2register/register.jsp";
			}
			function getMessage(){
				window.parent.location.href="${pageContext.request.contextPath }/UserServlet?method=getUserMsg";
			}
		</script>
	</head>

	<body >
		<!-- 头部 -->
		<div id="top">
			<!-- 标题 -->
			<div id="logo">
				<img style="border-radius:8px;" src="${pageContext.request.contextPath }/sys/style/images/logo.png" />
	        </div>
	        
	        <!-- 首页二字 -->
	        <div class="divID">
	        	<ul>
	        		<li><a href="${pageContext.request.contextPath }/UserServlet" style="text-decoration:none;">
	        			<font size="5" color="black">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</font>
	        			</a>
	        		</li>
	        		 <!-- 历史订单选项,用户没有登录时为灰色，无效，只有登录状态才能点击有效 -->
	        		<li>
	        			<c:choose>
	        			<c:when test="${not empty sessionScope.USER }">
			        		<a target="_blank" href="${pageContext.request.contextPath }/UserServlet?method=getMyOrder" style="text-decoration:none;">
			        			<font size="5" color="black">我的订单</font>
			        		</a>
	        			</c:when>
	        			<c:otherwise>
	        				<a href ="javascript:return false;" style="text-decoration:none;">
	        					<font size="5" color="#808080">我的订单</font>
	        				</a>
	        			</c:otherwise>
	        			</c:choose>
	        		</li>
	        	</ul>
	        </div>
	 
	        <!-- 搜索框 -->
	        <div class="divID">
	        	<br>
	        	<div class="bar1">
	        	<form action="${pageContext.request.contextPath }/UserServlet" method="post">
					<input type="hidden" name="method" value="search">
					<input id="search" type="text" name="keyword" placeholder="请输入店铺名称">
					&nbsp;&nbsp;
					<input id="search2" type="submit" value="搜索">
				</form>
				</div>
	        </div>
	        
	        <!-- 头部的右边部分 -->
	        <div id="rightID">
	        	<!-- 显示用户名、退出等信息 -->
	        	<div class="innerID">
	        	<% 
				Object uname=null;
				if((uname=session.getAttribute("USER"))!=null){
					uname="<a  href='javascript:void(0)' onclick='getMessage()' style='text-decoration:none;'>欢迎，"+uname+"</a>|"
						+"<a href='javaScript:void(0)' onclick='exitEnsure()' style='text-decoration:none;'>退出</a>";
				}else{
					uname="<a href='javascript:void(0)' onclick='log()' style='text-decoration:none;'>登&nbsp;录</a>&nbsp;|"
	        			+"<a href='javascript:void(0)' onclick='register()' style='text-decoration:none;'>&nbsp;注&nbsp;册</a>";
				}
				%>
				<%=uname %><p>
	        	</div>
	        	<div class="apply">
	        		<a target="_blank" href="${pageContext.request.contextPath }/shopSys/welcome.jsp">申 请 开 店</a>
	        	</div>
	        </div>
	      </div>
	             
	        <hr id="hr1">
	        
	     <!-- 撤销float：left的效果 -->
	     <div id="clear"></div>
	     <br>
	        <!-- 中间部分 -->
	        <div id= "center">
	        
	        	<!-- 中间的左边部分 -->
	        	<div id="left" >
	        		 <ul>
					    <li >
				            <div>
								<img style="max-width:20px;height:18px;margin-right:-5px;margin-left:6px" 
									src="${pageContext.request.contextPath }/sys/style/images/shop.jpg" /> 
								商家分类
							</div>
				            <ul>
								<c:forEach items="${requestScope.TYPES }" var="type">
					                <li>
					                	<a href="${pageContext.request.contextPath }/UserServlet?method=search&style_id=${type.style_id}">${type.style_name }</a>
									</li>
								</c:forEach>
				            </ul>
				        </li>
				    </ul>
	        	</div>
	        	
	        	<!-- 中间的右边部分 -->
	        	<div id="right" >
	        		<!-- 每一个店铺都是一个div块 -->
	        		<div id="shopID">
		        		<ul>
							<!-- 循环列出商家店铺 -->
							<c:forEach items="${requestScope.pageBean.pageData}" var="shop">
								<li >
									<a target="_blank" href="${pageContext.request.contextPath }/UserServlet?method=getShopDetail&shop_id=${shop.shop_id}">
										<img width="100%" height="100%" src="${pageContext.request.contextPath }/${shop.shop_pic}" />
									</a>
									<a href="${pageContext.request.contextPath }/UserServlet?method=getShopDetail&shop_id=${shop.shop_id}">${shop.shop_name}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					
					<!-- 撤销float：left的效果 -->
	     			<div id="clear"></div>
	     			
	     			
					<!-- 其他功能超链接 -->
					<div align="center" id="pageID">
					<br>
					<hr>
						当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/UserServlet?method=search&currentPage=1">首页</a>&nbsp;
						<a href="${pageContext.request.contextPath }/UserServlet?method=search&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>&nbsp;
						<a href="${pageContext.request.contextPath }/UserServlet?method=search&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>&nbsp;
						<a href="${pageContext.request.contextPath }/UserServlet?method=search&currentPage=${requestScope.pageBean.totalPage}">末页</a>
				    </div> 
	        	</div>
	        </div>
	        
	        <!-- 撤销float：left的效果 -->
	        <div id="clear" ></div>
	        
	        <!-- 底部 -->
	        <div id="bottom">
	        	<img style="width:75%;opacity: 0.7;" alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/bottom.png">
	        </div>
		
	</body>
</html>