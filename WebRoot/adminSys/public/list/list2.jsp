<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户账户信息分页查询</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3,keyword4,keyword5,keyword6,keyword7">
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
	function del(userid){
		if(window.confirm("您确定要删除该用户吗？")){
			window.location.href="${pageContext.request.contextPath}/AdminServlet?method=deleteUser&userid="+userid;
		}
	}
	</script>
  </head>

  <body>
  	<h1 style="text-align:center;font-size:32px;color:white;text-shadow:1px 1px 1px orange">用 户 列 表</h1>
	<hr id="hr1">
    <table id="sheet" border="1" align="center" cellpadding="5" cellspacing="0" width="80%">
        <tr>
        	<th align="center">序号</th>
        	<th align="center">账号ID</th>
            <th align="center">用户名</th>
            <th align="center">用户密码</th>
            <th align="center">联系方式</th>
            <th align="center">性别</th>
            <th align="center">收货地址</th>
            <th align="center">管理账号</th>
        </tr>
        <c:choose>
            <c:when test="${not empty requestScope.pageBean.pageData }">
                <c:forEach var="user" items="${requestScope.pageBean.pageData }" varStatus="status">
                    <tr>
                    	<td align="center">${status.count }</td>
                        <td align="center">${user.user_id }</td>
                        <td align="center">${user.user_name }</td>
                        <td align="center">${user.user_pwd }</td>
                        <td align="center">${user.user_pho }</td>
                        <td align="center">${user.user_sex }</td>
                        <td align="center">${user.user_add }</td>
                        <td align="center">
							<input type="button" name="删除" value="删 除" style="border-radius: 5px"
					   				onClick="del('<c:out value="${user.user_id}"/>')"></td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="8" align="center">对不起，查询的数据不存在。</td>
                </tr>
            </c:otherwise>
        </c:choose>
        <tr>
            <td colspan="8" align="center">
            <span>当前${requestScope.pageBean.currentPage }/${requestScope.pageBean.totalPage }页</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${requestScope.pageBean.currentPage != 1 }">
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getUser&currentPage=1">首页</a>　
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getUser&currentPage=${requestScope.pageBean.currentPage-1}">上一页</a>
            </c:if>
            <c:if test="${requestScope.pageBean.currentPage != requestScope.pageBean.totalPage }">
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getUser&currentPage=${requestScope.pageBean.currentPage+1}">下一页</a>　
                <a href="${pageContext.request.contextPath }/AdminServlet?method=getUser&currentPage=${requestScope.pageBean.totalPage}">末页</a>
            </c:if>　
            </td>
        </tr>
    </table>
  </body>
</html>