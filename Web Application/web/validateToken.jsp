<%@page import="java.io.OutputStream"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>

<%
        final String POST_URL = "http://localhost:8080/Identity_Service/TokenValidation";
	final String POST_PARAMS = "token="+session.getAttribute("token");
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
            try {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer resp = new StringBuffer();
	
		while ((inputLine = in.readLine()) != null) {
			resp.append(inputLine);
		}
		in.close();
		
		JSONObject json = new JSONObject(resp.toString());
		if (json.has("isValid")) {
                    //do nothing, stay in this page
		} else {
                    response.sendRedirect("logout.jsp");
		}
            } catch (Exception ex) {
              response.sendRedirect("logout.jsp");
            }
	} else {
            response.sendRedirect("logout.jsp");
        }
        //out.println("yay valid <br>");
%>
