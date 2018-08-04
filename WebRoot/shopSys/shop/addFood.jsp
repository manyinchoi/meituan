<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title>无线点餐平台</title>
	<style type="text/css">
		body {
    		background-color:#EEE8AA;
    	}
		#MainArea{
			width:500px;
			margin:auto;
		}
		#InputDetailBar{
			text-align:center;
		}
		table{
			margin:auto;
			width:500px;
			border:3px solid orange;
			border-radius: 8px;
			color:#262626;
			box-shadow:0px 0px 8px 4px #aaa;
		}
		td{
			font-size:20px;
			width:50px;
			border:1px solid orange;
		}
		td input{
			border:none;
			width:99%;
			font-size:15px;
			background-color:#EEE8AA;
		}
		textarea{
			font-size:15px;
			font-family:微软雅黑;
			background-color:#EEE8AA;
			border:none;
			rows:2;
			width:98%;
			resize: none; 
			maxlength:100; 
		}
		h2{
			margin-top:2%;
			text-align:center;
			font-size:35px;
			color:black;
			text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15); 
		}
		hr{
			width:60%;
			height:10px;
			margin-bottom:20px;
			border:none;
			border-top:10px groove skyblue;"
		}
		a{
			color:orange;
			text-decoration:none;
		}
		a:hover{
			border-bottom: 2px solid orange;
		}
		input:hover{
			background:linear-gradient(#F5DEB3,#F4A460); 
 			color: black;
		}
		textarea:hover{
			background:linear-gradient(#F5DEB3,#F4A460); 
 			color: black;
		}
		input:hover,textarea:hover,input:focus,textarea:focus { border-color: black; }
		#submit{
			background-color:#F0C17D;
			margin-top:10px;
			width:20%;
			font-size:20px;
			font-family:微软雅黑;
			font-weight:bold;
			border-radius: 6px;
		}
		#submit input:hover{
 			background:linear-gradient(#F5DEB3,#F4A460); 
 			color: black; 
		}
	</style>
	</head>
<body>

<h2>添加菜品</h2>
<hr>
<div id="MainArea">
	<!-- 表单内容 -->
	<form action="${pageContext.request.contextPath }/ShopServlet?method=addFood" method="post" enctype="multipart/form-data">
		<table cellpadding="0" cellspacing="0" >
			<tr>
				<td width="80px" style="text-align:center;">菜&nbsp;&nbsp;名:</td>
				<td width="131px"><input type="text" name="food_name"  value=""/> </td>
			</tr>
			<tr>
				<td width="80px" style="text-align:center;">价&nbsp;&nbsp;格:</td>
				<td><input type="text" name="food_price" value=""/> </td>
			</tr>
			<tr>
				<td width="80px" style="text-align:center;">简&nbsp;&nbsp;介:</td>
				<td><textarea name="food_desc"></textarea></td>
			</tr>
			<tr>
				<td width="80px" style="text-align:center;">菜品图片:</td>
				<td>
					
					<input style="width:200px;font-size:15px" type="file" name="food_pic"/> 
				</td>
			</tr>
		</table>
		
		<!-- 表单操作 -->
		<div id="InputDetailBar">
			<input id="submit" type="submit" value="添 加">
			<br>
            <a href="javascript:history.go(-1);" >返回</a>
        </div>
	</form>
</div>
</body>
</html>
