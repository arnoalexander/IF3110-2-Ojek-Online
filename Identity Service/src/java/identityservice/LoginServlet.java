/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;

import org.json.JSONObject;

public class LoginServlet extends HttpServlet {

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
    response.setContentType("application/json");
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
    
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    Connection connection = DBConnection.connectDB();
    String query = "SELECT * FROM members WHERE username = ? AND password = ?";
    JSONObject json = new JSONObject();
    Timestamp expire = new Timestamp(System.currentTimeMillis());
    
    try {
      PreparedStatement SQLStatement = connection.prepareStatement(query);
      SQLStatement.setString(1, username);
      SQLStatement.setString(2, password);
      ResultSet resultSet = SQLStatement.executeQuery();
      if (resultSet.next()) {
        String token = resultSet.getString("token");
        int isDriver = resultSet.getInt("isDriver");
        
        /*JSON WRITING*/
        json.put("token", token);
        json.put("isDriver", isDriver);
        
        query = "UPDATE members SET expired_t = ? WHERE username = ?;";
        SQLStatement = connection.prepareStatement(query);
        SQLStatement.setLong(1, expire.getTime() + 60000);
        SQLStatement.setString(2, username);
        SQLStatement.executeUpdate();
      } else {
        response.sendRedirect("login.html?error=true");
      }
    } catch (Exception ex) {
      try {
        json.put("status",ex.getMessage());
      } catch (Exception ex1) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex1);
      }
      response.sendRedirect("login.html?error=true");
    }
    
    response.getWriter().println(json.toString());
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
