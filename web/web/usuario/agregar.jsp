<%-- 
    Document   : agregar
    Created on : 12/04/2024, 2:42:12 p. m.
    Author     : Dereck
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("usuario.login") == null){
        getServletContext().getRequestDispatcher("/web/usuario/login.jsp").forward(request, response);
    }
    String mensaje = request.getParameter("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Usuario al Sistema</title>
    </head>
    <body>
    <center>
        <h1>Agregar Usuario</h1>
        <hr/>
        <form action ="/ejemplosesion/usuario?accion=agregar" method="post">
        <table>
                <tr>
                    <th style="text-align: right">ID: </th>
                    <th><input type="text" name="id"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Password: </th>
                    <th><input type="password" name="password"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Nombre: </th>
                    <th><input type="text" name="nombre"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Apellido: </th>
                    <th><input type="text" name="apellido"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Email: </th>
                    <th><input type="text" name="email"/></th>
                </tr>
                <tr>
                    <th style="text-align: right">Tipo: </th>
                    <th>
                        <select name="tipo">
                            <option value="Administrador">Administrador</option>
                            <option value="Cliente">Cliente</option>
                        </select>
                    </th>
                </tr>
                <tr>
                    <th><input type="submit" name="ENTRAR"/></th>
                    <th><input type="reset" name="LIMPIAR"/></th>
                </tr> 
        </table>
        </form>
        <hr/>
        <p style="color:#FF0000;">
            <%= (mensaje != null && ! mensaje.isEmpty()) ?mensaje: ""%>
        </p>
    </center>
    </body>
</html>

