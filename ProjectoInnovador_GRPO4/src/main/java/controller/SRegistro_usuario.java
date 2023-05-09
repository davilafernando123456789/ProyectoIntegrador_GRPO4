package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.*;//

@WebServlet("/SRegistro_producto")
public class SRegistro_usuario extends HttpServlet {
    public Connection conn;
    public Statement sen;
    public ResultSet data;
    public String driver="com.mysql.cj.jdbc.Driver";
    public String cadena="jdbc:mysql://localhost/ProyectoIntegrador";///base de datos crearla asi
    public String usuario="root";
    public String clave="";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codigo = request.getParameter("codigo");
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String usuario = request.getParameter("usuario");
        String contraseña = request.getParameter("contraseña");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        conectar();
        String registro;
        try {
        	sen = conn.createStatement();
            registro = "insert into usuario(codigo,nombres, apellidos,usuario,contraseña, correo, telefono) "
            		+ "values('" + codigo + "','" + nombres + "','" + apellidos + "','" + usuario + "','"+contraseña+"','"+correo+"','"+telefono+"')";
            sen.executeUpdate(registro);
  
        }catch(SQLException e5) {
            JOptionPane.showMessageDialog(null, "error en la consulta");
        }
        response.sendRedirect("SLista_producto");
    }
    public void conectar() {
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(cadena,usuario,clave);
        }catch(ClassNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "error en el driver");
        }catch(SQLException e2) {
            JOptionPane.showMessageDialog(null, "error en el sql");
      }
    }
}

