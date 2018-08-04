<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>店铺首页</title>
	<!--<link href="${pageContext.request.contextPath }/sys/style/css/listmenu.css" rel="stylesheet" type="text/css">  -->
	<style type="text/css">
		body{
			background-image:url('${pageContext.request.contextPath }/sys/style/images/back.jpg');
			background-size:100%100%;
		}
		#top{
			width:100%;
			height:50px;
			margin-bottom:25px
		}
		#outer{
			margin:auto;
			width:77%;
			border:1px solid black;
			background: rgba(255,228,181, 0.5);
		}
		#hr1{
			width:100%;
			height:10px;
			margin-bottom:-25px;
			border:none;
			border-top:5px groove skyblue;"
		}
		#hr2{
			width:100%;
			height:10px;
			border:none;
			border-top:5px groove red;"
		}
		#message{
			width:95%;
			height:150px;
			margin:10px;
		}
		#foodID ul li{
			list-style:none;
			float:left;
			width:253px;
			height:100%;
			background:url(img/bg2.gif) no-repeat;
			margin-left:3px;
			margin-top:8px;
			padding-top:5px;
			border:2px solid orange;
			border-radius:3px;
		}
		#listfood{
			width:85%;
			margin:10px;
			list-style:none;
			float:left;
		}
		#commend{
			width:75%;
			margin:10px;
			border:1px solid red;
			background: rgba(255,228,181, 0.8);
		}
		#title{
			font-family:"黑体";
			font-size:22px;
			color:red;
			margin-left:5px;
			margin-top:5px;
			text-shadow:0.5px 0.5px 0.5px #000; 
		}
		#clear{
			clear:both;
		}
		#url_info {
			display: block;
			position: fixed;
			right: 12%;
			bottom: 0;
			width: 50px;
			padding: 5px;
			-moz-border-radius: 4px;
			-webkit-border-radius: 4px;
			border-radius: 4px;
			background: transparent;
			color: #E8E8E8;
		}
		ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		}
		td{
			width:200px;
		}
	</style>
</head>


	<body style="background-color:#F8F8F8 ">
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
		<div id="outer" style="background: rgba(255,228,181, 0.5);">
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
			<hr id="hr1">
			<br>
			<!-- 列出所有的菜品 -->
			<div id="listfood">
				<div id="foodID">
					<ul>
						<c:forEach items= "${requestScope.FOODS}" var= "food">
							<li>
								<dl>
									<dt>
										<a href="${pageContext.request.contextPath }/UserServlet?method=getFoodDetail&food_id=${food.food_id}">
											<img style="margin-left:7px" width="240px" height="160px" src="${pageContext.request.contextPath }/${food.food_pic}" />
										</a>
									</dt>
									<dd>
										<a style="text-decoration:none;">${food.food_name}</a>
									</dd>
									<dd>
										<a style="text-decoration:none;">&yen;${food.food_price}</a>
									</dd>
									<dd>
										<c:choose>
							        		<c:when test="${not empty sessionScope.USER }">
												<a href="${pageContext.request.contextPath }/UserServlet?method=putInCar&food_id=${food.food_id }" style="text-decoration:none;">
													<img style="margin-left:144px" alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/plus.png">
												</a>
							        		</c:when>
							        		<c:otherwise>
							        			<img style="margin-left:144px" alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/plus.png">
							        		</c:otherwise>
							        	</c:choose>
									</dd>
								</dl>
							</li>
						</c:forEach >
					</ul>
				</div>
			</div>
			
			 <!-- 撤销float：left的效果 -->
	     	<div id="clear"></div>
	     	<hr id="hr2">
			<!-- 展示用户的评论 -->
			<div id="commend">
				<div id="title">
					用户评论区
				</div>
				<hr>
				<table style="width:100%;margin-bottom:10px;">
					<c:choose>
						<c:when test="${not empty requestScope.REVIEWS and not empty requestScope.USERS }">
							<c:forEach items="${requestScope.REVIEWS }" var="review">
								<c:forEach items="${requestScope.USERS }" var="user">
									<c:if test="${user.user_id == review.user_id }">
										<tr>
											<td style="border-top:orange solid 2px;border-bottom:orange solid 2px;">${user.user_name }:</td>
											<td style="width:85%;border-top:orange solid 2px;border-bottom:orange solid 2px;">${review.rev_desc }</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div style="text-align:center;margin-bottom:5px">暂时没有用户评论！！</div>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			
			<!-- 我的餐车 -->
        	<div id="url_info" style="width:14%;height:8%;line-height: 0;z-index: 50;">
        		<c:choose>
	        		<c:when test="${not empty sessionScope.USER }">
		        		<a href="${pageContext.request.contextPath }/sys/public/cart/cartMenu.jsp" >
		        			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/car.png">
		        		</a>
	        		</c:when>
	        		<c:otherwise>
	        			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/car.png">
	        		</c:otherwise>
	        	</c:choose>
        	</div>
		</div>
	</body>
</html>