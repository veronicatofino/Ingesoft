<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>SDP Javeriana Cali</title>
    <script>
        var leftSelection;
        var rightSelection;

        function modifySelectedTextArea(openTag, closeTag) {
            size = document.getElementById("textarea").value.length;
            modifiedPart = document.getElementById("textarea").value;
            modifiedPart = modifiedPart.substring(leftSelection, rightSelection);
            modifiedPart = "<" + openTag  + ">" + modifiedPart + "</" + closeTag  + ">";
            firstPart = document.getElementById("textarea").value.substring(0, leftSelection);
            secondPart = document.getElementById("textarea").value.substring(rightSelection, size);
            document.getElementById("textarea").value = firstPart + modifiedPart + secondPart;
        }

        function negrilla() {
            modifySelectedTextArea("B", "B");

            applyLiveChanges();
        }

        function tema() {
            modifySelectedTextArea("h1", "h1");

            applyLiveChanges();
        }

        function subtema() {
            modifySelectedTextArea("p style=\"padding-left: 2em; display:inline\"", "p");

            applyLiveChanges();
        }

        function link() {
            var link = prompt("Digite el link:", "");
            modifySelectedTextArea("a href=\"" + link + "\"", "a");

            applyLiveChanges();
        }

        function imagen() {
            var link = prompt("Digite el link de la imagen:", "");
            modifySelectedTextArea("img src=\"" + link + "\"", "img");

            applyLiveChanges();
        }

        function centrar() {
            modifySelectedTextArea("center", "center");

            applyLiveChanges();
        }

        function derecha() {
            modifySelectedTextArea("div style=\"text-align: right;\"", "div");

            applyLiveChanges();
        }

        function applyLiveChanges() {
            /** Pick up the table **/
            var table = document.getElementById("tableId");
            /** Ineficient perhaps?, replace newlines by html new line **/
            var textArea = document.getElementById("textarea").value;
            textArea = textArea.replace(/\n/g, "<br/>");
            /** Render the changes **/
            document.getElementById("realtimediv").innerHTML = textArea;
        }

    </script>
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
        <a href="/noticiasgeneral">Noticias</a>
        <a href="/eventosgeneral">Eventos</a>
    </div>

    <div class="row">
        <div class="leftcolumn">
            <div class="card">

                <!-- Edit of the content -->
                <core:if test="${buttonType=='0'}">
                    <form:form id="editForm" method="post" action="eventos?storeFlag=true&id=${id}">
                        <!-- Store button -->
                        <center><input type="submit" value="Guardar" /></center>
                        <br>
                        <center>Fecha del evento: <input id="date" type="date" name="date" required></center>
                        <br>
                        <center>
                            <button type="button" onclick=negrilla()>Negrilla</button>
                            <button type="button" onclick=tema()>Tema</button>
                            <button type="button" onclick=subtema()>Subtema</button>
                            <button type="button" onclick=centrar()>Centrar</button>
                            <button type="button" onclick=derecha()>Derecha</button>
                            <button type="button" onclick=link()>Link</button>
                            <button type="button" onclick=imagen()>Imagen</button>
                        </center>
                        <br/>
                        <!-- Edit of the content -->
                        <table style="width:100%" id = "tableId">
                            <tr>
                                <td>
                                    <textarea name="modifiedContent" class="textarea" id="textarea" style="height: 725px; width: 600px"><core:out value="${editableContent}"/></textarea>
                                </td>
                                <td>
                                    <div id="realtimediv" align="left" style="height: 725px; overflow: auto; width:85%; margin:0; padding:0"></div>
                                </td>
                            </tr>
                        </table>
                    </form:form>
                </core:if>

                <!-- Displaying of the content -->
                <core:if test="${buttonType=='1'}">
                    <form:form id="editForm" method="post" action="eventos?editFlag=true&id=${id}">
                        <input type="submit" value="Editar" />
                    </form:form>

                    <!-- Displaying of the content -->
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