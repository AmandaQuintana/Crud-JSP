<%-- 
    Document   : eliminar
    Created on : 12/04/2024, 2:42:31 p. m.
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
    <center>
        <h1>Eliminar Usuario</h1>
        <hr/>
        <form action ="/ejemplosesion/usuario?accion=buscar&redir=borrar" method="post">
        <table>
                <tr>
                    <th style="text-align: right">ID: </th>
                    <td><input type="text" name="id"/><th>
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
         <%
             if (alguien != null){
         %>
        <form action="/ejemplosesion/usuario?accion)borrar" method="post">
            <input type="hidden" name="id" value="<%=alguien.getId()%>"
                   <table>
                       <tr><td><input type="submit" value="Eliminar"/></td></tr>
            </table>
        </form>
        <%
            }
        %>
        <p style="color:#FF0000;">
            <%= (mensaje != null && ! mensaje.isEmpty()) ?mensaje: ""%>
        </p>
        <%= request.getSession().setAttribute("usuario.buscar", null);%>
    </center>
    </body>
</html>

