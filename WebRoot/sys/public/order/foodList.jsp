<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>订单详情</title>
	<style type="text/css">
		body{
			background-image:url('${pageContext.request.contextPath }/sys/style/images/back.jpg');
			background-size:100% 800px;
		}
		#top{
			width:100%;
			height:50px;
			margin-bottom:25px
		}
		#hr1{
			width:90%;
			height:10px;
			border:none;
			border-top:5px groove skyblue;
		}	
		#outer{
			margin:auto;
			border:4px solid orange;
			border-radius:3px;
			text-align:center;
			width:75%;
		}
		#MainArea{
			text-align:center;
			width:100%;
			height:400px;
		}
		#hr1{
			width:100%;
			height:10px;
			border:none;
			border-top:5px groove skyblue;
		}
		h1{
			text-align:center;
		}
		a{
			text-decoration:none;
		}
		a:hover{
			color:red;
			border-bottom:1px solid chocolate;
		}
		td{
			width:200px;
			height:30px;
		}
	</style>
</head>
	<body style="background-color:#FFF8D7">
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
	
		<div id="outer">
			<!-- 主内容区域（数据列表或表单显示） -->
			<h1 style="font-size:30px;text-shadow:1px 1px 1px orange">订 单 详 情</h1>
			<hr id="hr1">
			<div id="MainArea">
				<table align="center" cellspacing="0" cellpadding="0">
					<!-- 表头-->
					<thead>
						<tr align="center" valign="middle" ">
							<td style="font-size:20px;border-bottom:2px solid orange">菜名</td>
							<td style="font-size:20px;border-bottom:2px solid orange">单价</td>
							<td style="font-size:20px;border-bottom:2px solid orange">数量</td>
							<td style="font-size:20px;border-bottom:2px solid orange">小计</td>
						</tr>
					</thead>
					<!--显示数据列表 -->
					<tbody id="TableData">
						<c:forEach items="${requestScope.orderDetail}" var="od">
					 		<tr align="center">
					 		<c:forEach items="${requestScope.foods}" var="food">
					 			<c:if test="${food.food_id==od.food_id }">
					 				<c:set var="foodName" value="${food.food_name}"/>
					 				<c:set var="foodPrice" value="${food.food_price }"/>
					 			</c:if>
					 		</c:forEach>
						 		<td><c:out value="${foodName}"/> </td>
						 		<td><c:out value="${foodPrice}"/></td>
						 		<td>${od.food_count}</td>
						 		<td>${od.food_count*foodPrice }</td>
						 		<c:set var="sum" value="${sum+od.food_count*foodPrice}"></c:set>
					 		</tr>
					 	</c:forEach>
					 	<tr>
							<td colspan="6" align="right"><hr>总计:
								<span style="font-size:36px;">&yen;&nbsp;<c:out value="${sum }"></c:out></span>
								<label
									id="counter" style="font-size:36px"></label>
							</td>
						</tr>
					</tbody>
				</table>
				<a style="position:absolute;bottom:27%" href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
			</div>
			<!-- 配送信息 -->
			<div id="deliver">
				
			</div>
		</div>
	</body>	
</html>