<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Frame top</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
		body {
			margin: 0;
		}
		#Head_1 {
			background: linear-gradient(black,#666666);
		    height: 64px;
		    margin: 0 auto;
		    width: 100%;
		}
		#Head_1_Logo {
			float: left;
		    left: 20px;
		    position: absolute;
		    top: 12px;
			color: white;
		    font-family: Arial Black,Arial;
		    font-size: 38px;
			text-shadow: 0 1px 0 #ccc,0 2px 0 #c9c9c9,0 3px 0 #bbb,0 4px 0 #b9b9b9,0 5px 0 #aaa,0 6px 1px rgba(0,0,0,.1),0 0 5px rgba(0,0,0,.1),0 1px 3px rgba(0,0,0,.3),0 3px 5px rgba(0,0,0,.2),0 5px 10px rgba(0,0,0,.25),0 10px 10px rgba(0,0,0,.2),0 20px 20px rgba(0,0,0,.15); 
		}
		#Head_1_UserWelcome {
		    float: right;
		    color: #B3E1FF;
		    font-family: "宋体";
		    font-size: 12px;
		    height: 25px;
			padding-top: 11px;
			margin-right: 15px;text-align:center;
		}
		#Head_1_Time {
		    float: right;
		    height: 25px;
			padding-top: 11px;
			margin-right: 20px;
		}
		#Head_1_Time input{
			background-color: transparent;
		    font-family: "黑体";
		    font-size: 16px;
		    color:white;
		}
		#Head_2 {
			background-color:#F0C17D;
		    height: 36px;
		    margin: 0;
		    width: 100%;
		}
		#back_forward{
			float:right;
			margin-top:7px;
			margin-right:10px;
		}
		#Head_1_UserWelcome a:link{
			background-color: transparent; 
 			color: white; 
		}
		#Head_1_UserWelcome a:hover{
 			background-color: #F0C17D; 
 			color: #262626; 
		}
		a {
			height: 36px; 
			padding:7px;
			text-decoration: none;
		}
		a:link{
 			background-color: #F0C17D; 
 			color: black; 
		}
		a:hover{
 			background-color: #FFE177; 
 			color: black; 
		}
	</style>
	<script type="text/javascript">
		function exitEnsure(){
			if(confirm("确认要退出登录吗")){
				window.parent.location.href="${pageContext.request.contextPath }/shopSys/exit.jsp";
			}else{
			
			}
		}
	</script>
</head>

<body>
 	
	<!-- 上部 -->
	<div id="Head_1">
		<!-- 标题 -->
		<div id="Head_1_Logo">
			<b style="font-family: '黑体';">美团商家中心</b> 
        </div>
		<!-- 欢迎用户的文字 -->
		<div id="Head_1_UserWelcome">
			<img border="0" width="13" height="14" style="vertical-align:middle" src="${pageContext.request.contextPath }/shopSys/style/images/user.gif" /> 
			<% 
			Object uname=null;
			if((uname=session.getAttribute("SHOP_ACCOUNT"))!=null){
				uname="<a  href='javascript:void(0)' style='text-decoration:none;font-family: 仿宋;font-size: 15px;'><b>欢迎，"+uname+"</b></a>|"
					+"<a href='javaScript:void(0)' onclick='exitEnsure()' style='text-decoration:none;'><b>退出</b></a>";
			}else{
				uname = "未登录！无权操作";
			}
			%>
			<%=uname %><p>
		</div>
		
	</div>
	<!-- 下部 -->
    <div id="Head_2">
		
		<div id="back_forward" >
			<a href="javascript: window.parent.right.history.forward();" >
				<b>前&nbsp;进</b>		
			</a>
			<a href="javascript: window.parent.right.history.back();" >
				<b>后&nbsp;退</b>
			</a>
        </div>
        
	</div>
</body>
</html>