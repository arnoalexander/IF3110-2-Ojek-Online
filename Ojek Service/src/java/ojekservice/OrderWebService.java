/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ojekservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Asus
 */
public class OrderWebService {
  public static String fetchPreferredDriver(String preferredUsername) {
    JSONArray jsonArray = new JSONArray();
    
    try {
      final String POST_URL = "http://localhost:8080/Identity_Service/DriverServlet";
      URL obj = new URL(POST_URL);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("POST");

      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) { //success
              BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
              String inputLine;
              StringBuffer resp = new StringBuffer();

              while ((inputLine = in.readLine()) != null) {
                      resp.append(inputLine);
              }
              in.close();

              JSONArray response = new JSONArray(resp.toString());
              for (int i=0; i<response.length(); i++) {
                JSONObject json = (JSONObject) response.get(i);
                if (json.getString("username").equals(preferredUsername)) {
                  jsonArray.put(json);
                }
              }
      }
    } catch (IOException | JSONException ex) {
      // do nothing
    }
    
    return jsonArray.toString();
  }
  
  public static String fetchOtherDriver(String preferredUsername) {
    JSONArray jsonArray = new JSONArray();
    
    try {
      final String POST_URL = "http://localhost:8080/Identity_Service/DriverServlet";
      URL obj = new URL(POST_URL);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();
      con.setRequestMethod("POST");

      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) { //success
              BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
              String inputLine;
              StringBuffer resp = new StringBuffer();

              while ((inputLine = in.readLine()) != null) {
                      resp.append(inputLine);
              }
              in.close();

              JSONArray response = new JSONArray(resp.toString());
              for (int i=0; i<response.length(); i++) {
                JSONObject json = (JSONObject) response.get(i);
                if (!json.getString("username").equals(preferredUsername)) {
                  jsonArray.put(json);
                }
              }
      }
    } catch (IOException | JSONException ex) {
      // do nothing
    }
    
    return jsonArray.toString();
  }
}
