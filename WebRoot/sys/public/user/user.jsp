<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户信息</title>
		<link href="${pageContext.request.contextPath }/shopSys/style/css/register.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			#top{
				width:100%;
				height:50px;
			}
			#foot{
				margin:auto;
				margin-top:25px;
				width:500px;
			}
			table{
				margin:auto;
				width:100%;
				border:1px solid black;
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
			style="width:100px;height:100%;" />
			<hr color="orange">
		</div>
		
		<!-- 下面是注册页面 -->
		<div align="center" id="foot">
		<br><br>
		<form action="${pageContext.request.contextPath }/UserServlet?method=updateUser" method="post">
			<table width="200px" height="430px" >
				<th colspan="3"><h2>用 户 信 息</h2></th>
				<tr>
					<td>用户名：</td>
					<td>
						<input type="text" maxlength="10" style="width:131px;height:25px;margin-left:-80px;"
							name="username" value="${requestScope.USER_MSG.user_name }" />
					</td>
					<td><span id="nameID"></span></td>
				</tr>
				<tr>
					<td>手机号：</td>
					<td>
						<input type="text" maxlength="15" style="width:131px;height:25px;margin-left:-80px;"
							name="phone" value="${requestScope.USER_MSG.user_pho }"
						/>
					</td>
					<td><span id="phoneID"></span></td>
				</tr>
				<tr>
					<td>性&nbsp;别：</td>
					<td>
						<input style="width:25pt;height:20pt;margin-left:-80px;" type="radio" name="sex" id="sex" value="男" <c:if test="${requestScope.USER_MSG.user_sex=='男'}">checked</c:if> />男
						<input style="width:25pt;height:20pt;" type="radio" name="sex" id="sex" value="女" <c:if test="${requestScope.USER_MSG.user_sex=='女'}">checked</c:if> />女
					</td>
					<td><span id="gender"></span></td>
				</tr>
				<tr>
					<td>密&nbsp;码：</td>
					<td>
						<input type="text" maxlength="15" style="width:131px;height:25px;margin-left:-80px;"
							name="password" value="${requestScope.USER_MSG.user_pwd }"
						/>
					</td>
					<td><span id="passwordID"></span></td>
				</tr>
				<tr>
					<td>地&nbsp;址：</td>
					<td colspan="2">
						<input type="text" maxlength="50" style="width:230px;height:30px;margin-left:-115px;"
							name="address" value="${requestScope.USER_MSG.user_add }" 
						/>
					</td>
				</tr>
				<tr><td><input type="hidden" name="id" value="${requestScope.USER_MSG.user_id }"/></td></tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="保 存" style="background-color:orange;width:30%;height:90%;font-size:25px" />
						<a href="javascript:history.go(-1);" style="text-decoration:none;">返回</a>
					</td>
				</tr>
				
			</table>
		</form>
		</div>
		
	</body>
</html>