<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商家账号信息</title>
		<style type="text/css">
			body {
    			background-color:#EEE8AA;
    		}
			#foot{
				margin:auto;
				margin-top:15px;
				width:800px;
			}
			#button{
				text-decoration:none;
				color:black;
				background-color:orange;
				-moz-box-shadow:0 0 5px 5px orange;
    		  	-webkit-box-shadow:0 0 5px 5px orange;
    		  	box-shadow:0 0 5px 5px orange;
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
				height:70px;
				
			}
			td input{
				font-size:20px;
				border:3px solid orange;
				border-radius: 8px;
				width:70%;
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
			a:hover{
				background:linear-gradient(#F4A460,#F5DEB3); 
			}
			input:hover{
 				background:linear-gradient(#F4A460,#F5DEB3); 
 				color: black; 
			}
			input:hover,textarea:hover,input:focus,textarea:focus { border-color: black; }
			
		</style>
		
		<script type="text/javascript">
			function editEnsure(){
				var acc=document.getElementById("shopacc_acc").value;
				var id=document.getElementById("shopacc_id").value;
				var pwd=document.getElementById("shopacc_pwd").value;
				if(confirm("修改保存后，自动退出当前账号")){
					// 先获取当前表单对象
					//var frm = document.forms[0];
					//frm.action="${pageContext.request.contextPath }/ShopAccountServlet?method=updateShopAcc";
					//frm.submit();
					//但是用下面这种方法传参，又会会出现乱码的，怎么办呢？
					window.parent.location.href="${pageContext.request.contextPath }/ShopAccountServlet?method=updateShopAcc&shopacc_acc="+encodeURI(encodeURI(acc))+"&shopacc_id="+id+"&shopacc_pwd="+encodeURI(encodeURI(pwd));
			}
		}
		</script>
	</head>
	<body>
		
		<div align="center" id="foot">
		<h2>账号信息修改</h2>
		<hr>
		<form  method="post">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>账号：</td>
					<td>
						<input type="text" id="shopacc_acc" value="${requestScope.SHOPACCOUNT_MSG.shopacc_acc}"/> 
						<input type="hidden" id="shopacc_id" value="${requestScope.SHOPACCOUNT_MSG.shopacc_id}"/>
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" id="shopacc_pwd" value="${requestScope.SHOPACCOUNT_MSG.shopacc_pwd}"/> </td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="javascript:editEnsure()" id="button" style="text-decoration:none;margin-left:40%">修改并保存</a>
					</td>
				</tr>
			</table>
		</form>
		</div>
		
	</body>
</html>