/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ojekservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Asus
 */
public class HistoryWebService {
  public static String fetchUserHistory(String username) {
    JSONArray jsonArray = new JSONArray();
    
    Connection connection = DBConnection.connectDB();
    if (connection == null) {
      return "null";
    }
    String query = "SELECT * FROM order_history WHERE passengerUsername = ? AND isHide = ?";
    
    try {
      PreparedStatement SQLStatement = connection.prepareStatement(query);
      SQLStatement.setString(1, username);
      SQLStatement.setBoolean(2, false);
      ResultSet resultSet = SQLStatement.executeQuery();
      while (resultSet.next()) {
        JSONObject json = new JSONObject();        
        json.put("driverUsername", resultSet.getString("driverUsername"));
        json.put("date", resultSet.getDate("date"));
        json.put("pickingPoint", resultSet.getString("pickingPoint"));
        json.put("destination", resultSet.getString("destination"));
        json.put("rating", resultSet.getInt("rating"));
        json.put("comment", resultSet.getString("comment"));
        
        jsonArray.put(json);
      }
    } catch (SQLException | JSONException ex) {
      return ex.getMessage();
    }
    
    return jsonArray.toString();
  }
  
  public static String fetchDriverHistory(String username) {
    JSONArray jsonArray = new JSONArray();
    
    Connection connection = DBConnection.connectDB();
    String query = "SELECT * FROM order_history WHERE driverUsername = ? AND isHide = ?";
    
    try {
      PreparedStatement SQLStatement = connection.prepareStatement(query);
      SQLStatement.setString(1, username);
      SQLStatement.setBoolean(2, false);
      ResultSet resultSet = SQLStatement.executeQuery();
      while (resultSet.next()) {
        JSONObject json = new JSONObject();        
        json.put("passengerUsername", resultSet.getString("passengerUsername"));
        json.put("date", resultSet.getDate("date"));
        json.put("pickingPoint", resultSet.getString("pickingPoint"));
        json.put("destination", resultSet.getString("destination"));
        json.put("rating", resultSet.getInt("rating"));
        json.put("comment", resultSet.getString("comment"));
        
        jsonArray.put(json);
      }
    } catch (SQLException | JSONException ex) {
      return ex.getMessage();
    }
    
    return jsonArray.toString();
  }
}
