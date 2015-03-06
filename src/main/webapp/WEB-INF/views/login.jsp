<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cover Template for Bootstrap</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/cover.css" rel="stylesheet">
</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">


            <div class="inner cover">
                <form action="/a/login/do" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-3 control-label sr-only" for="userId">账号</label>

                        <div class="input-group col-sm-5">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <input id="userId" type="text" name="userId" class="form-control" placeholder="请输入账号"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-3 control-label sr-only" for="password">密码</label>

                        <div class="input-group col-sm-5">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-eye-close"></span></span>
                            <input id="password" type="text" name="password" class="form-control" placeholder="请输入密码"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-5">
                            <button type="submit" class="btn btn-primary btn-block">登录</button>
                        </div>
                    </div>
                </form>
            </div>

            <div class="clearfix" style="margin-bottom: 10px;"></div>
            <div class="mastfoot">
                <div class="inner">
                    <p>login page for hxoa, by xh.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                </div>
            </div>

        </div>

    </div>

</div>


<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>






