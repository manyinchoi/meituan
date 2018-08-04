<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>查看申请分页查询</title>
	<meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3,keyword4,keyword5,keyword6,keyword7,keyword8">
    <style type="text/css">
    	body {
    		background-color:#808080;
    	}
    	#sheet {
    		background-color: #fff; border:5px solid #ccc;
    	}
    	#hr1{
			width:100%;
			height:10px;
			border:none;
			border-top:5px groove yellow;
		}
    	a:link {color: blue; text-decoration:none;}
    	a:hover {color: red; text-decoration:underline;}
    	a:active:{color: red; }
    </style>
    <script language="javaScript">
		function pass(shopaccid){
			if(window.confirm("您确定要通过该申请吗？")){
				window.location.href="${pageContext.request.contextPath}/AdminServlet?method=pass&shopaccid="+shopaccid;
			}
		}
		function notpass(shopaccid){
			if(window.confirm("您确定要拒绝该申请吗？")){
				window.location.href="${pageContext.request.contextPath}/AdminServlet?method=notpass&shopaccid="+shopaccid;
			}
		}
	</script>
  </head>

  <body>
  	<h1 style="text-align:center;font-size:32px;color:white;text-shadow:1px 1px 1px orange">申 请 列 表</h1>
	<hr id="hr1">
    <table id="sheet" border="1" align="center" cellpadding="5" cellspacing="0" width="80%">
        <tr>
        	<th align="center">序号</th>
        	<th align="center">所属商家ID</th>
            <th align="center">店铺名称</th>
            <th align="center">店铺地址</th>
            <th align="center">联系方式</th>
            <th align="center">描述</th>
            <th align="center">操作</th>
        </tr>
        <c:choose>
            <c:when test="${not empty requestScope.pageBean.pageData }">
                <c:forEach var="ap" items="${requestScope.pageBean.pageData }" varStatus="status">
                    <tr>
                        <td align="center">${status.count }</td>
                        <td align="center">${ap.shopacc_id }</td>
                        <td align="center">${ap.shop_name }</td>
                        <td align="center">${ap.shop_add }</td>
                        <td align="center">${ap.shop_ctc }</td>
                        <td align="center">${ap.shop_desc }</td>
                        <td align="center">
							<input type="button" name="通过" value="通 过" style="border-radius: 5px"
					   				onClick="pass('<c:out value="${ap.shopacc_id}"/>')">
					   		<input type="button" name="拒绝" value="拒 绝" style="border-radius: 5px"
					   				onClick="notpass('<c:out value="${ap.shopacc_id}"/>')"></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7" align="center">暂时没有开店申请，请耐心等候！</td>
                </tr>
            </c:otherwise>
        </c:choose>
        <tr>
            <td colspan="7" align="center">
            <span>当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${requestScope.pageBean.currentPage != 1 }">
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getShopApply&currentPage=1">首页</a>　
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getShopApply&currentPage=${requestScope.pageBean.currentPage-1}">上一页</a>
            </c:if>
            <c:if test="${requestScope.pageBean.currentPage != requestScope.pageBean.totalPage }">
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getShopApply&currentPage=${requestScope.pageBean.currentPage+1}">下一页</a>　
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getShopApply&currentPage=${requestScope.pageBean.totalPage}">末页</a>
            </c:if>　
            </td>
        </tr>
    </table>
  </body>
</html>