<%--
  Created by IntelliJ IDEA.
  User: christopherharnett
  Date: 2023-10-19
  Time: 3:50 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous" defer></script>
    <title>Author Added</title>
  </head>
  <body class="p-3">
    <jsp:include page="navbar.jsp" /><br><br>
    <h1>New Author Added!</h1>
    <hr>
    <% String lastName = (String) request.getAttribute("lastName");
      String firstName = (String) request.getAttribute("firstName");
    %>
    <h1> <%= lastName + ", " + firstName %> </h1>
  </body>
</html>
