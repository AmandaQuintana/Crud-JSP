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
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

/**
 *
 * @author Dereck
 */
public class ConexionBaseDatos {
    protected String driver = "com.mysql.jdbc.Driver";
    protected String nombreIPServidorBD = "localhost";
    protected String url = "jdbc:mysql://";
    protected int puertoServidorBD = 3306;
    protected String usuarioBD = "root";
    protected String passwordUsuarioBD = "";
    protected String nombreBD = "mibasedatos";
    protected Connection conexion;
    protected PreparedStatement sentencia;
    protected ResultSet filasConsulta;
    
    //constructores
    public ConexionBaseDatos () throws Exception{
        url = url+nombreIPServidorBD +":"+puertoServidorBD+"/"+nombreBD;
        this.conectar();
    }
    
    public ConexionBaseDatos(String driver, String servidor, String url,
            String usuarioBD, String passwordUsuarioBD, String nombreBD)
            throws Exception
    {
       this.driver = driver;
       this.nombreIPServidorBD = servidor;
       this.url = url;
       this.usuarioBD = usuarioBD;
       this.passwordUsuarioBD = passwordUsuarioBD;
       this.nombreBD = nombreBD;
       this.conectar();  
    }
    
    public void conectar() throws Exception{
        try{
            Class.forName(driver);
        }
        catch (ClassNotFoundException ex){
            throw new Exception("Error de Driver "+ ex.getMessage());
        }
        try{
            conexion = DriverManager.getConnection(url,usuarioBD,passwordUsuarioBD);
        }
        catch (SQLException ex){
            throw new Exception("Error de Conexion \n Codigo:"
            + ex.getErrorCode()+ " Explicacion:"+ex.getMessage());
        }
    }
    
    public int actualizar(PreparedStatement sentencia) throws Exception {
        try{
            int res = sentencia.executeUpdate();
            return res;
        }
        catch( SQLException ex){
            throw new SQLException ("Error al ejecutar sentencia BD Conexion \n Codigo:"
            + ex.getErrorCode() + "Explicacion:" +ex.getMessage());
        }
    }
    
    public ResultSet consultar (PreparedStatement sentencia) throws Exception{
        try{
            ResultSet filasBD = sentencia.executeQuery();
            return filasBD;
        }
        catch  (SQLException ex) {
            throw new SQLException("Error al ejecutar sentencia BD Conexion"
                    + ex.getMessage());
        }
    }
    
    public void desconectar(){
        try{
            conexion.close();
        } catch (SQLException ex){
            conexion = null;
        }
    }
    
    public PreparedStatement crearSentencia(String sql) throws Exception{
        try{
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            return sentencia;
        }
        catch (SQLException ex){
            throw new SQLException("Error de sentencia DB \n Codigo:"
                    +ex.getErrorCode()+ " Explicacion:" +ex.getMessage());
        }
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getNombreIPServidorBD() {
        return nombreIPServidorBD;
    }

    public void setNombreIPServidorBD(String nombreIPServidorBD) {
        this.nombreIPServidorBD = nombreIPServidorBD;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPuertoServidorBD() {
        return puertoServidorBD;
    }

    public void setPuertoServidorBD(int puertoServidorBD) {
        this.puertoServidorBD = puertoServidorBD;
    }

    public String getUsuarioBD() {
        return usuarioBD;
    }

    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    public String getPasswordUsuarioBD() {
        return passwordUsuarioBD;
    }

    public void setPasswordUsuarioBD(String passwordUsuarioBD) {
        this.passwordUsuarioBD = passwordUsuarioBD;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public PreparedStatement getSentencia() {
        return sentencia;
    }

    public void setSentencia(PreparedStatement sentencia) {
        this.sentencia = sentencia;
    }

    public ResultSet getFilasConsulta() {
        return filasConsulta;
    }

    public void setFilasConsulta(ResultSet filasConsulta) {
        this.filasConsulta = filasConsulta;
    }
    
    
}
