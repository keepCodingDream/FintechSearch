<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head><title>finTeck </title>
    <meta http-equiv=Content-Type content="text/html; charset=UTF-8">
    <style>body {
        font-family: 宋体
    }
    img {
        border: 0
    }
    td, p {
        font-size: 12px
    }
    p {
        width: 600px;
        margin: 0;
        padding: 0
    }
    .ff {
        font-family: Verdana;
        font-size: 16px
    }
    #n {
        margin: 0px auto;
        font-size: 12px;
        padding: 0px;
        border-bottom: 1px solid #00c;
        BACKGROUND: #eee;
        width: 600px;
        height: 18px
    }

    #n li {
        float: left;
        display: block;
        margin: 0px;
        padding: 0px;
        width: 67px
    }

    #n li a {
        display: block;
        text-decoration: none;
        padding: 4px 0px 0px 0px;
        margin: 0px;
        width: 100%
    }

    #n li a:hover {
        text-decoration: underline;
        background: #FFF;
        padding: 4px 0px 0px 0px;
        margin: 0px
    }

    #n li#h {
        width: 56px;
        height: 18px
    }

    #n li#m {
        width: 85px;
        height: 18px
    }

    #n .w {
        background: #00C;
        color: #FFF;
        padding: 4px 0px 0px 0px;
        margin: 0px;
        font-weight: bold
    }

    #b {
        width: 600px;
        height: 30px;
        padding-top: 4px;
        color: #77c;
        font-size: 12px;
        font-family: Arial
    }

    #b a {
        color: #77c;
        font-size: 12px
    }</style>
    <script>
        function h(obj, url) {
            obj.style.behavior = 'url(#default#homepage)';
            obj.setHomePage(url);
        }</script>
</head>
<body bgcolor=ffffff text=000000 link=0000cc vlink=0000cc alink=ff6600 onload=document.f.wd.focus()>
<center><br><img src=/img/logo.jpg width=570 height=238 alt="百度"><br><br><br><br>
    <p style=height:44px;></p>
    <table width=600 border=0 cellpadding=0 cellspacing=0>
        <tr valign=top>
            <td width=92></td>
            <td height=62>
                <form name=f action=/search/list>
                    <input type=text name=value class=ff size=35 maxlength=100>
                    <input type=submit value=搜索><br><br></form>
            </td>
        </tr>
    </table>
</center>
</body>
</html>