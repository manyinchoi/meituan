<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>开店申请</title>
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
				width:400px;
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
			}
			h2{
				font-size:35px;
				color:black;
				text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15); 
			}
			hr{
				width:100%;
				height:10px;
				margin-bottom:20px;
				border:none;
				border-top:10px groove skyblue;"
			}
			textarea{
				font-size:15px;
				background-color:#EEE8AA;
				border:none;
				rows:2;
				width:98%;
				resize: none; 
				maxlength:100; 
				warp:virtual;
			}
			a{
				color:orange;
				text-decoration:none;
			}
			a:hover{
				border-bottom: 2px solid orange;
			}
			input:hover{
 				background:linear-gradient(#F4A460,#F5DEB3); 
 				color: black; 
			}
			input:hover,textarea:hover,input:focus,textarea:focus { border-color: black; }
			select{
				width:131px;height:30px;padding:0 16%;
			}
			option{
				text-align:center;
				background-color: #F5DEB3;
			}
		</style>
	</head>
	<body>
		<!-- 下面是填写开店申请  -->
		<div align="center" id="foot">
		<c:choose>
			<c:when test="${not empty message }">
				<c:if test="${message==1 }">
					<div id="tips">开店申请已提交，运营商审核中，请耐心等待</div>
				</c:if>
				<c:if test="${message==2 }">
					<div id="tips">已经提交过申请或已经成功通过申请了，请前往店铺看看！</div>
				</c:if>
			</c:when>
			<c:otherwise>
			<h2>美团商家开店申请</h2>
			<hr>
			<form action="${pageContext.request.contextPath }/ShopServlet?method=apply" enctype="multipart/form-data" method="post">
				<table width="200px" height="300px" >
					<tr>
						<td style="text-align:center;">店&nbsp;&nbsp;名：</td>
						<td><input type="text" name="shop_name" maxlength="15" style="width:131px;height:25px"/></td>
					</tr>
					<tr>
						<td style="text-align:center;">所属菜系：</td>
						<td>
	                        <select name="style_id">
		                        <c:forEach items="${requestScope.SHOPTYPE}" var="type">
		  							<option value="${type.style_id}">${type.style_name }</option>
		  						</c:forEach>	   					
                        </select>
	                    </td>
					</tr>
					<tr>
						<td style="text-align:center;">联系电话：</td>
						<td><input type="text" name="shop_ctc" maxlength="12" style="width:131px;height:25px"/></td>
					</tr>
					<tr>
						<td style="text-align:center;">地&nbsp;&nbsp;址：</td>
						<td><textarea name="shop_add"></textarea></td>
					</tr>
					<tr>
						<td style="text-align:center;">店铺图片：</td>
						<td><input type="file" name="shop_pic" /></td>
					</tr>
					<tr>
						<td style="text-align:center;">店铺介绍：</td>
						<td><textarea name="shop_desc"></textarea></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="同意以下协议并提交申请"
							style="background-color:orange;width:40%;height:30px;border-radius: 8px;" />
						</td>
					</tr>
					
				</table>
			</form>
			<a href="#">《美团网开店协议》</a>
			</c:otherwise>
		</c:choose>
		</div>
		
	</body>
</html>