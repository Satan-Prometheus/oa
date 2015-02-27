<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<body>
<h2>Hello World!</h2>

<c:if test="${not empty errMsg}">
    <c:out value="${errMsg}"/>
</c:if>

<form action="/a/login/do" method="post">
    <input type="text" name="userId" value="userId"/>
    <input type="text" name="password" value="password"/>

    <input type="submit"/>
</form>
</body>
</html>
