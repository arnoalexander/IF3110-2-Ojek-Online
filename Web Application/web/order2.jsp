<%-- 
    Document   : order2
    Created on : 16-Nov-2017, 00:46:05
    Author     : Asus
--%>

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
            Nothing to display<br>
            <h3>Other Drivers</h3>
            Nothing to display<br>
        </form>
        
        
    </body>
</html>
