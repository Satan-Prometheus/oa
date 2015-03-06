<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cover Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="http://cdn.bootcss.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="cover.css" rel="stylesheet">

</head>

<body>

<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">


            <div class="inner cover">
                <form action="/a/login/" method="post" class="form-horizontal">
                    <div class="form-group"> <!--style="width:300px; margin: 0px 0px 20px 150px"-->
                        <label class="col-sm-3 control-label sr-only" for="userId">账号</label>

                        <div class="input-group col-sm-5">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <input id="userId" type="text" name="userId" class="form-control" placeholder="请输入账号"
                                   aria-describedby="basic-addon1">
                        </div>
                    </div>


                    <div class="form-group"> <!-- style="width:300px; margin: 0px 0px 20px 150px"-->
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


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</body>
</html>
