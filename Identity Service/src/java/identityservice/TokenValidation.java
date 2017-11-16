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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.weld.servlet.SessionHolder;
import org.json.JSONObject;

/**
 *
 * @author Asus
 */
public class TokenValidation extends HttpServlet {
  
  public static void zerofyExpiredT(String token) {
    Connection connection = DBConnection.connectDB();
    String query = "UPDATE members SET expired_t = ? WHERE token = ?";
    PreparedStatement SQLStatement;
    try {
      SQLStatement = connection.prepareStatement(query);
      SQLStatement.setLong(1, 0);
      SQLStatement.setString(2, token);
      SQLStatement.executeUpdate();
    } catch (Exception ex) {
      Logger.getLogger(TokenValidation.class.getName()).log(Level.SEVERE, null, ex);
    }     
  }

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
        Timestamp timenow = new Timestamp(System.currentTimeMillis());
        long timenow_ms = timenow.getTime();
        if (timenow_ms < resultSet.getLong("expired_t")) {
          query = "UPDATE members SET expired_t = ? WHERE token = ?";
          SQLStatement = connection.prepareStatement(query);
          SQLStatement.setLong(1, timenow_ms + 60000);
          SQLStatement.setString(2, token);
          SQLStatement.executeUpdate();
          json.put("isValid", 1);
        } else {
          json.put("isExpired", 1);
          response.sendRedirect("/Web_Application/logout.jsp");
        }
      } else {
        json.put("notFound", 1);
        response.sendRedirect("/Web_Application/logout.jsp");
      }
    } catch (Exception ex) {
      response.sendRedirect("/Web_Application/logout.jsp");
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
