<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24. 8. 5.
  Time: 오후 4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="message" items="${messages}">
        <li>
            ${message}
        </li>
    </c:forEach>
</ul>
<form action="/message/send" method="get">
    <button type="submit">새 쪽지 보내기</button>
</form>
</body>
</html>