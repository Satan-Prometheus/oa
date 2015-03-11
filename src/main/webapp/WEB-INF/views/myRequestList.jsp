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
    <h2 class="sub-header">我的申请</h2>

    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>请假类型</th>
                <th>申请时间</th>
                <th>状态更新时间</th>

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
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>
