<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SDP Javeriana Cali</title>
    <style type="text/css">
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial;
            padding: 10px;
            background: #f1f1f1;
        }

        /* Header/Blog Title */
        .header {
            padding: 30px;
            text-align: center;
            background: white;
        }

        .header h1 {
            font-size: 50px;
        }

        /* Style the top navigation bar */
        .topnav {
            overflow: hidden;
            background-color: cornflowerblue;
        }

        /* Style the topnav links */
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 30px;
            text-decoration: none;
        }

        /* Change color on hover */
        .topnav a:hover {
            background-color: aliceblue;
            color: black;
        }

        /* Create two unequal columns that floats next to each other */
        /* Left column */
        .leftcolumn {
            float: left;
            width: 70%;
        }

        /* Right column */
        .rightcolumn {
            float: left;
            width: 30%;
            background-color: #f1f1f1;
            padding-left: 20px;
        }

        /* Add a card effect for articles */
        .card {
            background-color: white;
            padding: 20px;
            margin-top: 20px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Footer */
        .footer {
            padding: 20px;
            text-align: center;
            background: #ddd;
            margin-top: 20px;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 800px) {
            .leftcolumn, .rightcolumn {
                width: 100%;
                padding: 0;
            }
        }

        /* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
        @media screen and (max-width: 400px) {
            .topnav a {
                float: none;
                width: 100%;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>SDP Javeriana</h1>
    </div>

    <div class="topnav">
        <a href="/">Inicio</a>
        <a href="/estudiantes">Estudiantes</a>
        <a href="/postgrados">Postgrados</a>
        <a href="/aspirantes">Aspirantes</a>
        <a href="/profesoresGeneral">Profesores</a>
        <a href="/busqueda">Busqueda</a>
        <core:if test="${sessionScope.admin=='true'}">
            <a href="/noticiasgeneral">Noticias</a>
            <a href="/eventosgeneral">Eventos</a>
            <a href="/logout">Logout</a>
        </core:if>
        <core:if test="${sessionScope.admin=='false'}">
            <a href="/login">Login</a>
        </core:if>
    </div>

    <div class="row">
        <form:form id="editForm" method="post" action="login?send=true">
            <!-- Store button -->
            <center>
                <br>
                Usuario: <input type="text" name="user" required>
                <br>
                Password: <input type="password" name="password" required>
                <br>
                <br>
                <center><input type="submit" value="Inicia sesion" /></center>
            </center>

           <center>
                   ${specialMessage}
           </center>
        </form:form>
    </div>

</body>

</html>