<%@ page import="java.util.LinkedList" %>
<%@ page import="com.example.servletproject.Book" %>
<%@ page import="com.example.servletproject.Author" %>

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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <title>Title</title>
</head>
<body>
<jsp:include page="navbar.jsp" />
<% LinkedList<Book> bookList = (LinkedList<Book>) request.getAttribute("bookList");
    for( Book book: bookList) {
        out.println("<h2> Title: " + book.getTitle() + "</h2>");
        out.println("<h3> Author(s): ");
        for (Author author: book.getAuthorList()){
            out.println(author.printAuthorName() + "; ");
        };
        out.println("<h3> ISBN: " + book.getISBN() + "</h3>");
        out.println("<h3> Edition Number: " + book.getEdition() + "</h2>");
        out.println("<h3> Copyright: " + book.getCopyright() + "</h2>");
        out.println("<br>");
    }
%>

</body>
</html>
