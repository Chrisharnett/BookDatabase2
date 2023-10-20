<%@ page import="com.example.servletproject.Book" %>
<%@ page import="com.example.servletproject.Author" %><%--
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
  <title>Book Added</title>
</head>
<body class="p-3">
<jsp:include page="navbar.jsp" /><br><br>
  <h1>New Book Added!</h1>
  <hr>
  <% Book newBook = (Book) request.getAttribute("newBook"); %>
  <h1> <%= newBook.getTitle() %></h1>
  <p> Author: <% for (Author author: newBook.getAuthorList()){ %>
                  <%= author.printAuthorName() %>
                  <% } %></p>
  <p> ISBN: <%= newBook.getISBN() %> </p>
  <p> Edition: <%= newBook.getEdition() %> </p>
  <p> Copyright: <%= newBook.getCopyright() %> </p>
</body>
</html>
