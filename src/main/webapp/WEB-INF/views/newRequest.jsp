<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/3/2
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<div class="panel panel-default">

    <div class="panel-body">

        <div class="container">
            <div class="row form-group">
                <label for="requestType" class="col-sm-1 my-label">选择申请类型</label>

                <div class="col-sm-6">
                    <select id="requestType" class="form-control" name="requestType"
                            onchange="requestTypeChange(this.options[this.selectedIndex].value);">
                        <c:forEach var="rtName" items="${requestTypeNames}">
                            <option value="${rtName}">${rtName}</option>
                        </c:forEach>
                        <option value=" " selected></option>
                    </select>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-body">
        <div class="container">

            <div class="row form-group">
                <div class="col-md-3 col-md-offset-1">
                    <div class="input-group">
                        <div class="input-group-addon">姓名</div>
                        <input type="text" class="form-control" placeholder="${user.name}" readonly>
                    </div>
                </div>


                <div class="col-md-3 col-md-offset-1">
                    <div class="input-group">
                        <div class="input-group-addon">部门</div>
                        <input type="text" class="form-control" placeholder="${user.department}" readonly>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <div class="col-md-3 col-md-offset-1">
                    <div class="input-group">
                        <div class="input-group-addon">工号</div>
                        <input type="text" class="form-control" placeholder="${user.id}" readonly>
                    </div>
                </div>


                <div class="col-md-3 col-md-offset-1">
                    <div class="input-group">
                        <div class="input-group-addon">申请日期</div>
                        <input type="text" class="form-control" placeholder="${date}" readonly>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="begOff" class="panel panel-default" style="display: none;">
    <div class="panel-body">

        <div class="container">
            <div class="row form-group">
                <label for="div-day-length" class="col-sm-1 my-label">请假时长</label>

                <div class="col-sm-3">
                    <div id="div-day-length" class="input-group">

                        <input id="begoff-day" type="text" class="form-control" placeholder="0" maxlength="2">

                        <div class="input-group-addon">天</div>
                        <input id="begOff-hour" type="text" class="form-control" placeholder="0" maxlength="2">

                        <div class="input-group-addon">小时</div>
                    </div>
                </div>
            </div>

            <div class="row form-group">
                <label for="div-day-range" class="col-sm-1 my-label">请假时段</label>

                <div class="col-sm-6">
                    <div id="div-day-range" class="input-group">

                        <div class="input-group-addon">从</div>
                        <input id="begoff-from" type="text" class="form-control" placeholder="1900/01/01 00:00"
                               maxlength="16">

                        <div class="input-group-addon">至</div>
                        <input id="begoff-to" type="text" class="form-control" placeholder="1900/01/01 00:00"
                               maxlength="16">
                    </div>
                </div>
            </div>

            <div class="row form-group">

                <label for="div-begoff-desc" class="col-sm-1 my-label">请假说明</label>

                <div class="col-sm-6">
                    <div id="div-begoff-desc" class="input-group">
                        <textarea id="begoff-desc" class="form-control" rows="6" cols="200"></textarea>
                    </div>
                </div>
            </div>

            <div id="div-errMsg" class="bg-danger">
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <button id="begOff-submit" type="submit" class="btn btn-primary btn-block">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var currentShowing = null

    function requestTypeChange(val) {
        var boxId = chooseFormBox(val)
        if (currentShowing != null) {
            $("#" + currentShowing).hide()
        }
        if (boxId == null) return
        currentShowing = boxId
        $("#" + boxId).show()
    }

    function chooseFormBox(requestType) {
        var begOff = ["病假", "调休", "事假", "年休"]


        if (arrContains(begOff, requestType)) {
            return "begOff";
        }

        return null
    }

    function arrContains(vec, val) {
        for (var i = 0; i < vec.length; i++) {
            if (vec[i] == val) return true
        }
        return false
    }

    $(document).ready(function () {

        $("#begOff-submit").click(function () {

            $("#begOff .has-error").removeClass("has-error")
            var $errMsg = $("#div-errMsg")
            $errMsg.empty()

            var data = new Object()
            data.requestType = $.trim($("#requestType").val())
            data.day = $.trim($("#begoff-day").val())
            data.hour = $.trim($("#begoff-hour").val())
            data.requestReason = $.trim($("#begoff-desc").val())
            data.from = $.trim($("#begoff-from").val())
            data.to = $.trim($("#begoff-to").val())

            var errInputList = new Array()
            var errMsgList = new Array()
            if (data.requestType == "") {
                errInputList.push("requestType");
                errMsgList.push("类型不能为空")
            }
            if (isNaN(data.day = Number(data.day))) {
                errInputList.push("begoff-day");
                errMsgList.push("天数应该是数字")
            }
            if (isNaN(data.hour = Number(data.hour)) || data.hour < 0 || data.hour > 23) {
                errInputList.push("begoff-hour")
                errMsgList.push("小时为 [0-23]")
            }
            if (data.hour == 0 && data.day == 0) {
                errInputList.push("begoff-day")
                errMsgList.push("请假时长不能为0")
            }
            if (data.requestReason == "") {
                errInputList.push("begoff-desc")
                errMsgList.push("申请缘由不能为空")
            }
            if (isNaN(data.from=Date.parse(data.from+":00"))) {
                errInputList.push("begoff-from")
                errMsgList.push("起始日期格式错误")
            }
            if (isNaN(data.to=Date.parse(data.to+":00"))) {
                errInputList.push("begoff-to")
                errMsgList.push("结束日期格式错误")
            }
            if (!isNaN(data.from) && !isNaN(data.to) && data.from >= data.to) {
                errInputList.push("begoff-from")
                errInputList.push("begoff-to")
                errMsgList.push("起始日期应该小于结束日期")
                errMsgList.push("结束日期应该大于起始日期")
            }

            if (errInputList.length > 0) {
                for (var i=0 ; i < errInputList.length; i++) {
                    $("#"+errInputList[i]).parent().addClass("has-error")
                }



                for (var i=0; i < errMsgList.length; i++) {
                    $errMsg.append("<p>"+ errMsgList[i] +"</p>")
                }

                return
            }

            $.post("/a/request/create/do", data, function (result) {

                if (result.errCode == 0) {
                    alert("success!!")
                    $("#li-mylist").click();
                }

            }, 'json')
        })

    })


</script>
