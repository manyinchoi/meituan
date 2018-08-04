<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>菜品详情</title>
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
		#outer{
			margin:auto;
			width:77%;
			background: rgba(255,228,181, 0.5);
		}
		#message{
			width:95%;
			height:150px;
			margin:10px;
		}
		#clear{
			clear:both;
		}
		ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		}
		td{
			width:200px;
		}
		#fooddetail{
			width:75%;
			margin:10px;
			list-style:none;
			float:left;
			border:2px solid orange;
			border-radius:3px;
			background: rgba(255,228,181, 0.5);
		}
		#img{
			float:left;
			width:300px;
			height:300px;
		}
		#foodmessage{
			float:left;
			width:400px;
			height:100px;
			margin-left:50px;
			font-size:25px;
		}
		#button{
			border:1px solid red;
			width:120px;
			height:auto;
			margin-left:80%;
			margin-bottom:15px;
			border-radius:6px;
			background: rgba(218,165,32,0.5);
		}
		.apply{
			display:block; 
			width:100%;
			height:100%;
			font-size:23px;
			color:black;
			text-shadow:1px 1px 1px orange;
		}
		#button a:hover{
			background:rgba(2,2,2,0.1);
		}
		#back a:hover{
			border-bottom:2px solid orange;
			color:red;
		}
	</style>
</head>
	<body style="background-color:#F8F8F8 ">
		<div id="outer" style="background-color:#FFFFFF ">
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
			
			<!-- 列出所有的菜品 -->
			<div id="fooddetail">
				<!-- 菜品图片 -->
				<div id="img">
					<img width="100%" height="200px" alt="sorry" src="${pageContext.request.contextPath }/${requestScope.FOOD_MSG.food_pic}">
				</div>
				
				<!-- 菜品信息 -->
				<div id="foodmessage">
					<font style="font-size:35px;font-family:'楷体'">菜品介绍</font>
					<hr>
					<table style="font-family:'楷体';font-size:29px;">
						<tr>
							<td>菜名</td>
							<td style="width:70%">
								${requestScope.FOOD_MSG.food_name }
							</td>
						</tr>
						<tr>
							<td>单价</td>
							<td>
								&yen;${requestScope.FOOD_MSG.food_price }
							</td>
						</tr>
						<tr>
							<td>介绍</td>
							<td>
								${requestScope.FOOD_MSG.food_desc }
							</td>
						</tr>
					</table>
				</div>
				
				<!-- 撤销float：left的效果 -->
	     		<div id="clear"></div>
	     		
				<!-- 添加和返回按钮 -->
	     		<div id="back" style="float:left;margin-top:10px;margin-left:14px">
	     			<a href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
	     		</div>
				<div id="button">
					<c:choose>
		        		<c:when test="${not empty sessionScope.USER }">
							<a class="apply" href="${pageContext.request.contextPath }/UserServlet?method=putInCar&food_id=${requestScope.FOOD_MSG.food_id }" style="text-decoration:none;">
								加入购物车
							</a>
		        		</c:when>
		        		<c:otherwise>
		        			加入购物车
		        		</c:otherwise>
		        	</c:choose>
				</div>
			</div>
			
		</div>
	</body>
</html>