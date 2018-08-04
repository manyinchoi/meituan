<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>商家中心--导航栏</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<style type="text/css">
		html {
			height: 100%;
		}
		body {
			background:linear-gradient(black,#696969);
			margin: 0;
			padding: 0;
		}
		div {
			padding-top:14px;
			font-family:"黑体";
			font-size:22px;
			color:white;
			text-shadow:2px 2px 2px #000; 
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
		    color: white;
		    padding: 8px 16px;
		    text-decoration: none;
		    text-align:center;
		    font-family:"黑体";
		}
		li a:hover {
		    background-color: #FFE177;
		    color: black;
		}
		img {
			margin-left:15px;
		}
	</style>
	<script type="text/javascript">
		function goto(){
			window.parent.location.href="${pageContext.request.contextPath}/shopSys/shop/frame.jsp";
		}
	</script>
</head>
<body>
	<ul id="Menu">
	    <li>
            <div>
				<img src="${pageContext.request.contextPath }/shopSys/style/images/func20001.gif" class="Icon" /> 
				功能选择
			</div>
			<hr style="border:3 double yellow" width="100%" color=#F0C17D SIZE=3>
            <ul>
            	<li>
                    <a target="right" href="${pageContext.request.contextPath }/ShopServlet?method=getShopType">申请开店</a>
				</li>
                <li>
                	<c:choose>
                		<c:when test="${not empty sessionScope.SHOP_ACCOUNT }">
		                	<a href="javascript:void(0)" onclick="goto()">进入店铺</a>
                		</c:when>
                		<c:otherwise>
                			进入店铺
                		</c:otherwise>
                	</c:choose>
				</li>
                <li>
                	<a target="right" href="${pageContext.request.contextPath }/ShopAccountServlet?method=getShopAccMsg">管理账号</a>
				</li>
                <li>
                	<a target="right" href="${pageContext.request.contextPath }/ShopServlet?method=getInform">通知中心</a>
				</li>
            </ul>
        </li>
    </ul>
</body>
</html>