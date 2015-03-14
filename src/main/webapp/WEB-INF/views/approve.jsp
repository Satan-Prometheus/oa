<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="/js/jquery.min.js"></script>

<script src="/js/bootstrap.min.js"></script>
<script>

    var posturl="";
    var index_id = "";
    var action="";
    $(document).ready(function () {
        $(this).find("[name='agree']").click(function () {
            //alert($(this).parent().attr("id"))
            posturl = $(this).attr("data")
            index_id = $(this).parent().attr("id")
            action = "agree"
            //$("#exampleModalLabel").innerHTML="同意"
            document.getElementById("exampleModalLabel").innerHTML = "同意 " + $(this).attr("department") + "的" + $(this).attr("userName") + "的" + $(this).attr("requestType") + "申请"
            document.getElementById("Modeldetail").innerHTML = $(this).attr("requestDetail")
            document.getElementById("message-text").value ="同意"
            $("#exampleModal").modal('show')
        });

        $(this).find("[name='reject']").click(function () {
            //alert($(this).parent().attr("id"))
            posturl = $(this).attr("data")
            index_id = $(this).parent().attr("id")
            action = "reject"
            //$("#exampleModalLabel").innerHTML="拒绝"
            document.getElementById("exampleModalLabel").innerHTML = "拒绝" + $(this).attr("department") + "的" + $(this).attr("userName") + "的" + $(this).attr("requestType") + "申请"
            document.getElementById("Modeldetail").innerHTML = $(this).attr("requestDetail")
            document.getElementById("message-text").value =""

            $("#exampleModal").modal('show')

        });

        $("#apply").click(function () {
            //alert(posturl+" data:" + document.getElementById("message-text").value)
            if (document.getElementById("message-text").value.length == 0) {
                alert("回复不能空，请填写");
                return ;
            }
            $.post(posturl, document.getElementById("message-text").value,
                    function (data, status) {
                        if (data.errCode == 0) {
                            $("#exampleModal").modal('hide')
                            if (action == 'agree') {
                                //alert("agreeeeee")
                                document.getElementById(index_id).innerHTML = "已同意"
                            } else {
                                //alert("rejectttttt")
                                document.getElementById(index_id).innerHTML = "已拒绝"
                            }

                        } else {
                            alert("操作失败："+data.errMsg)
                            $("#exampleModal").modal('hide')
                        }
                    },
                    'json'
            );
        });
        //$("#cancel").click();
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
                    <td>${item.requestDetail_AskForLeave}</td>
                    <td id="${item.requestId}">
                        <button userName="${item.userName}" department="${item.department}" requestType="${item.requestType}" requestDetail="${item.requestDetail_AskForLeave}" class="btn btn-primary" data="/a/request/approve/${item.requestId}/${item.stepOrder}/2" name="agree">同意</button>
                        <button userName="${item.userName}" department="${item.department}" requestType="${item.requestType}" requestDetail="${item.requestDetail_AskForLeave}" class="btn btn-primary" data="/a/request/approve/${item.requestId}/${item.stepOrder}/1" name="reject">拒绝</button>
                        <span name="agreeed" style="display:none">已同意</span><span name="rejected" style="display:none">已拒绝</span>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>


<div style="display: none;" class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
                <h4 class="modal-title" id="exampleModalLabel">同意</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group" id="Modeldetail">
                        <label for="recipient-name" class="control-label">姓名 请假类型 时间 详情</label>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">回复</label>
                        <textarea class="form-control" id="message-text"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="cancel" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="apply"  type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>
