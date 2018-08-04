<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>发表评论</title>
	<style type="text/css">
		body{
			background-image:url('${pageContext.request.contextPath }/sys/style/images/back.jpg');
			background-size:100% 800px;
		}
		#menu{
			margin:auto;
			width:80%;
			border:1px solid black;
			border-radius:5px;
			background: rgba(255,228,181, 0.5);
		}
		#message{
			width:95%;
			height:150px;
			margin:10px;
		}
		#count{
			width:65%;
			height:200px;
			margin-top:20px;
			margin-left:15%;
			border:4px solid orange;
			border-radius:3px;
			list-style:none;
			float:left;
		}
		#comment{
			position:absolute;
			left:35%;
			bottom:40%;
		}
		#title{
			font-family:"黑体";
			font-size:22px;
			color:red;
			margin-left:5px;
			margin-top:5px;
			text-shadow:0.5px 0.5px 0.5px #000; 
		}
		#hr2{
			width:100%;
			height:10px;
			border:none;
			border-top:5px groove red;"
		}
		a{
			position:absolute;
			left:24%;
			bottom:40%;
		}
		a:hover{
			border-bottom:1px solid chocolate;
			color:red;
		}
	</style>
	<script type="text/javascript">
		// 下单
		function generateOrder() {
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
			<div id="title">
				写评论
			</div>
			<hr id="hr2">
				<form id="comment" action="${pageContext.request.contextPath }/UserServlet?method=saveCommend" method="post">
					<textarea type="text" name="commend" maxlength="150" style="font-size:16px;resize:none;width:350px;height:100px;"></textarea>
					<input type="hidden" name="shopId" value="${sessionScope.SHOP_MSG.shop_id }">
					<br>
					<input style="margin-left:42%" type="submit" value="提交"/>
				</form>
				<a href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
			</div>
			
		</div>
	</body>
</html>