<%-- 
    Document   : order1
    Created on : 16-Nov-2017, 00:45:55
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
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
        <h3>ORDER</h3>
        <form method="post" action="order2.jsp">
        Picking point:<input type="text" name="pick" /><br/>
        Destination:<input type="text" name="destination" /><br/>
        Preferred driver:<input type="text" name="pref" /><br/>
        <input type="submit" value="next" />
        </form>
        <a href="register.html">Register</a>
    </body>
</html>
