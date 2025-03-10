/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.inicio.servlets;
import java.sql.*;
import java.io.IOException;
/*import java.io.PrintWriter;*/
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
@WebServlet(name = "iniciosesionservlet", urlPatterns = {"/login"})
public class iniciosesionservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet 
     * @throws jakarta.servlet.ServletException 
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* recibe los valores ingresados para realizar la confirmacion*/
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("contraseña");
        
        try {/* validar que el driver mysql existe*/
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(iniciosesionservlet.class.getName()).log(Level.SEVERE, null, ex);
        }
         /* ***** nombramiento de variables ****** */
            String url =  "jdbc:mysql://localhost:3306/loginservlet";
            Connection conexion;
            Statement statement;
            ResultSet rs;

            /*System.out.println("conectandoo...");*/
/* ***** metodo try, catch que realiza la conexion y caso contrario muestra mensaje de error ****** */
        try {
            conexion = DriverManager.getConnection(url,"root","");
            statement = conexion.createStatement();
                rs = statement.executeQuery("SELECT * FROM `usuario` WHERE `usuario` = '"+usuario+"' AND `contraseña` = '"+password+"'");
                /* valida si existen los daatos ingresados*/
                if (rs.next()){
                    request.getSession().setAttribute("usuario", usuario);
                    response.sendRedirect("panel.jsp");
                }else{
                    response.sendRedirect("index.html");
                }
        }catch (SQLException ex) {
            Logger.getLogger(iniciosesionservlet.class.getName()).log(Level.SEVERE, null, ex);
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
