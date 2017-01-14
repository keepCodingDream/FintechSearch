<%--
  Created by IntelliJ IDEA.
  User: lurenjie
  Date: 2017/1/13
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>搜索结果</title>
    <script src="${pageContext.request.contextPath}/js/jquery.mobile.custom.min.js"></script>
</head>
<body>

<div class="content">
    <ul>
        <c:forEach var="result" items="${response.result}">
            <li>
                <div class="title">
                    <h2><a href="${result.url}">${result.title}</a></h2>
                </div>
                <div class="content">
                    <h3>${result.content}</h3>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
