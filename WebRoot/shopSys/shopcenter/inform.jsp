<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>通知中心</title>
		<style type="text/css">
			body{
				background-color:#EEE8AA;
			}
			#foot{
				margin:auto;
				margin-top:15px;
				width:700px;
			}
			#tips{
				color:black;
				margin-top:20%;
				font-size:25px;
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
			table{
				margin:auto;
				width:200px;
				border:3px solid orange;
				border-radius: 8px;
				color:#262626;
				box-shadow:0px 0px 8px 4px #aaa;
			}
			td{
				font-size:25px;
				font-weight:bold;
				height:70px;
				text-align:center;
				border:3px solid orange;
			}
		</style>
		<script type="text/javascript">
			
		</script>
		
	</head>
	<body>
		<!-- 下面是注册页面  -->
		<div align="center" id="foot">
		<c:choose>
			<c:when test="${INFORM == 1 }">
				<h2>开店申请审核状况</h2>
				<hr>
				<table>
					<tr>
						<td style="height:30px">
							<c:choose>
								<c:when test="${requestScope.SHOPACCOUNT.shopacc_state == 0 }">
								未通过
								</c:when>
								<c:otherwise>
								已通过
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<div id="tips">还未申请开店，没有通知信息。</div>
			</c:otherwise>
		</c:choose>
		</div>
		
	</body>
</html>