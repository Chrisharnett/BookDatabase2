<%@ page import="com.example.servletproject.Author" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.example.servletproject.Book" %><%--
  Created by IntelliJ IDEA.
  User: christopherharnett
  Date: 2023-10-18
  Time: 4:17 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <title>Authors</title>
</head>
<body class="p-3">
<jsp:include page="navbar.jsp" />
<div class="p-4">
  <% LinkedList<Author> authorList = (LinkedList<Author>) request.getAttribute("authorList"); %>
  <%for( Author author: authorList) { %>
    <h2><%=author.printAuthorName()%></h2>
    <p> Title(s):
    <% for (Book b: author.getBookList()){ %>
       <%= "| " + b.getTitle() %>
    <% }; %>
    </p><hr>
  <% } %>
</div>

</body>
</html>