<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商家登录</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/login.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<!-- 上部分是一张美团图片 -->
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
		
		<!-- 下面是登录页面 -->
		<div align="center" id="foot">
		<form action="${pageContext.request.contextPath }/ShopAccountServlet?method=login" method="post">
			<br><br>
			<table height="300px" >
				<th colspan="3"><h2>商 家 登 录</h2></th>
				<tr>
					<td style="width:40px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账&nbsp;号：</td>
					<td style="width:130px;"><input type="text" name="username" id="username" maxlength="10" style="width:131px;height:25px"/></td>
					<td style="width:130px;"><span id="nameID" style="font-size:15px;"></span></td>
				</tr>
				
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密&nbsp;码：</td>
					<td><input type="password" name="password" id="password" maxlength="10" style="width:131px;height:25px"/></td>
					<td><span id="passwordID" style="font-size:15px;"></span></td>
				</tr>
				<tr>
					<td colspan="3" align="center" style="padding-top:20px">
					&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="登 录"
						style="background-color:orange;font-size:30px;width:30%;height:90%;" />
						<a href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
					</td>
				</tr>
			</table>
		</form>
		</div>
		
	</body>
	
	<script>
		var inputNode1 = document.getElementById("username");
		inputNode1.onblur=function check1(){
			if(inputNode1.value=="" || inputNode1.value.trim()==""){
				var divNode1 = document.getElementById("nameID");
				divNode1.innerHTML="<img src='./style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;账户不能为空！";
			}else{
				var divNode1 = document.getElementById("nameID");
				divNode1.innerHTML="";
			}
		}
		var inputNode2 = document.getElementById("password");
		inputNode2.onblur=function check2(){
			if(inputNode2.value=="" || inputNode2.value.trim()==""){
				var divNode2 = document.getElementById("passwordID");
				divNode2.innerHTML="<img src='./style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;密码不能为空！";
			}else if(inputNode2.value.length<6 || inputNode2.value.length>10){
				var divNode2 = document.getElementById("passwordID");
				divNode2.innerHTML="<img src='./style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;密码必须为6~10个字符！";
			}else{
				var divNode2 = document.getElementById("passwordID");
				divNode2.innerHTML="";
			}
		}
	</script>
</html>