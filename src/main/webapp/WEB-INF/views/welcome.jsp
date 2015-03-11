<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href='/css/common.css' rel="stylesheet">
    <link href="/css/dashboard.css" rel="stylesheet">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">HXOA</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a>
                    <div>${user.name}, 你好，上次登录时间:<fmt:formatDate value="${user.lastLoginTime}" type="both"
                                                                 pattern="yyyy-MM-dd hh:mm:ss"/></div>
                </a></li>

                <li><a href="#">Exit</a></li>
            </ul>

        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul id='nav-sidebar' class="nav nav-sidebar">
                <li id="li-approve" class="active"><a data="/a/index/approve">待我审批</a></li>
                <li id="li-create"><a data="/a/request/create/pg">新建申请</a></li>
                <li id="li-mylist"><a data="/a/request/my/list/ing">我的申请</a></li>
            </ul>
        </div>
        <div id="div-main" class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        </div>
    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->

<script src="/js/jquery.min.js"></script>
<script>
    $("document").ready(function () {
        $("#nav-sidebar").find("li").click(function () {
            $(this).parent().find("li").removeClass('active')
            $(this).addClass('active')
            $('#div-main').load($(this).find('a').attr('data'));
        })

        $("#nav-sidebar").find("li.active").click()
    })
</script>

</body>
</html>
