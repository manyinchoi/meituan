<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商家注册</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/register.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<!-- 上部分是一张美团图片 -->
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
		
		<!-- 下面是注册页面 -->
		<div align="center" id="foot">
		<br><br>
		<form action="${pageContext.request.contextPath }/ShopAccountServlet?method=register" method="post">
			<table width="200px" height="300px" >
				<th colspan="3"><h2>商 家 注 册</h2></th>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账&nbsp;号：</td>
					<td><input type="text" name="username" maxlength="10" style="width:131px;height:25px"/></td>
					<td><span id="nameID"></span></td>
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;码：</td>
					<td><input type="password" name="password" maxlength="10" style="width:131px;height:25px"/></td>
					<td><span id="passwordID"></span></td>
				</tr>
				<tr>
					<td colspan="3" align="center" style="height:70px">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="同意以下协议并注册"
						style="background-color:orange;width:50%;height:60%;" />
						<a href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
				</tr>
				<tr>
					<td colspan="3" align="center" style="height:20px;">
						<a href="#" style="text-decoration:none;height:50%;">《美团网用户协议》</a>
					</td>
				</tr>
				
			</table>
		</form>
		</div>
		
	</body>
</html>