<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>我的订单</title>
	<style type="text/css">
		body{
			background-image:url('${pageContext.request.contextPath }/sys/style/images/back.jpg');
			background-size:100% 800px;
		}
		#menu{
			margin:auto;
			border:4px solid orange;
			border-radius:3px;
			text-align:center;
			width:82%;
		}
		#top{
			width:100%;
			height:50px;
			margin-bottom:25px
		}
		#hr1{
			width:100%;
			height:10px;
			border:none;
			border-top:5px groove skyblue;
		}
		#page{
			margin-bottom:5px;
		}
		#page a{
			text-decoration:none;
		}
		#page a:hover{
			color:red;
			border-bottom:2px solid chocolate;
		}
		h1{
			text-align:center;
		}
		td{
			width:250px;
			height:30px;
		}
		table{
			margin-left:2%;
			width:95%;
			text-align:center;
		}
	</style>
</head>
	<body style="background-color:#FFF8D7">
	
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
		
		
		
		<div id="menu">
		<h1 style="font-size:30px;text-shadow:1px 1px 1px orange">我 的 订 单</h1>
		<hr id="hr1">
			<div id="listorder">
				<table>
					<tr>
						<td style="font-size:20px;border-bottom:2px solid orange">店铺名称</td>
						<td style="font-size:20px;border-bottom:2px solid orange">总金额</td>
						<td style="font-size:20px;border-bottom:2px solid orange">时间</td>
						<td style="font-size:20px;border-bottom:2px solid orange">付款状态</td>
						<td style="font-size:20px;border-bottom:2px solid orange">配送状态</td>
						<td style="font-size:20px;border-bottom:2px solid orange">收货状态</td>
						<td style="font-size:20px;border-bottom:2px solid orange">操作</td>
					</tr>
					<c:choose>
						<c:when test="${not empty requestScope.SHOPS }">
							<c:forEach items="${requestScope.SHOPS }" var="shop">
								<c:forEach items="${requestScope.pageBean.pageData }" var="order" >
									<c:if test="${order.shop_id == shop.shop_id }">
										<tr>
											<td>${shop.shop_name }</td>
											<td>&yen;${order.order_price }</td>
											<td>${order.order_time }</td>
											<td>
												<c:choose>
													<c:when test="${order.order_pay == 0 }">未付款</c:when>
													<c:otherwise>已付款</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${order.order_deli == 0 }">未配送</c:when>
													<c:otherwise>已配送</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${order.order_get == 0 }">未收货</c:when>
													<c:otherwise>已收货</c:otherwise>
												</c:choose>
											</td>
											<td>
												<a href="${pageContext.request.contextPath }/UserServlet?method=getOrderDetail&orderId=${order.order_id}" >详细</a>
												<a href="${pageContext.request.contextPath }/UserServlet?method=writeCommend&shopId=${order.shop_id}" >评论</a>
												<a href="${pageContext.request.contextPath }/UserServlet?method=admitGet&orderId=${order.order_id}" >确认收货</a>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:when>
						<c:otherwise>
						没有订单数据
						</c:otherwise>
					</c:choose>
				</table>
				
			</div>
			
			<hr>
			<!-- 底部导航栏 -->
			<div id="page" align="center">
				当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页     &nbsp;&nbsp;
				<a href="${pageContext.request.contextPath }/UserServlet?method=getMyOrder&currentPage=1">首页</a>&nbsp;
				<a href="${pageContext.request.contextPath }/UserServlet?method=getMyOrder&currentPage=${requestScope.pageBean.currentPage-1}">上一页 </a>&nbsp;
				<a href="${pageContext.request.contextPath }/UserServlet?method=getMyOrder&currentPage=${requestScope.pageBean.currentPage+1}">下一页 </a>&nbsp;
				<a href="${pageContext.request.contextPath }/UserServlet?method=getMyOrder&currentPage=${requestScope.pageBean.totalPage}">末页</a>
		    </div> 
		</div>
	</body>
</html>