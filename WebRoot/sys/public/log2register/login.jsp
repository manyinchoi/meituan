<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户登录</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/login.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			#img{
				opacity:0.7;
				filter:alpha(opacity=40);
				margin-top:23%; 
				margin-left:10%;
				border-radius:40px;
				width:60%;
			}
			#outer{
				margin:auto;
				width:75%;
			}
			#left{
				float:left;
			}
			#right{
				margin-top:150px;
				margin-bottom:100px;
				margin-left:-95px;
				float:left;
				width:400px;
			}
			table{
				margin:auto;
				width:450px;
				height:300px;
				border-radius: 20px;
			}
			td{
				text-align:center;
				font-size:27px;
			}
		</style>
	</head>
	
	
	<body>
		<div id="outer">
			<!-- 左边是图片 -->
			<div id="left">
				<img id="img" alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/login.png" />
			</div>
			
			<!-- 右边是登录界面 -->
			<div align="center" id="right">
				<form action="${pageContext.request.contextPath }/UserServlet?method=login" method="post">
					<table >
						<th colspan="3"><h2>欢 迎 登 录</h2></th>
						<tr>
							<td>用户名：</td>
							<td><input type="text" id="username" name="username" maxlength="10" style="width:111px;height:25px;margin-left:-80px"/></td>
							<td style="	width:70px;color:red;font-size:2px;"><span id="nameID"></span></td>
						</tr>
						<tr>
							<td >密&nbsp;&nbsp;&nbsp;码：</td>
							<td><input type="password" id="password" name="password" maxlength="10" style="width:111px;height:25px;margin-left:-80px"/></td>
							<td style="	width:70px;color:red;font-size:2px;"><span id="passwordID"></span></td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="submit" value="登录"  
								style="background-color:orange;width:40%;height:90%;font-size:27px"/>
							</td>
						</tr>
						
					</table>
				</form>
			</div>
		</div>
	</body>
	
	<script>
		var inputNode1 = document.getElementById("username");
		inputNode1.onblur=function check1(){
			if(inputNode1.value=="" || inputNode1.value.trim()==""){
				var divNode1 = document.getElementById("nameID");
				divNode1.innerHTML="<img src='${pageContext.request.contextPath }/adminSys/style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;用户名不能为空！";
			}else{
				var divNode1 = document.getElementById("nameID");
				divNode1.innerHTML="";
			}
		}
		var inputNode2 = document.getElementById("password");
		inputNode2.onblur=function check2(){
			if(inputNode2.value=="" || inputNode2.value.trim()==""){
				var divNode2 = document.getElementById("passwordID");
				divNode2.innerHTML="<img src='${pageContext.request.contextPath }/adminSys/style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;密码不能为空！";
			}else if(inputNode2.value.length<6 || inputNode2.value.length>10){
				var divNode2 = document.getElementById("passwordID");
				divNode2.innerHTML="<img src='${pageContext.request.contextPath }/adminSys/style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;密码必须为6~10个字符！";
			}else{
				var divNode2 = document.getElementById("passwordID");
				divNode2.innerHTML="";
			}
		}
	</script>
</html>