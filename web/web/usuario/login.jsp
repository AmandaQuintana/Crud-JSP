<%-- 
    Document   : login
    Created on : 12/04/2024, 2:42:48 p. m.
    Author     : Dereck
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null){
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu de la Aplicacion</title>
    </head>
    <body>
    <center>
        <table border="0">
            <tbody>
                <tr>
                    <th><h1><a href="web/usuario/agregar.jsp" >Agregar</a></h1></th>
                </tr>
                <tr>
                    <th><h1><a href="web/usuario/buscar.jsp" >Buscar</a></h1></th>
                </tr>
                <tr>
                    <th><h1><a href="web/usuario/modificar.jsp" >Modificar</a></h1></th>
                </tr>
                <tr>
                    <th><h1><a href="web/usuario/eliminar.jsp" >Eliminar</a></h1></th>
                </tr>
                <tr>
                    <th><h1><a href="usuario?accion=listartodo" >Listar</a></h1></th>
                </tr>
                <tr>
                    <th><h1><a href="/ejemplosesion/usuario?accion=logout" >Salir</a></h1></th>
                </tr>
            </tbody>
        </table>
    </center>
    </body>
</html>
