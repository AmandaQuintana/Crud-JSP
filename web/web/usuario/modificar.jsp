<%-- 
    Document   : modificar
    Created on : 12/04/2024, 2:42:56 p. m.
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
        <title>Modificar Usuario</title>
    </head>
    <body>
    <center>
        <h1>Modificar Usuario</h1>
        <hr/>
        <form action ="/ejemplosesion/usuario?accion=buscar&redir=modificar" method="post">
        <table>
                <tr>
                    <th style="text-align: right">ID: </th>
                    <th><input type="text" name="id"/></th>
                </tr>
                <tr>
                    <th><input type="submit" value="Buscar"> </th>
                    <td><input type="reset" name="limpiar"/></td>
                </tr>
               </table>
        </form>
        <hr/>
        <%
             if (alguien != null){
         %>
        <form action ="/ejemplosesion/usuario?accion=modificar" method="post">
            <table>
                <tr>
                    <th style="text-align: right">ID: </th>
                    <td><input type="text" name="id" value="<%= (alguien != null) ? alguien.getId() : ""%>" readonly="readonly"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Password:</th>
                    <th><input type="password" name="password" value="<%= (alguien != null) ? alguien.getPassword() : ""%>"/></th>
                </tr>
                 <tr>
                    <th style="text-align: right">Nombre:</th>
                    <th><input type="text" name="nombre" value="<%= (alguien != null) ? alguien.getNombre() : ""%>"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Apellido:</th>
                    <th><input type="text" name="apellido" value="<%= (alguien != null) ? alguien.getApellido() : ""%>"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Email:</th>
                    <th><input type="text" name="email" value="<%= (alguien != null) ? alguien.getEmail() : ""%>"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Tipo: </th>
                    <th>
                        <select name="tipo">
                            <option value="Administrador"
                                    <%= (alguien != null && alguien.getTipo().equals("Administrador")) ? "selected" : ""%>
                                    Administrador</option>
                            <option value="Cliente"
                                    <%= (alguien != null && alguien.getTipo().equals("Cliente")) ? "selected" : ""%>
                                    Cliente</option>
                        </select>
                    </th>     
                </tr>
                    <tr>
                    <th><input type="submit" name="MODIFICAR"/></th>
                    <th><input type="reset" name="LIMPIAR"/></th>
                </tr> 
                
        </table>
        </form>
        <hr/>
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

