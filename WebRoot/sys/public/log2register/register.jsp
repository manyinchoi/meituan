<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>注册</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/register.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			#top{
				width:100%;
				height:50px;
			}
			#foot{
				margin:auto;
				margin-top:15px;
				width:500px;
			}
			table{
				margin:auto;
				width:100%;
				height:400px
			}
			td{
				text-align:center;
				font-size:30px;
				width:50px;
			}
		</style>
		<script type="text/javascript">
			
		</script>
		
	</head>
	<body>
		<!-- 上部分是一张美团图片 -->
		<div id="top">
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:100px;height:100%;border-radius:5px" />
			<hr color="orange">
		</div>
		
		<!-- 下面是注册页面 -->
		<br><br>
		<div align="center" id="foot">
		<form action="${pageContext.request.contextPath }/UserServlet?method=register" method="post">
			<table>
				<th colspan="3"><h2>用 户 注 册</h2></th>
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="username" name="username" maxlength="10" style="width:131px;height:25px;margin-left:-100px;"/></td>
					<td style="	width:70px;color:red;font-size:2px;"><span id="nameID"></span></td>
				</tr>
				<tr>
					<td>手机号：</td>
					<td><input type="text" id="phone" name="phone" maxlength="15" style="width:131px;height:25px;margin-left:-100px;"/></td>
					<td style="	width:70px;color:red;font-size:2px;"><span id="phoneID"></span></td>
				</tr>
				<tr>
					<td>密&nbsp;&nbsp;&nbsp;码：</td>
					<td><input type="password" id="password" name="password" maxlength="10" style="width:131px;height:25px;margin-left:-100px;"/></td>
					<td style="	width:70px;color:red;font-size:2px;"><span id="passwordID"></span></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="同意以下协议并注册"
						style="background-color:orange;width:50%;height:100%;" />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center"><a href="#" style="text-decoration:none;">《美团网用户协议》</a></td>
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
				divNode1.innerHTML="<img src='${pageContext.request.contextPath }/adminSys/style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp用户名不能为空！";
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
		var inputNode3 = document.getElementById("phone");
		inputNode3.onblur=function check3(){
			if(inputNode1.value=="" || inputNode1.value.trim()==""){
				var divNode3 = document.getElementById("phoneID");
				divNode3.innerHTML="<img src='${pageContext.request.contextPath }/adminSys/style/images/tips.png' style='height:30px;vertical-align:middle'/>&nbsp;手机号不能为空！";
			}else{
				var divNode3 = document.getElementById("phoneID");
				divNode3.innerHTML="";
			}
		}
	</script>
</html>