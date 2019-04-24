<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
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
    <title>SIGERE</title>
</head>
<body>

<!-- Barra -->
<nav class="navbar navbar-expand-md navbar-custom sticky-top">
    <div class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
            <p>SIGERE</p>
            
            
    </div>
    <div class="MiClass">
    	<table align="left">
    			<tr>
      				<th class="form-heading"><a href="register">Registrarse</a></th>
    			</tr>
  		</table>
    </div>
    <div class="mx-auto order-0">
        <a class="navbar-brand mx-auto" href="/"><img src="https://queanimalada.net/wp-content/uploads/2012/09/foto-graciosa-gato-negro-bigote-blanco-mancha-curiosidades.jpg" width="60" height="70"></a>
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
                <center>
                    <form:form id="loginForm" modelAttribute="login" action="loginProcess" method="post">
                        <div class="form-group w-50 p-3">
                            <label for="exampleInputEmail1">
                                Usuario:
                            </label>
                            <input type="text" name="username" class="form-control" required>
                        </div>

                        <div class="form-group w-50 p-3">
                            <label for="exampleInputPassword1">
                                Contraseña:
                            </label>
                            <input type="password" class="form-control" name="password" required>
                        </div>

                        <input type="submit" class="btn btn-primary" value="Entrar" />
                    </form:form>

                    <center>
                        ${specialMessage}
                    </center>
                </center>
            </div>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>