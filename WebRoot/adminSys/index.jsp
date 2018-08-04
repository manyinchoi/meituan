<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>管理员登录</title>
		<link href="${pageContext.request.contextPath }/adminSys/style/css/index.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<!-- 背景特效 -->
		<script src="/demos/googlegg.js"></script>
		<canvas id="canvas"></canvas>
		<div class="wrapper">
		  <div class="colorizer1"></div>
		  <div class="colorizer2"></div>
		  <div class="colorizer3"></div>
		  <div class="colorizer4"></div>
		  <div class="circles">
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		    <div class="circle"></div>
		  </div>
		</div>
		
		<!-- 标题 -->
		<div id="Head_1_Logo"  align="center" >
			<b style="font-family:'黑体'">后台管理中心</b> 
        </div>
		<div id="outer">
			<!-- 登录表单 -->
			<div align="center" id="right">
				<h1>欢迎登录</h1>
				<form action="${pageContext.request.contextPath}/AdminServlet?method=login" method="post">
					
					<hr width="100%" color="#ccc">
					用户名：<input type="text" id="adminName" name="adminName" maxlength="10"
						   style="margin-top:55px; margin-bottom:5px;border-radius: 8px">
					<div id="tip1"></div>										
					密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password" maxlength="10"
						   style="margin-top:25px; margin-bottom:5px;border-radius: 8px">
					<div id="tip2"></div>
					<br>	
					<input type="submit" value="登&nbsp;&nbsp;&nbsp;录" id="submit"/>
				</form>
			</div>
		</div>
		
	</body>
	
	<script>
		var inputNode1 = document.getElementById("adminName");
		inputNode1.onblur=function check1(){
			if(inputNode1.value=="" || inputNode1.value.trim()==""){
				var divNode1 = document.getElementById("tip1");
				divNode1.innerHTML="<img src='./style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;用户名不能为空！";
			}else{
				var divNode1 = document.getElementById("tip1");
				divNode1.innerHTML="";
			}
		}
		var inputNode2 = document.getElementById("password");
		inputNode2.onblur=function check2(){
			if(inputNode2.value=="" || inputNode2.value.trim()==""){
				var divNode2 = document.getElementById("tip2");
				divNode2.innerHTML="<img src='./style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;密码不能为空！";
			}else if(inputNode2.value.length<6 || inputNode2.value.length>10){
				var divNode2 = document.getElementById("tip2");
				divNode2.innerHTML="<img src='./style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;密码必须为6~10个字符！";
			}else{
				var divNode2 = document.getElementById("tip2");
				divNode2.innerHTML="";
			}
		}
	</script>
</html>