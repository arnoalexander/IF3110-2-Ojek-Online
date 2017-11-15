/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identityservice;

import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.sql.Timestamp;

/**
 *
 * @author Rio
 */
@WebServlet(name = "registerServlet", urlPatterns = {"/registerServlet"})
public class RegisterServlet extends HttpServlet {

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
        JSONObject js = new JSONObject();
        Connection con = DBConnection.connectDB();
        try {
            processRequest(request, response);
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String token = username + "_token";
            boolean isDriver = true;
            if (request.getParameter("isDriver") == null) {
              isDriver = false;
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long exp_t = timestamp.getTime() + 60000;
            String querySQL = "SELECT COUNT(*) AS userCount FROM members WHERE username = ?";
            PreparedStatement SQLStatement = con.prepareStatement(querySQL);
            SQLStatement.setString(1, username);
            ResultSet SQLResult = SQLStatement.executeQuery();
            SQLResult.next();
            if (SQLResult.getInt("userCount") == 1) {
                js.put("status","userExist");
            }
            else {
                con.setAutoCommit(false);
                querySQL = "INSERT INTO members "
                        + "VALUE (?,?,?,?,?,?,?,?,?,?)";
                SQLStatement = con.prepareStatement(querySQL, Statement.RETURN_GENERATED_KEYS);
                SQLStatement.setString(1,username);
                SQLStatement.setString(2,password);
                SQLStatement.setString(3,token);
                SQLStatement.setLong(4,exp_t);
                SQLStatement.setString(5,name);
                SQLStatement.setString(6,email);
                SQLStatement.setString(7,phone);
                SQLStatement.setBoolean(8,isDriver);
                SQLStatement.setInt(9,0);
                SQLStatement.setInt(10,0);
                
                SQLStatement.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                
                js.put("username", username);
                js.put("isDriver", isDriver);
                js.put("token", token);
            }
        } catch (Exception ex) {
            js.put("status",ex.getMessage());
            response.sendRedirect("register.html?error=true");
        }
        response.getWriter().println(js.toJSONString());
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
