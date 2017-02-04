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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8"/>
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta name="renderer" content="webkit">
    <meta property="qc:admins" content="340151745401645346313025363757"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="viewport"
          content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui, width=device-width"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="yes"/>
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/libs/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/fintech.css"/>
    <script src="${pageContext.request.contextPath}/libs/mod.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.mobile.custom.min.js"></script>
</head>
<body>
<script src="${pageContext.request.contextPath}/libs/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/bootstrap-3.3.0-dist/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/libs/angular/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/angular-route/angular-route.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/angular-resource/angular-resource.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/angular-touch/angular-touch.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/angular-animate/angular-animate.min.js"></script>
<script src="${pageContext.request.contextPath}/libs/angular-sanitize/angular-sanitize.min.js"></script>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">FinTech Search</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search" action="/search/list">
                <div class="form-group">
                    <input type="text" name="value" class="form-control search-input" placeholder="Search"
                           maxlength=100>
                </div>
                <button type="submit" class="btn btn-success">Search</button>
            </form>
        </div>
    </div>
</nav>
<div class="content">
    <c:choose>
        <c:when test="${response.totalElements==0}">
            <div>
                抱歉，找不到相关结果
            </div>
        </c:when>
        <c:otherwise>
            <c:forEach var="result" items="${response.result}">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            <a href="${result.url}">${result.title}</a>
                        </h3>
                    </div>
                    <div class="panel-body">
                            ${result.content}
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<div class="foot" style="text-align: center">
    <ul class="pagination" style="text-align: center">
        <c:forEach var="next" items="${response.next}">
            <c:choose>
                <c:when test="${next.current=='1'}">
                    <li class="active"><a href="${next.url}">${next.index}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${next.url}">${next.index}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </ul>
</div>
</body>
</html>
