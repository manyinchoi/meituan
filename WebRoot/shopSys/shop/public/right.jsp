<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
	body {
    	background-color: #EEE8AA;
    }
</style>
</head>
	<body>
		<center>
			<h1 id="HotNews" style="color:black;font-size:50px;margin-top:15%;"></h1>
			<script language="JavaScript">
				var NewsTime = 2000;	//每条新闻的停留时间
				var TextTime = 200;		//新闻标题文字出现等待时间，越小越快
	
				var newsi = 0;
				var txti = 0;
				var txttimer;
				var newstimer;
	
				var title = new Array();	//标题
	
				title[0] = "欢迎使用美团商家后台管理！";
				
				function shownew()
				{
					var endstr = "_"
					hwnewstr = title[newsi];
					if(txti==(hwnewstr.length-1)){endstr="";}
					if(txti>=hwnewstr.length){
						clearInterval(txttimer);
						clearInterval(newstimer);
						newsi++;
						if(newsi>=title.length){
							newsi = 0
						}
						newstimer = setInterval("shownew()",NewsTime);
						txti = 0;
						return;
					}
					clearInterval(txttimer);
					
					document.getElementById("HotNews").innerHTML = hwnewstr.substring(0,txti+1)+endstr;
					txti++;
					txttimer = setInterval("shownew()",TextTime);
				}
				shownew();
			</script>
		</center>
	</body>
</html>