<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>欢迎来到美团线上点餐平台</title>
		<link href="${pageContext.request.contextPath }/sys/style/css/index.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			#foot{
				margin:auto;
				margin-top:15px;
				width:400px;
			}
			h2{
				text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15); 
			}
			table{
				position:absolute;
				left:455px;
				top:35px;
				width:400px;
				border-spacing:20px;
				background: transparent;
			}
			td{
				text-align:center;
				font-size:30px;
				width:50px;
				border:3px solid orange;
				box-shadow:0px 0px 8px 4px #aaa;
			}
			a{
				text-decoration:none;
				color: black;
			}
			td:hover{
				text-decoration:none;
				border-radius: 20px;
				border:4px solid yellow;
				font-size:32px;
				background:rgba(2,2,2,0.2);
			}
		</style>
	</head>
	
	<body>
		<svg id="ckLine" xmlns="http://www.w3.org/2000/svg" width="1000" height="1000"></svg>
		<script src="sys/style/js/jquery-1.11.0.min.js" type="text/javascript"></script>
		<script src="sys/style/js/jquery.ckLine.js"></script>
		<script type="text/javascript">
			$('#ckLine').ckLine({
				leftRight: false,
				strokeColor: 'rgba(255,255,255,0.4)',
				interval: 800,
				animateTime: 1600,
				animationTimeRange: [800, 1600]
			});
		</script>
		
		<h1 style="z-index:9999;font-size:35px;position:absolute;color:white;left:10px;top:-45px;">
		<!-- 上部分是一张美团图片 -->
			<p>
			<img alt="sorry" src="${pageContext.request.contextPath }/sys/style/images/register.png"
			style="width:120px;height:50px;margin-top:10px;margin-left:10px;border-radius:5px" /></p>
		</h1>
		<h2 style="z-index:900;font-size:50px;position:absolute;color:white;left:27%;top:6%; ">欢迎访问美团线上点餐平台</h2>
		
		<!-- 下面是注册页面 -->
		<h1 style="z-index:9999;font-size:35px;position:absolute;color:white;left:10px;top:150px;">
		
			<table>
				<tr style="height:180px; border-spacing:100px">
					<td><a href="sys/public/log2register/login.jsp">登&nbsp;&nbsp;录</a></td>
					<td><a href="sys/public/log2register/register.jsp">注&nbsp;&nbsp;册</a></td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="${pageContext.request.contextPath }/UserServlet">
							先&nbsp;去&nbsp;逛&nbsp;逛
						</a>
					</td>
				</tr>
			</table>
		
		</h1>
	</body>
</html>