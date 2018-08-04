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
			background-color:#cc0066;
		    height: 36px;
		    margin: 0;
		    width: 100%;
		}
		#back_forward{
			float:right;
			margin-top:7px;
			margin-right:10px;
		}
		a {
			height: 36px; 
			padding:7px;
			text-decoration: none;
		}
		a:link{
 			background-color: #cc0066; 
 			color: #ffff00; 
		}
		a:hover{
 			background-color: #ee6363; 
 			color: #ffff00; 
		}
	</style>
	<script>
		function showTheTime(){
 			now = new Date();
 			var weekday=new Array(7)
 			weekday[0]="星期日"
 			weekday[1]="星期一"
 			weekday[2]="星期二"
 			weekday[3]="星期三"
 			weekday[4]="星期四"
 			weekday[5]="星期五"
 			weekday[6]="星期六"
 			form.showTime.value = now.getFullYear() + "年"+ (now.getMonth()+1) + "月" + now.getDate() + "日 "
     							+ weekday[now.getDay()] + " "+ now.getHours() + ":"+ now.getMinutes() + ":" + now.getSeconds();
 			setTimeout("showTheTime()",1000);
		}
		function exit(){
			if(window.confirm('您确定要退出登录吗？')){
				window.parent.location.href="logout.jsp";
			}
			
		}
	</script>
</head>

<body  onload="showTheTime()">
 	
	<!-- 上部 -->
	<div id="Head_1">
	
		<!-- 标题 -->
		<div id="Head_1_Logo">
			<b style="font-family:'黑体'">美团账号后台管理&nbsp;</b> 
        </div>
		<!-- 欢迎用户的文字 -->
		<div id="Head_1_UserWelcome">
			<img border="0" width="13" height="14" src="${pageContext.request.contextPath }/adminSys/style/images/user.gif" /> 
			您好，<b>管理员</b>&nbsp;
			<a href="javascript:exit()" style="font-color:#cc0066;-moz-box-shadow:0 0 5px 5px #cc0066;
    		  -webkit-box-shadow:0 0 5px 5px #cc0066;
    		  box-shadow:0 0 5px 5px #cc0066;">
				<b>退 出</b>
			</a>
		</div>
		<div  id="Head_1_Time">
			<form name="form" >
				<input name="showTime" size="30" style="border: 0px;">
			</form>
		</div>
	</div>
	
	<!-- 下部 -->
    <div id="Head_2">
		
		<div id="back_forward" >
			<a href="javascript: window.parent.right.history.forward();" >
				前&nbsp;进		
			</a>
			
			<a href="javascript: window.parent.right.history.back();" >
				后&nbsp;退
			</a>
        </div>
        
	</div>
</body>
</html>