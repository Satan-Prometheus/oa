<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/3/2
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="/a/request/create/do" method="POST">
<select id="requestType" name="requestType">
    <c:forEach var="rtName" items="$requestTypeNames">
        <option value="$rtName">$rtName</option>
    </c:forEach>
</select>

</form>

</body>
</html>
