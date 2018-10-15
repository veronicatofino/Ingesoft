<%--
  Created by IntelliJ IDEA.
  User: Sebastían
  Date: 7/06/2017
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Curso ${name} </title>
</head>
<body>
    <h2> Bienvenido al curso: ${name}  grupo: ${group} </h2>
    <br>

    <core:forEach items="${students}" var="student">
        <tr>
            <h2> ${student.name} </h2>
            <br>
        </tr>
    </core:forEach>

    <h2> Ingresar notas </h2>
</body>
</html>
