<%-- 
    Document   : order2
    Created on : 16-Nov-2017, 00:46:05
    Author     : Asus
--%>

<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Select Driver</title>
    </head>
    <body>
        <%@include file="validateToken.jsp" %>
        hi,
        <b><%
            out.print(session.getAttribute("username"));  
        %></b>
        <br>
        <a href="logout.jsp">Logout</a>
        <br>
        <br>
        
        <a href="order1.jsp">ORDER</a>        
        <br>
        <a href="history_user.jsp">HISTORY</a>
        <br>
        <a href="profile.jsp">MY PROFILE</a>
        <br>
        
        <br>
        <form method="post" action="order2.jsp">
            <input type="hidden" name="pick" value="<% out.println(request.getParameter("pick")); %>"/>
            <input type="hidden" name="destination" value="<% out.println(request.getParameter("destination")); %>"/>
            <input type="hidden" name="pref" value="<% out.println(request.getParameter("pref")); %>"/>
            <h3>Preferred Drivers</h3>
            <%
              //JSONArray json = OjekWebClient.fetchPreferredDriver(request.getParameter("pref"));
              ojekclient.OjekWebService service = ojekclient.OjekWebService_Service.;
            %>
            <h3>Other Drivers</h3>
            Nothing to display<br>
        </form>
        
        
    <%-- start web service invocation --%><hr/>
    <%
    try {
	ojekclient.OjekWebService_Service service = new ojekclient.OjekWebService_Service();
	ojekclient.OjekWebService port = service.getOjekWebServicePort();
	 // TODO initialize WS operation arguments here
	java.lang.String preferredUsername = "";
	// TODO process result here
	java.lang.String result = port.fetchPreferredDriver(preferredUsername);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    <%-- start web service invocation --%><hr/>
    <%
    try {
	ojekclient.OjekWebService_Service service = new ojekclient.OjekWebService_Service();
	ojekclient.OjekWebService port = service.getOjekWebServicePort();
	 // TODO initialize WS operation arguments here
	java.lang.String preferredUsername = "";
	// TODO process result here
	java.lang.String result = port.fetchPreferredDriver(preferredUsername);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>
    </body>
</html>
