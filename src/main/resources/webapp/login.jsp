<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
</head>
<body>
<form:form id="editForm" method="post" action="login">
        <label>
            Usuario:
        </label>
        <input type="text" name="user" required>

        <label>
            Contraseña:
        </label>
        <input type="password" name="password" required>

    <input type="submit" value="Submit" />
</form:form>

${mensaje}

</body>
</html>