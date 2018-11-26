<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
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
            modifySelectedTextArea("h3", "h3");

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
    <style>
        .navbar-custom {
            background-color: #233cbf;
        }

        .btn-primary,
        .btn-primary:hover,
        .btn-primary:active,
        .btn-primary:visited,
        .btn-primary:focus {
            background-color: #233cbf;
            border-color: #233cbf;
        }

        /* change the brand and text color */
        .navbar-custom .navbar-brand,
        .navbar-custom .navbar-text {
            color: rgba(255,255,255,.8);
        }

        /* change the link color */
        .navbar-custom .navbar-nav .nav-link {
            color: rgba(255,255,255,.5);
        }

        /* change the color of active or hovered links */
        .navbar-custom .nav-item.active .nav-link,
        .navbar-custom .nav-item:hover .nav-link {
            color: #ffffff;
        }

        /* for dropdown only - change the color of droodown */
        .navbar-custom .dropdown-menu {
            background-color: #ff5500;
        }
        .navbar-custom .dropdown-item {
            color: #ffffff;
        }
        .navbar-custom .dropdown-item:hover,
        .navbar-custom .dropdown-item:focus {
            color: #333333;
            background-color: rgba(255,255,255,.5);
        }
    </style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="stylePrueba.css">
    <title>SDP Javeriana</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-custom sticky-top">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/estudiantes">Estudiantes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/postgrados">Postgrados</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/aspirantes">Aspirantes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profesoresGeneral">Profesores</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/busqueda">Buscar</a>
            </li>
            <core:if test="${sessionScope.admin=='true'}">
                <li class="nav-item">
                    <a class="nav-link" href="/noticiasgeneral">Noticias</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/eventosgeneral">Eventos</a>
                </li>
            </core:if>
        </ul>
    </div>
    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto" href="/"><img src="http://pegasus.javeriana.edu.co/~CIS1410IS10/img/600px-Javeriana.svg.png" width="60" height="70"></a>
        <button class="navbar-toggler" style="background-color: #233cbf; color: white" type="button" data-toggle="collapse" data-target=".dual-collapse2">
            <span>Menu</span>
        </button>
    </div>

    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
        <ul class="navbar-nav ml-auto">
            <core:if test="${sessionScope.admin=='false'}">
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </core:if>
            <core:if test="${sessionScope.admin=='true'}">
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            </core:if>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <!-- Edit of the content -->
                <core:if test="${buttonType=='0'}">
                    <form:form id="editForm" method="post" action="aspirantes?storeFlag=true">
                        <!-- Store button -->
                        <br>
                        <center><input class="btn btn-primary btn-large" type="submit" value="Guardar" /></center>
                        <br>
                        <center>
                            <button class="btn btn-primary btn-large" type="button" onclick=negrilla()>Negrilla</button>
                            <button class="btn btn-primary btn-large" type="button" onclick=tema()>Tema</button>
                            <button class="btn btn-primary btn-large" type="button" onclick=subtema()>Subtema</button>
                            <button class="btn btn-primary btn-large" type="button" onclick=centrar()>Centrar</button>
                            <button class="btn btn-primary btn-large" type="button" onclick=derecha()>Derecha</button>
                            <button class="btn btn-primary btn-large" type="button" onclick=link()>Link</button>
                            <button class="btn btn-primary btn-large" type="button" onclick=imagen()>Imagen</button>
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
                    <core:if test="${sessionScope.admin=='true'}">
                        <form:form id="editForm" method="post" action="aspirantes?editFlag=true">
                            <br>
                            <input class="btn btn-primary btn-large" type="submit" value="Editar" />
                        </form:form>
                    </core:if>

                    <!-- Displaying of the content -->
                    ${content}

                </core:if>

            </div>
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

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>