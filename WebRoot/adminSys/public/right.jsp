<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<style>
		body {
    		background-color:#808080;
    	}
        h1 {
        	text-align:center;
        	margin-top:15%;
        	font-size:50px;
            
            background-image: -webkit-linear-gradient(left,blue,#66ffff 10%,#cc00ff 20%,#CC00CC 30%, #CCCCFF 40%, #00FFFF 50%,#CCCCFF 60%,#CC00CC 70%,#CC00FF 80%,#66FFFF 90%,blue 100%);
            -webkit-text-fill-color: transparent;/* 将字体设置成透明色 */
            -webkit-background-clip: text;/* 裁剪背景图，使文字作为裁剪区域向外裁剪 */
            -webkit-background-size: 200% 100%; 
            -webkit-animation: masked-animation 10s linear infinite;
        }
        @keyframes masked-animation {
            0% {
                background-position: 0  0;
            }
            100% {
                background-position: -100%  0;
            }
        }
        form {
        	float:right;
        }
 	</style>
</head>

	<body>
		<h1><b>欢迎来到后台管理中心！</b></h1>
	</body>
</html>