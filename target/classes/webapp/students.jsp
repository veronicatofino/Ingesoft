<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>SDP Javeriana Cali</title>
    <!-- Script for the button -->
    <script type="text/javascript">
        var offset = 0;
        function add(key) {
            /** Left side **/
            var container = document.getElementById(key);

            var elementLeft = document.createElement("input");
            elementLeft.setAttribute("type", "text");
            elementLeft.setAttribute("name", key + "@" + offset + "@TITLE");
            container.appendChild(elementLeft);
            /** Right side **/
            var elementRight = document.createElement("input");
            elementRight.setAttribute("type", "text");
            elementRight.setAttribute("name", key + "@" + offset + "@URL");
            container.appendChild(elementRight);
            container.appendChild(document.createElement("br"))
            offset += 1
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
        <p> Bienvenido </p>
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

                <!-- Edit of the content -->
                <core:if test="${buttonType=='0'}">
                    <form:form id="editForm" method="post" action="estudiantes?storeFlag=true">
                        <input type="submit" value="Guardar" />

                        <!-- Edit of the content -->
                        <core:forEach var="entry" items="${editComposition}">
                            <!-- Main topic -->
                            <h2> ${entry.key} </h2>
                            <!-- Subtopics -->
                            <core:forEach var="pair" items="${entry.value}">
                                <core:set var = "tempString" value = "${pair.left}"/>
                                <input type = "text" name = "${entry.key}@${pair.left}@TITLE" value = "${pair.left}" size="${fn:length(tempString)}"/>
                                <core:set var = "tempString" value = "${pair.right}"/>
                                <input type = "text" name = "${entry.key}@${pair.left}@URL" value = "${pair.right}" size="${fn:length(tempString)}"/>
                                </br>
                            </core:forEach>
                            <!-- Button -->
                            <div id="${entry.key}"> </div>

                            <input type= "button" id="addrows" name="addrows" value="Add Rows '${entry.key}'" onclick= "add('${entry.key}');" >
                            <br>
                        </core:forEach>
                    </form:form>
                </core:if>

                <!-- Displaying of the content -->
                <core:if test="${buttonType=='1'}">
                    <form:form id="editForm" method="post" action="estudiantes?editFlag=true">
                        <input type="submit" value="Editar" />
                    </form:form>

                    <!-- Displaying of the content -->
                    <core:forEach var="entry" items="${composition}">
                        <h2> ${entry.key} </h2>
                        <core:forEach var="pair" items="${entry.value}">
                            <a style="padding-left: 2em" href="${pair.right}">${pair.left}.</a>
                            </br>
                        </core:forEach>
                    </core:forEach>
                </core:if>


            </div>
        </div>
    </div>
</body>

</html>