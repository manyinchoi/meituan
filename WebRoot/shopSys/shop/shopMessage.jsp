<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>店铺信息</title>
		<style type="text/css">
			body {
    			background-color:#EEE8AA;
    		}
			#foot{
				margin:auto;
				margin-top:15px;
				width:800px;
			}
			#tips{
				color:black;
				margin-top:20%;
				font-size:25px;
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
				background-color:#EEE8AA;
				border:none;
				rows:2;
				width:98%;
				resize: none; 
				maxlength:100; 
			}
			h2{
				text-align:center;
				font-size:35px;
				color:black;
				text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15); 
			}
			hr{
				width:70%;
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
				text-decoration:underline;
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
			select{
				height:30px;
				padding:0 3%;
				font-size:15px;
			}
			option{
				text-align:center;
				background-color: #F5DEB3;
			}
			#submit{
				background-color:#F0C17D;
				width:100%;
				font-size:20px;
				font-family:微软雅黑;
				font-weight:bold;
			}
			#submit input:hover{
 				background:linear-gradient(#F5DEB3,#F4A460); 
 				color: black; 
			}
		</style>
	</head>
	<body>
		<h2>店铺信息修改</h2>
		<hr>
		<div align="center" id="foot">
		<form action="${pageContext.request.contextPath }/ShopServlet?method=updateShop" method="post" enctype="multipart/form-data">
			<table cellpadding="0" cellspacing="0">
                    	
				<tr>
					<td width="80px" style="text-align:center;">菜&nbsp;&nbsp;系:</td>
					<td>
                        <select name="style_id" style="width:80px">
                        	
	                        <c:forEach items="${requestScope.shopStyle}" var="types">
	                          	<c:choose>
		                            <c:when test="${requestScope.SHOP_MSG.style_id==types.style_id}">
										<option value="${types.style_id}" selected="selected">${types.style_name }</option>
									</c:when>
									<c:otherwise>
										<option value="${types.style_id}">${types.style_name }</option>
									</c:otherwise>
			   					</c:choose>
		  					</c:forEach>	   					
                        </select>
                        <input type="hidden" name="shop_id" value="${requestScope.SHOP_MSG.shop_id}" />
                     </td>
				</tr>
				<tr>
					<td style="text-align:center;">店&nbsp;&nbsp;名:</td>
					<td><input style="width:180px;height:25px" type="text" name="shop_name" value="${requestScope.SHOP_MSG.shop_name}"/> </td>
				</tr>
				<tr>
					<td style="text-align:center;">联系电话:</td>
					<td><input style="width:180px;height:25px" type="text" name="shop_ctc" value="${requestScope.SHOP_MSG.shop_ctc}"/> </td>
				</tr>
                <tr>
					<td style="text-align:center;">地&nbsp;&nbsp;址:</td>
					<td><textarea name="shop_add">${requestScope.SHOP_MSG.shop_add}</textarea></td>
				</tr>
				
				<tr>
					<td style="text-align:center;">店铺介绍:</td>
					<td><textarea name="shop_desc">${requestScope.SHOP_MSG.shop_desc}</textarea></td>
				</tr>
				<tr>
					<td style="text-align:center;">店铺图片:</td>
					<td>
						<img style='margin-left:35%;margin-top:2%;max-width:90px;width:90px;width:expression(width>90?"90px":width "px");max-width: 90px;' 
								src="${pageContext.request.contextPath }/${requestScope.SHOP_MSG.shop_pic}">
						<br>
						<input style="margin-left: 25%;margin-bottom:2%;width:190px" type="file" name="shop_pic"/> 
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input id="submit" type="submit" value="修改并保存" >
					</td>
				</tr>
			</table>
		</form>
		</div>
		
	</body>
</html>