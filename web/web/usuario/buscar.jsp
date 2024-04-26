<%-- 
    Document   : buscar
    Created on : 12/04/2024, 2:42:24 p. m.
    Author     : Dereck
--%>
<%@page import="johnarrieta.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null){
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
    String alguien = (Usuario)request.getSession().getAttribute("usuario.buscar");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar Usuario</title>
    </head>
    <body>
    <center>
        <h1>Buscar Usuario</h1>
        <hr/>
        <form action ="/ejemplosesion/usuario?accion=buscar&redir=buscar" method="post">
        <table>
                <tr>
                    <th style="text-align: right">ID: </th>
                    <th><input type="text" name="id"/></th>
                </tr>
                <tr>
                    <th><input type="submit" value="Buscar"> </th>
                    <td><input type="reset" name="limpiar"/></td>
                </tr>
                <tr>
                    <th style="text-align: right">Password: </th>
                    <th style="text-align: left"><%= (alguien != null)?"*******":""%></th>
                </tr>
                <tr>
                    <th style="text-align: right">Nombre: </th>
                    <th style="text-align: left"><%= (alguien != null)?alguien.getNombre():""%></th>
                </tr>
                <tr>
                    <th style="text-align: right">Apellido: </th>
                    <th style="text-align: left"><%= (alguien != null)?alguien.getApellido():""%></th>
                </tr>
                <tr>
                    <th style="text-align: right">Email: </th>
                    <th style="text-align: left"><%= (alguien != null)?alguien.getEmail():""%></th>
                </tr>
                <tr>
                    <th style="text-align: right">Tipo: </th>
                    <td style="text-align: left">
                        <%= (alguien != null)?alguien.getTipo():""%>
                        </td>
                </tr>
                
        </table>
        </form>
        <hr/>
        <p style="color:#FF0000;">
            <%= (mensaje != null && ! mensaje.isEmpty()) ?mensaje: ""%>
        </p>
        <%= request.getSession().setAttribute("usuario.buscar", null);%>
    </center>
    </body>
</html>
