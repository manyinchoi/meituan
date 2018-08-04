<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>注册</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/welcome.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<!-- 上部分是一张美团图片 -->
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
		
		<!-- 下面是注册页面 -->
		<h2 style="z-index:900;font-size:50px;position:absolute;color:white;left:30%;top:6%; ">欢迎访问美团商家中心</h2>
		<div align="center" id="foot">
			<table>
				<tr style="height:180px; border-spacing:100px">
					<td><a href="login.jsp">登录</a></td>
					<td><a href="register.jsp">注册</a></td>
				</tr>
			</table>
		</div>
		
	</body>
</html>