<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/3/5
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
    <div class="panel panel-default">

        <div class="panel-body">
            <div class="row form-group">
                <label for="listType" class="col-sm-1 my-label">处理状态</label>

                <div class="col-sm-6">
                    <select id="listType" class="form-control" name="listType"
                            onchange="listTypeChange(this.options[this.selectedIndex].value);">
                        <option value="all">全部请求</option>
                        <option value="done">已结束</option>
                        <option value="ing">处理中</option>
                    </select>
                </div>
            </div>
        </div>
    </div>


    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>请假类型</th>
                <th>申请时间</th>
                <th>状态更新时间</th>
                <th>最后更新人</th>
                <th>状态</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${list}" varStatus="status">
                <tr>
                    <td>${item.requestType}</td>
                    <td><fmt:formatDate value="${item.createTime}" type="both"
                                        pattern="yyyy-MM-dd hh:mm:ss"/></td>
                    <td><fmt:formatDate value="${item.lastUpdateTime}" type="both"
                                        pattern="yyyy-MM-dd hh:mm:ss"/></td>
                    <td>${item.lastOperatorName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.approve == 0}">未完成</c:when>
                            <c:when test="${item.approve == 1}">已拒绝</c:when>
                            <c:when test="${item.approve == 2}">已通过</c:when>
                        </c:choose></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<script>
    function listTypeChange(val) {
        if (val == "all") {
            $("#div-main").load("/a/request/my/list/all")
        } else if (val == "done") {
            $("#div-main").load("/a/request/my/list/done")
        } else if (val == "ing") {
            $("#div-main").load("/a/request/my/list/ing")
        }
    }

    $(document).ready(function () {
        $("#listType").val("${listType}")
    })
</script>