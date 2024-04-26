/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package johnarrieta.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import com.mysql.cj.xdevapi.Result;

/**
 *
 * @author Dereck
 */
public class CRUDUsuario {
    private Usuario alguien;
    private ConexionBaseDatos baseDatos;
    
    public void agregarUsuario () throws Exception{
        if (alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El ID del usuario es Necesario");
        }
        //armar el SQL INSERT de forma dinamica
        String sqlInsert = "INSERT INTO Usuarios "
                + "(id, password, nombre, apelllido, email, tipo) "
                + "VALUES (?,?,?,?,?,?)";
        try{
            //Crear una sentencia JDBC mediante la sentencia SQL anterior
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlInsert);
            //Pasarle los datos del usuario a la sentencia SQL
            sentenciaSQL.setString(1, alguien.getId());
            sentenciaSQL.setString(2, alguien.getPassword());
            sentenciaSQL.setString(3, alguien.getNombre());
            sentenciaSQL.setString(4, alguien.getApellido());
            sentenciaSQL.setString(5, alguien.getEmail());
            sentenciaSQL.setString(6, alguien.getTipo());
            // actualizar la BD usando la sentenciaSQL con los datos del usuario
            
            baseDatos.actualizar(sentenciaSQL);            
        } catch (Exception error){
            throw new Exception ("Error al Agregar el usuario " + alguien.getId()
            + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }
    
    public void modificarUsuario() throws Exception{
        if (alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El ID del usuario es Necesario");
        }
        //armar el SQL UPDATE de forma dinamica
        String sqlUpdate = "UPDATE Usuarios "
                + "SET password =?, nombre =?, apellido=?, email=?, tipo=? "
                + "WHERE id=?";
        
        try{
            //Crear una sentencia JDBC mediante la sentencia SQL anterior
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlUpdate);
            //Pasarle los datos del usuario a la sentencia SQL
            sentenciaSQL.setString(1, alguien.getPassword());
            sentenciaSQL.setString(2, alguien.getNombre());
            sentenciaSQL.setString(3, alguien.getApellido());
            sentenciaSQL.setString(4, alguien.getEmail());
            sentenciaSQL.setString(5, alguien.getTipo());
            sentenciaSQL.setString(6, alguien.getId());
            // actualizar la BD usando la sentenciaSQL con los datos del usuario
            baseDatos.actualizar(sentenciaSQL);            
        } catch (Exception error){
            throw new Exception("Error al Actualizar el Usuario " + alguien.getId()
            + "<br/>Explicacion: " + error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }
    
    public void eliminarUsuario() throws Exception{
         if (alguien.getId() == null || alguien.getId().isEmpty()){
            throw new Exception("El ID del usuario es Necesario");
        }
        // armar el SQL DELETE de forma dinamica
        
        String sqlDelete = "DELETE FROM Usuarios "
                + "WHERE id =?";
        
        try{
            //Crear una sentencia JDBC mediante la sentencia SQL anterior
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlDelete);
            //Pasarle los datos del usuario a la sentencia SQL
            sentenciaSQL.setString(1, alguien.getId());
            // actualizar la BD usando la sentenciaSQL con los datos del usuario
            baseDatos.actualizar(sentenciaSQL);
        } catch (Exception error){
            throw new Exception("Error al Eliminar el Usuario " + alguien.getId()
            + "<br/>Explicacion: "+ error.getMessage());
        } finally {
            baseDatos.desconectar();
        }
    }
    
    
    public static Usuario iniciarSesion (String id, String password) throws Exception{
       if (id == null || id.isEmpty()|| password == null || password.isEmpty()){
            throw new Exception("El ID y el Password del Usuario son Necesarios");
        }  
       Usuario alguien; ConexionBaseDatos baseDatos = null;
       // armar el SQL SELECT de forma dinamica
       String sqlSelect = "SELECT * FROM Usuarios WHERE id =? and password =?";
       try{
           //Crear una sentencia JDBC mediante la sentencia SQL anterior
           baseDatos = new ConexionBaseDatos();
           PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
           // Pasarle los datos del usuario a la sentencia SQL
           sentenciaSQL.setString(1, id); sentenciaSQL.setString(2, password);
           // Verificar el Resultado de la consulta
           ResultSet resultado = baseDatos.consultar(sentenciaSQL);
           if (resultado.next() == true){
               alguien = new Usuario();
               alguien.setId(resultado.getString("id"));
               alguien.setPassword(resultado.getString("password"));
               alguien.setNombre(resultado.getString("nombre"));
               alguien.setApellido(resultado.getString("apellido"));
               alguien.setEmail(resultado.getString("email"));
               alguien.setTipo(resultado.getString("tipo"));
               return alguien;
           }else{
               throw new Exception("Error al Consultar el Usuario "+ id + "<br/>Explicacion: ");
           }
           
       } catch (Exception error){
           throw new Exception(error.getMessage()+ "Error en el Id o el Password estan Errados");
       } finally {
           if(baseDatos != null){
               baseDatos.desconectar();
           }
       }
    }
    
    public static Usuario consultarUsuario (String id) throws Exception{
           if (id == null || id.isEmpty()){
            throw new Exception("El ID del usuario es Necesario");
        }
        Usuario alguien; ConexionBaseDatos baseDatos = null;
        //armar el SQL SELECGT de forma dianmica
        String sqlSelect = "SELECT * FROM Usuarios WEHERE id =?";
        try{
            // Crear una sentencia JDBC mediate la sentencia SQL anterior
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            // Pasarle los datos del usuario a la sentencia SQL
            sentenciaSQL.setString(1, id);
            //Verificar el Resultado de la consulta
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            
              if (resultado.next() == true){
               alguien = new Usuario();
               alguien.setId(resultado.getString("id"));
               alguien.setPassword(resultado.getString("password"));
               alguien.setNombre(resultado.getString("nombre"));
               alguien.setApellido(resultado.getString("apellido"));
               alguien.setEmail(resultado.getString("email"));
               alguien.setTipo(resultado.getString("tipo"));
               return alguien;
           }else{
               throw new Exception("Error al Consultar el Usuario "+ id + "<br/>Explicacion: ");
           }
           
       } catch (Exception error){
           throw new Exception(error.getMessage()+ "El usuario No existe en la BD");
       } finally {
           if(baseDatos != null){
               baseDatos.desconectar();
           }
       }
    }
    
    public static Usuario[] listarTodosLosUsuarios() throws Exception {
        Usuario alguien; ConexionBaseDatos baseDatos = null;
        // armar el SQL SELECT de forma dinamica
        String sqlSelect =  "SELECT * FROM Usuarios";
        try{
           // Crear una sentencia JDBC mediate la sentencia SQL anterior
            baseDatos = new ConexionBaseDatos();
            PreparedStatement sentenciaSQL = baseDatos.crearSentencia(sqlSelect);
            // Verificar el Resultado de la consulta
            ResultSet resultado = baseDatos.consultar(sentenciaSQL);
            resultado.last(); //colocarnos en el ultimo registro del resultado
            Usuario[] listado = new Usuario[resultado.getRow()];
            resultado.beforeFirst(); // nos colocamos antes del primer registro
            while (resultado.next() == true){
               alguien = new Usuario();
               alguien.setId(resultado.getString("id"));
               alguien.setPassword(resultado.getString("password"));
               alguien.setNombre(resultado.getString("nombre"));
               alguien.setApellido(resultado.getString("apellido"));
               alguien.setEmail(resultado.getString("email"));
               alguien.setTipo(resultado.getString("tipo"));
               listado[resultado.getRow()] = alguien;
            } if (listado.length <= 0){
                throw new Exception("Error al Listar los Usuarios"
                + "<br/>Explicacion: ");
            }
            return listado;
        } catch (Exception error){
            throw new Exception(error.getMessage() +  "La BD esta vacia");
        } finally {
            if (baseDatos != null){
                baseDatos.desconectar();
            }
        }
    }

    public Usuario getAlguien() {
        return alguien;
    }

    public void setAlguien(Usuario alguien) {
        this.alguien = alguien;
    }

    public ConexionBaseDatos getBaseDatos() {
        return baseDatos;
    }

    public void setBaseDatos(ConexionBaseDatos baseDatos) {
        this.baseDatos = baseDatos;
    }
    
    
    
    
}

