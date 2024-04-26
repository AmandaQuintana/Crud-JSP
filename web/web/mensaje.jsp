<%-- 
    Document   : mensaje
    Created on : 12/04/2024, 4:43:31 p. m.
    Author     : Dereck
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje del sistema</title>
    </head>
    <body>
    <center>
        <h1>
            <%=request.getParameter("mensaje")%>
        </h1>
        <hr/>
        <a href="usuario/login.jsp"> <<< Volver ::: </a><!-- comment -->
    </center> 
    </body>
</html>
