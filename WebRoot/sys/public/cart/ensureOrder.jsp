<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>确认订单</title>
	<style type="text/css">
		body{
			background-image:url('${pageContext.request.contextPath }/sys/style/images/back.jpg');
			background-size:100% 800px;
		}
		#menu{
			margin:auto;
			width:80%;
			border:1px solid black;
			background: rgba(255,228,181, 0.5);
		}
		#message{
			width:95%;
			height:150px;
			margin:10px;
		}
		#count{
			width:75%;
			margin:10px;
			border:3px solid orange;
			border-radius:3px;
			list-style:none;
			float:left;
		}
	</style>
	<script type="text/javascript">
		// 下单
		function generateOrder() {
			alert("下单成功!");
			window.location.href = "${pageContext.request.contextPath }/UserServlet?method=takeOrder";
		}
	</script>
</head>
	<body style="background-color:#FFF8D7">
		<div id="menu">
			<!-- 展示商家店铺的基本信息 -->
			<div id="message">
				<table>
					<c:choose>
						<c:when test="${not empty sessionScope.SHOP_MSG }">
							<tr>
								<td rowspan="2">
									<img width="214px" height="145px" src="${pageContext.request.contextPath }/${sessionScope.SHOP_MSG.shop_pic}" />
								</td>
								<td style="border-right:orange solid 2px;font-size:30px;text-align:center" rowspan="2">${sessionScope.SHOP_MSG.shop_name }</td>
								<td style="border-bottom:orange solid 2px;text-align:center">店铺介绍</td>
								<td style="border-right:orange solid 2px;border-bottom:orange solid 2px;">${sessionScope.SHOP_MSG.shop_desc }</td>
								
							</tr>
							<tr>
								<td style="text-align:center">店铺地址</td>
								<td style="border-right:orange solid 2px;">${sessionScope.SHOP_MSG.shop_add }</td>
							</tr>
						</c:when>
						<c:otherwise>
							没有数据，请先保存
						</c:otherwise>
					</c:choose>
				</table>
			</div>
		
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%" style="font-size:20px">
					<tr height="40">
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">菜名</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">单价</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">数量</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">小计</td>
				 	</tr>
					<c:forEach items="${sessionScope.foods}" var="entry">
						<c:if test="${sessionScope.SHOP_ID ==  entry.key.shop_id }">
							<tr height="60">
						 		<td align="center" width="20%">${entry.key.food_name }</td>
						 		<td align="center" width="20%">￥${entry.key.food_price }</td>
						 		<td align="center" width="20%">${entry.value}</td>
						 		<td align="center" width="20%">${entry.key.food_price*entry.value}</td>
					 		</tr>
					 		<c:set var="sum" value="${sum+entry.key.food_price*entry.value}"></c:set>
				 		</c:if>
					</c:forEach>

					<tr>
						<td colspan="6" align="right"><hr>总计: <span
							style="font-size:36px;">&yen;&nbsp;<c:out value="${sum }"></c:out></span>
							<label id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; font-size:15px; text-align: center;" align="right">
							<input type="hidden" name="bId" value="">
							<input type="button" value="结账" class="btn_next" lang=""
							onclick="generateOrder()" />
						</td>
					</tr>
				</table>
			</div>
			
		</div>
	</body>
</html>