<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <style>
        .main .btn-primary,
        .btn-primary:hover,
        .btn-primary:active,
        .btn-primary:visited,
        .btn-primary:focus {
            background-color: rgb(0,0,0);
            border-color: rgb(0,0,0);
        }
        a{
        	float:center;
        }
        a:link{
        	color:Black;
        	font:Arial,bold;
        	text-decoration:none;
        	font-size:150%;
        	
        }
        
        a:link:hover{
        	color:#800000;
        }
        
		body {
          font-family: Arial, sans-serif;
          background-color:#f1f1f1;
          
        }
        
		html,body,header,h1,a,p {
			padding:0;
			margin:0;
		}
	
		#navbar {
		  overflow: hidden;
		  background-color: rgb(171,0,15);
		}		
		#navbar a {
		  float: left;
		  display: block;
		  color: #333;
		  text-align: center;
		  padding: 14px 20px;
		  text-decoration: none;
		  font-size: 17px;
		}
		.my-header h1 { 
		    display: inline;
		    text-align:left; 
		    float:center;
		    font-size:280%; 
		    color:white; 
		    font-family:Arial
		}
    </style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="stylePrueba.css">
    <title>Registro</title>
</head>
<body>
	<div id="navbar" class="my-header">
	 	<tr style="float:center"><a class="navbar-brand mx-auto" href="/"><img src="https://cdn141.picsart.com/257937040000212.png?r1024x1024" width="60" height="70"></a>
        </tr>
        <h1>SIGERE</h1>
	</div> 
    <div class=main>
    	<div class="row">
            <div class="col-md-12">
                    <center>
                        <h1 style="padding: 15px 0px">Crea tu cuenta</h1>
                        <form:form id="regForm" modelAttribute="user" action="registerProcess"
						method="post">
                            <div class="form-group w-50 p-1">
                                Nombre de Usuario:
                                <input type="text" name="username" class="form-control" required>
                            </div>
                             <div class="form-group w-50 p-1">
                                Contraseña:
                                <input type="password" name="password" class="form-control" required>
                            </div>
                            <div class="form-group w-50 p-1">
                                Nombre:
                                <input type="text" name="firstname" class="form-control" required>
                            </div>
							<div class="form-group w-50 p-1">
                                Apellido:
                                <input type="text" name="lastname" class="form-control" required>
                            </div>
                            
                            <div class="form-group w-50 p-1">
                                Correo:
                                <input type="email" class="form-control" name="email" required>
                            </div>
                            <input type="submit" class="btn btn-primary" value="Aceptar" />
    				</form:form>           
                
			<tr>
				<td></td>
				<p>
				</p>
				<td ><a href="home" style="float:center">Inicio</a></td>
			</tr>
                </center>
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