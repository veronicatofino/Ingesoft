<%--
  Created by IntelliJ IDEA.
  User: Sebastían
  Date: 6/06/2017
  Time: 10:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Bienvenido ${displayUsername}</title>
</head>
<body>
    <h2>${displayUsername}</h2>

    <core:forEach items="${courses}" var="course">
        <tr>
            <a href="/AbetApp/course?name=${course.name}&identifier=${course.identifier}"> ${course.name} </a>
            <br>
        </tr>
    </core:forEach>

</body>
</html>
