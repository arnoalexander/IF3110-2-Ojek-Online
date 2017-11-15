<%@page import="java.io.OutputStream"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%
	final String POST_URL = "http://localhost:8080/Identity_Service/RegisterServlet";
	String POST_PARAMS;
        if (request.getParameter("isDriver") == null) {
            POST_PARAMS = "name="+ request.getParameter("name") +"&" + 
                            "username="+ request.getParameter("username") +"&" +
                            "email="+ request.getParameter("email") +"&" +
                            "password="+ request.getParameter("password") +"&" +
                            "phone="+ request.getParameter("phone");
        } else {
            POST_PARAMS = "name="+ request.getParameter("name") +"&" + 
                            "username="+ request.getParameter("username") +"&" +
                            "email="+ request.getParameter("email") +"&" +
                            "password="+ request.getParameter("password") +"&" +
                            "phone="+ request.getParameter("phone") +"&" +
                            "isDriver="+ request.getParameter("isDriver");
        }
	URL obj = new URL(POST_URL);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("POST");
	
	con.setDoOutput(true);
	OutputStream os = con.getOutputStream();
	os.write(POST_PARAMS.getBytes());
	os.flush();
	os.close();
	
	int responseCode = con.getResponseCode();
	if (responseCode == HttpURLConnection.HTTP_OK) { //success
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer resp = new StringBuffer();
	
		while ((inputLine = in.readLine()) != null) {
			resp.append(inputLine);
		}
		in.close();
		
		JSONObject json = new JSONObject(resp.toString());
		if (json.has("token")) {
                        session.setAttribute("username", json.get("username"));
			session.setAttribute("token", json.get("token"));
                        session.setAttribute("isDriver", json.get("isDriver"));
			if ((Boolean)json.get("isDriver")) {
				response.sendRedirect("profile.jsp");
			} else {
				response.sendRedirect("order1.jsp");
			}
		} else {
			response.sendRedirect("register.html?error=true");
		}
	} else {
          response.sendRedirect("register.html?error=true");
	}
	
%>