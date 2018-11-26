<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            width: 100%;
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
        <p> Bienvenido </p>
    </div>

    <div class="topnav">
        <a href="/">Inicio</a>
        <a href="/estudiantes">Estudiantes</a>
        <a href="/postgrados">Postgrados</a>
        <a href="/aspirantes">Aspirantes</a>
        <a href="/profesoresGeneral">Profesores</a>
        <a href="/eventoscalendario">Calendario de eventos</a>
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
        <div class="leftcolumn">
            <div class="card">
                <core:if test="${buttonType=='1'}">
                    <form:form id="editForm" method="post" action="profesoresGeneral?storeFlag=true">
                        <!-- Store button -->
                        <center><input type="submit" value="Guardar" /></center>
                        <br>
                        Nombre del profesor: <input type="text" name="name" required>
                    </form:form>
                </core:if>

                <core:if test="${buttonType=='0'}">
                    <core:if test="${sessionScope.admin=='true'}">
                        <form:form id="editForm" method="post" action="profesoresGeneral?editFlag=true">
                            <input type="submit" value="Crear profesor" />
                        </form:form>
                    </core:if>
                    <br>
                    ${content}
                </core:if>

            </div>
        </div>
    </div>

    <script>
        /** Selection of a piece of text **/
        document.querySelector('textarea').addEventListener('mouseup', function () {
            leftSelection = this.selectionStart;
            rightSelection = this.selectionEnd;
        });
        /**
         * This happens when the mouse leaves the text area, selection needs to be the same as before
         * otherwise it is cleared*
         */
        document.querySelector('textarea').addEventListener('mouseleave', function () {
            leftSelection = this.selectionStart;
            rightSelection = this.selectionEnd;
        });
        /** Keyboard listener **/
        document.querySelector('textarea').addEventListener('keyup', function () {
            applyLiveChanges();
        });
        /** Apply when the page loads **/
        document.addEventListener("DOMContentLoaded", function() {
            applyLiveChanges();
        });
    </script>
</body>

</html>