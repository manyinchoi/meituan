<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>购物车</title>
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
		#button{
			font-size:15px;
		}
		#back{
			font-size:14px;
		}
		a:hover{
			border-bottom:1px solid orange;
			color:red;
		}
	</style>
	<script type="text/javascript">
	 // 删除菜品项
		function removeOrder(node) {
			var gid = node.lang;
			window.location.href = "${pageContext.request.contextPath}/UserServlet?method=removeOrder&gid="+gid;
		}
		
		// 修改菜品项数量
		function alterOrder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "${pageContext.request.contextPath}/UserServlet?method=alterOrder&gid="+gid+"&snumber="+snumber;
		}
		// 下单
		function generateOrder() {
			window.location.href = "ensureOrder.jsp";
		}
	</script>
</head>
	<body>
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
				<table align="center" width="100%" style="font-size:20px" >
					<tr height="40">
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">菜名</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">单价</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">数量</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">小计</td>
				 		<td align="center" width="20%" style="border-bottom:orange solid 2px;">操作</td>
				 	</tr>
					<c:forEach items="${sessionScope.foods }" var="food">
						<c:if test="${sessionScope.SHOP_ID ==  food.key.shop_id }">
							<tr height="60">
								<td align="center" width="20%">${food.key.food_name }</td>
						 		<td align="center" width="20%">￥${food.key.food_price }</td>
						 		<td align="center" width="20%">
						 			<input style="font-size:16px" type="text" value="${food.value }" size="3" lang="${food.key.food_id }" onblur="alterOrder(this)"/>
						 		</td>
						 		<td align="center" width="20%">${food.key.food_price*food.value }</td>
						 		<td align="center" width="20%">
						 			<input type="button" value="删除" class="btn_next" lang="${food.key.food_id }" onclick="removeOrder(this)" />
				 				</td>
				 				<c:set var="sum" value="${sum+food.key.food_price*food.value}"></c:set>
						 	</tr>
					 	</c:if>
					</c:forEach>
					 		

					<tr>
						<td colspan="6" align="right"><hr>总计:
							<span style="font-size:36px;">&yen;&nbsp;<c:out value="${sum }"></c:out></span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
						<c:choose>
							<c:when test="${not empty sessionScope.foods }" >
								<input type="hidden" name="bId" value="">
								<input id="button" type="button" value="下单" class="btn_next" onclick="generateOrder()" />
								<a id="back" href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
							</c:when>
							<c:otherwise>
								<input id="button" type="button" value="下单" class="btn_next" disabled>
								<a id="back" href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</table>
			</div>
			
		</div>
	</body>
</html>