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
    <title>Title</title>
</head>
<body>

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
