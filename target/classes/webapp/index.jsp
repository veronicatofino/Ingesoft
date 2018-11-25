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
        <a href="/noticiasgeneral">Noticias</a>
        <a href="/eventosgeneral">Eventos</a>
    </div>

    <div class="row">
        <div class="leftcolumn">
            <div class="card">
                <h1>Ultimas Noticias</h1>
            </div>
            <div class="card">
                    ${newsArr[0]}
            </div>
            <div class="card">
                ${newsArr[1]}
            </div>
            <div class="card">
                ${newsArr[2]}
            </div>
            <div class="card">
                ${newsArr[3]}
            </div>
        </div>
        <div class="rightcolumn">
            <div class="card">
                <h1>Eventos</h1>
            </div>
            <div class="card">
                <div style="height: 725px; overflow: auto; width:100%; margin:0; padding:0">
                    <p>${eventArr[0][0]}.</p>

                    ${eventArr[0][1]}
                </div>
            </div>
            <div class="card">
                <div style="height: 725px; overflow: auto; width:100%; margin:0; padding:0">
                    <p>${eventArr[1][0]}.</p>

                    ${eventArr[1][1]}
                </div>
            </div>
        </div>
    </div>
</body>

</html>