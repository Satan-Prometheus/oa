<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery.min.js">
</script>
<script>
    $(document).ready(function () {
        $(this).find("[name='agree']").click(function () {

            var r = confirm("是否确定同意?");
            if (r == false) {
                return;
            }
            var post_ret = 3;
            $.post($(this).attr("data"), //"http://localhost:8080/errcode1.jsp",
                    null,
                    function (data, status) {
                        //alert("数据：" + data + "\n状态：" + status);
                        //var obj = $.parseJSON(data);
                        var obj = data;
                        if (obj.errCode == 0) {
                            //alert("errCode 0")
                            post_ret = 0;
                        } else {
                            alert("出现错误：" + obj.errMsg)
                            post_ret = 1;
                            location.reload()
                        }
                    },
                    'json');
            /*
             if (post_ret == 3) {
             alert("网络错误")
             return;
             }*/
            $(this).parent().find("[name='reject']").hide();
            $(this).hide()

            $(this).parent().find("[name='agreeed']").show();
        });

        $(this).find("[name='reject']").click(function () {

            var r = confirm("是否确定拒绝?");
            if (r == false) {
                return;
            }
            var post_ret = 3;
            $.post($(this).attr("data"), //"http://localhost:8080/errcode1.jsp",
                    null,
                    function (data, status) {
                        //alert("数据：" + data + "\n状态：" + status);
                        //var obj = $.parseJSON(data);
                        var obj = data;
                        if (obj.errCode == 0) {
                            //alert("errCode 0")
                            post_ret = 0;
                        } else {
                            alert("出现错误：" + obj.errMsg)
                            post_ret = 1;
                            location.reload()
                        }
                    },
                    'json');
            /*
             if (post_ret == 3) {
             alert("网络错误")
             return;
             }*/
            $(this).parent().find("[name='agree']").hide();
            $(this).hide()

            $(this).parent().find("[name='rejected']").show();
        });
    });
</script>
<div>
    <h2 class="sub-header">请假审批</h2>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>请假人姓名</th>
                <th>部门</th>
                <th>请假类型</th>
                <th>详情</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}" varStatus="status">
                <tr>
                    <td>${item.userName}</td>
                    <td>${item.department}</td>
                    <td>${item.requestType}</td>
                    <td>${item.requestDetail}</td>
                    <td>
                        <button data="/a/request/approve/${item.id}/${item.stepOrder}/2" name="agree">同意</button>
                        <button data="/a/request/approve/${item.id}/${item.stepOrder}/1" name="reject">拒绝</button>
                        <span name="agreeed" style="display:none">已同意</span><span name="rejected" style="display:none">已拒绝</span>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>