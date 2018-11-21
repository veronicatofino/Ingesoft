<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"
      xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core">
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
            width: 75%;
        }

        /* Right column */
        .rightcolumn {
            float: left;
            width: 25%;
            background-color: #f1f1f1;
            padding-left: 20px;
        }

        /* Fake image */
        .fakeimg {
            background-color: azure;
            width: 100%;
            padding: 20px;
        }

        .img_pnp {
            background-color: #aaa;
            width: 100%;
            background-image: url("https://i.ytimg.com/vi/YX40hbAHx3s/maxresdefault.jpg");
            background-size: 500px 200px;
            padding: 20px;
        }

        .img_abet {
            background-color: #aaa;
            width: 100%;
            background-image: url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScKIzoptwk33zuZyawPorbm3vi2TLRdw_5woEZNFW60gjAm11nhQ");
            background-size: 243px 200px;
            padding: 20px;
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
        <p> Bienvenido, la fecha es: #{DateController.date}</p>
    </div>

    <div class="topnav">
        <a href="/">Inicio</a>
        <a href="/estudiantes">Estudiantes</a>
        <a href="#">Profesores</a>
        <a href="/aspirantes">Aspirantes</a>
    </div>

    <div class="row">
        <div class="leftcolumn">
            <div class="card">
                <h2>Informacion financiera</h2>
                <h2>Convenios nacionales e internacionales</h2>
                <h2>Estudios:</h2>
                <a style="padding-left: 2em" href="/doctorado.xhtml">Informacion del doctorado.</a>
                <p style="padding-left: 2em">Informacion de las maestrias.</p>
                <p style="padding-left: 2em">Informacion de las especializaciones.</p>
            </div>
        </div>
    </div>
</body>

</html>