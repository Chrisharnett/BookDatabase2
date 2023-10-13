<%@ page import="java.util.LinkedList" %>
<%@ page import="com.example.servletproject.Bicycle" %>

<%--
  Created by IntelliJ IDEA.
  User: edsto
  Date: 2023-10-13
  Time: 10:11 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% LinkedList<Bicycle> bicycleList = (LinkedList<Bicycle>) request.getAttribute("bicycleList");
    for( Bicycle bike: bicycleList) {
        out.println("<h2> Name of bike:" + bike.name + "</h2>");
        out.println("<h3> Speeds:" + bike.speeds + "</h3>");
        out.println("<br>");
    }
%>

</body>
</html>
