/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Asus
 */
public class UserServlet extends HttpServlet {

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
    
    String token = request.getParameter("token");
    Connection connection = DBConnection.connectDB();
    String query = "SELECT * FROM members WHERE token = ?";
    JSONObject json = new JSONObject();
    
    try {
      PreparedStatement SQLStatement = connection.prepareStatement(query);
      SQLStatement.setString(1, token);
      ResultSet resultSet = SQLStatement.executeQuery();
      if (resultSet.next()) {
        json.put("valid", 1);
        json.put("username", resultSet.getString("username"));
        json.put("name", resultSet.getString("name"));
        json.put("email", resultSet.getString("email"));
        json.put("phone", resultSet.getString("phone"));
        json.put("isDriver", resultSet.getBoolean("isDriver"));
        json.put("stars", resultSet.getInt("stars"));
        json.put("nVoters", resultSet.getInt("nVoters"));
      } else {
        // do nothing
      }
    } catch (Exception ex) {
      // do nothing
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
