<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Frame left</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<style type="text/css">
		html {
			height: 100%;
		}
		body {
			background: linear-gradient(#ee6363,#cc0066);
			margin: 0;
			padding: 0;
		}
		div {
			padding-top:14px;
			font-family:"黑体";
			font-size:22px;
			color:white;
			text-shadow:1px 1px 1px #000; 
		}
		ul {
		    list-style-type: none;
		    margin: 0;
		    padding: 0;
		    width: 176px;
		}
		li a {
			margin-left:auto;
		    display: block;
		    color: #000;
		    padding: 8px 16px;
		    text-decoration: none;
		    text-align:center;
		    font-family:"黑体";
		}
		li a:hover {
		    background-color: #555;
		    color: white;
		}
		img {
			margin-left:15px;
		}
	</style>
</head>
<body>
	<ul id="Menu">
	    <li>
            <div>
				<img src="${pageContext.request.contextPath }/adminSys/style/images/func20001.gif" /> 
				<b>功能选择</b>
			</div>
			<hr style="border:3 double yellow" width="100%" color=yellow SIZE=3>
            <ul>
            	<li>
                    <a target="right" href="${pageContext.request.contextPath}/AdminServlet?method=getShop">查看商家</a>
				</li>
                <li>
                	<a target="right" href="${pageContext.request.contextPath}/AdminServlet?method=getUser">查看用户</a>
				</li>
				<li>
                	<a target="right" href="${pageContext.request.contextPath}/AdminServlet?method=getShopinfo">查看店铺</a>
				</li>
				<li>
                	<a target="right" href="${pageContext.request.contextPath}/AdminServlet?method=getShopApply">查看申请</a>
				</li>
            </ul>
        </li>
    </ul>
</body>
</html>