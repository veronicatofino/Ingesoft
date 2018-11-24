<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            width: 75%;
        }

        /* Right column */
        .rightcolumn {
            float: left;
            width: 25%;
            background-color: #f1f1f1;
            padding-left: 20px;
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
            background-image: url("https://www.utrgv.edu/csci/_files/images/abet.jpg");
            background-size: 750px 200px;
            padding: 20px;
        }

        .img_isr {
            background-color: #aaa;
            width: 100%;
            background-image: url("https://www.javerianacali.edu.co/sites/ujc/files/styles/img_slider_1280_235/public/banner-isr-2018-1.jpg");
            background-size: 270px 100px;
            padding: 20px;
        }

        .img_soft_engineering {
            background-color: #aaa;
            width: 100%;
            background-image: url("https://spectrum.ieee.org/image/MjkzMzk5NA.octet-stream");
            background-size: 270px 100px;
            padding: 20px;
        }

        .img_pnp_smbox {
            background-color: #aaa;
            width: 100%;
            background-image: url("https://i0.wp.com/techtales.co/wp-content/uploads/2017/08/P-vs-NP-Problem.png?fit=345%2C340");
            background-size: 270px 100px;
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
    </div>

    <div class="row">
        <div class="leftcolumn">
            <div class="card">
                <h1>Ultimas Noticias</h1>
            </div>
            <div class="card">
                <h3>Estudiante de postgrado de la Javeriana soluciona la conjetura de P = NP, Octubre 10, 2018</h3>
                <!-- <div class="img_pnp" style="height:200px;"/> --->
                <p>El estudiante Alejandro Hernandez soluciono el problema de la conjetura de P = NP en su tesis de doctorado.</p>
                <p>Este importante y doloroso aporte nos brinda nuevos puntos de vista respecto a nuestra seguridad en internet, por ahora los bancos y otras entidades financieras estan invirtiendo mas dinero en su seguridad.</p>
            </div>
            <div class="card">
                <h3>Javeriana Cali recibe acreditacion internacional, Febrero 10, 2018</h3>
                <!-- <div class="img_abet" style="height:200px;"/> --->
                <p>La Javeriana Cali reconocida por su increible rendimiento.</p>
                <p>La Javeriana de Cali fue acreditada recientemente por ABET, empresa encargada de la acreditacion de programas de educacion universitaria garantizando los criterios de calidad para la profesion. </p>
            </div>
        </div>
        <div class="rightcolumn">
            <div class="card">
                <h1>Eventos</h1>
            </div>
            <div class="card">
                <!-- <div class="img_isr" style="height:100px;"/> --->
                <p>International School on Rewriting. Octubre 20, 2018.</p>
            </div>
            <div class="card">
                <!-- <div class="img_pnp_smbox" style="height:100px;"/> --->
                <p>Presentacion de la prueba de P = NP. Octubre 22, 2018.</p>
            </div>
            <div class="card">
                <!-- <div class="img_soft_engineering" style="height:100px;"/> -->
                <p>Charla sobre Ingenieria de Software. Octubre 24, 2018.</p>
            </div>
        </div>
    </div>
</body>

</html>