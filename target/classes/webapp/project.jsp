<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
</head>
<body>

<h2>Proyecto: </h2> <h3>${name}</h3> </br> </br>
<h2>Descripcion: </h2> <h3>${description}</h3>

<img src="/image?id=${id}&type=project" width="115" border="0"></img>


</body>
</html>