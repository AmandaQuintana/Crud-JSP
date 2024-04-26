/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package johnarrieta.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import johnarrieta.modelo.CRUDUsuario;
import johnarrieta.modelo.Usuario;

/**
 *
 * @author Dereck
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario"})
public class ServletUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String accion = request.getParameter("accion");
            if(accion.equals("agregar")){
                CRUDUsuario crudAlguien =new CRUDUsuario();
                crudAlguien.getAlguien().setId(request.getParameter("id"));
                crudAlguien.getAlguien().setPassword(request.getParameter("password"));
                crudAlguien.getAlguien().setNombre(request.getParameter("nombre"));
                crudAlguien.getAlguien().setApellido(request.getParameter("apellido"));
                crudAlguien.getAlguien().setEmail(request.getParameter("email"));
                crudAlguien.getAlguien().setTipo(request.getParameter("tipo"));
                crudAlguien.agregarUsuario();
                response.sendRedirect("web/usuario/agregar.jsp?mensaje=Usuario " + request.getParameter("id")
                + " Agregado al Sistema");
                
            } else if (accion.equals("buscar")){
                Usuario alguien = CRUDUsuario.consultarUsuario(request.getParameter("id"));
                request.getSession().setAttribute("usuario.buscar", alguien);
                String redireccion = request.getParameter("redir");
                if (redireccion.equals("borrar")){
                    response.sendRedirect("web/usuario/eliminar.jsp");
                } else if (redireccion.equals("modificar")){
                    response.sendRedirect("web/usuario/modificar.jsp");
                } else{
                    response.sendRedirect("web/usuario/buscar.jsp");
                }
            } else if (accion.equals("modificar")){
                CRUDUsuario crudAlguien = new CRUDUsuario();
                crudAlguien.getAlguien().setId(request.getParameter("id"));
                crudAlguien.getAlguien().setPassword(request.getParameter("password"));
                crudAlguien.getAlguien().setNombre(request.getParameter("nombre"));
                crudAlguien.getAlguien().setApellido(request.getParameter("apellido"));
                crudAlguien.getAlguien().setEmail(request.getParameter("email"));
                crudAlguien.getAlguien().setTipo(request.getParameter("tipo"));
                crudAlguien.modificarUsuario();
                response.sendRedirect( "web/usuario/modificar.jsp?mensaje=Usuario"+request.getParameter("id") + "Modificado en el Sistema");
            } else if (accion.equals("borrar")){
                CRUDUsuario crudAlguien = new CRUDUsuario();
                crudAlguien.getAlguien().setId(request.getParameter("id"));
                crudAlguien.eliminarUsuario();
                response.sendRedirect( "web/usuario/eliminar.jsp?mensaje=Usuario"+ request.getParameter("id")+ "Eliminado del sistema");
            } else if (accion.equals("listartodo")){
                Usuario[] listado = CRUDUsuario.listarTodosLosUsuarios();
                request.getSession().setAttribute("usuario.listar", listado);
                response.sendRedirect("web/usuario/listar.jsp");
            }
            
            else if(accion.equals("login")){
                Usuario alguien = CRUDUsuario.iniciarSesion(request.getParameter("id"), request.getParameter("password"));
                request.getSession().setAttribute("usuario.login", alguien);
                response.sendRedirect("index.jsp?mensaje=Bienvenido al sistema");
            }
            else if(accion.equals("salir")){
                request.getSession().setAttribute("usuario.login", null);
                request.getSession().invalidate();
                response.sendRedirect("index.jsp?mensaje=Bienvenido al sistema");
            }
            else{
                response.sendRedirect("web/mensaje.jsp?mensaje=La Accion Solicitada no es Correcta");
            }
        }
        catch( Exception error){
            response.sendRedirect("web/mensaje.jsp?mensaje="+error.getMessage());
        }
        finally {
            out.close();
        }
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
