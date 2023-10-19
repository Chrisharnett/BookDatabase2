<%--
  Created by IntelliJ IDEA.
  User: edsto
  Date: 2023-10-12
  Time: 2:46 p.m.
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
<jsp:include page="navbar.jsp" /><br><br>
<form action="BookData" method="Post">
    Title: <input type ="text" name="title"><br>
    ISBN: <input type ="text" name="isbn"><br>
    Author (Last Name, First Name): <input type ="text" name="author"><br>
    Edition: <input type="text" name="edition"><br>
    Copyright: <input type="text" name="copyright"><br>
    <input type="submit" value="submit">
</form>

</body>
</html>
