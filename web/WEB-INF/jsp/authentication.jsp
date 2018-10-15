<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Sebastían
  Date: 6/06/2017
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inicio de sesion.</title>
</head>
<body>
    <h2>${message}</h2>
    <form:form id="loginForm" method="post" action="authentication" modelAttribute="userBean">
        <form:label path="username">Enter your user-name</form:label>
        <form:input id="username" name="username" path="username" /><br>
        <form:label path="username">Please enter your password</form:label>
        <form:password id="password" name="password" path="password" /><br>
        <input type="submit" value="Submit" />
    </form:form>

</body>
</html>
