<%-- 
    Document   : profile
    Created on : 16-Nov-2017, 00:43:21
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <%@include file="validateToken.jsp" %>
        hi,
        <b><%
            out.print(session.getAttribute("username"));  
        %></b>
        <br>
        <a href="logout.jsp?">Logout</a>
        <br>
        <br>
        
        <a href="order1.jsp">ORDER</a>        
        <br>
        <a href="history_user.jsp">HISTORY</a>
        <br>
        <a href="profile.jsp">MY PROFILE</a>
        <br>
        
        <br>
        <h3>MY PROFILE</h3>
        <br>
        <%
        final String URL_STR = "http://localhost:8080/Identity_Service/UserServlet";
	final String PARAMS = "token=" + session.getAttribute("token");
        URL url = new URL(URL_STR);
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.setRequestMethod("POST");
	
	connection.setDoOutput(true);
	OutputStream output = connection.getOutputStream();
	output.write(POST_PARAMS.getBytes());
	output.flush();
	output.close();
	
	responseCode = connection.getResponseCode();
	if (responseCode == HttpURLConnection.HTTP_OK) { //success
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String inputLine;
		StringBuffer resp = new StringBuffer();
	
		while ((inputLine = in.readLine()) != null) {
			resp.append(inputLine);
		}
		in.close();
		
		JSONObject json = new JSONObject(resp.toString());
		out.print(json.toString());
	} else {
          out.print("{}");
        }
        %>
        <br>
        <br>
        <a href="editprofile.jsp">Edit Profile</a><br>
        <%
            if ((Boolean) session.getAttribute("isDriver")) {
              out.print("Preffered Location<br>");
              out.print(">>  Query ke preffered location webservicedb");
            }
        %>
        
        
    </body>
</html>
