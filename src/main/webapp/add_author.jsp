<%--
  Created by IntelliJ IDEA.
  User: christopherharnett
  Date: 2023-10-18
  Time: 3:01 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous" defer></script>
    <title>Add Author</title>
</head>
<body class="p-3">
<jsp:include page="navbar.jsp" /><br><br>
<h1> Enter the author's name.</h1>
<form action="AuthorData" method="Post" class="p-3 border border-dark rounded">
    <div class="form-group">
        <div class="pt-2">
            <label for="lastName"> Last Name </label>
            <input type ="text" name="lastName" id="lastName" placeholder="Last Name"><br>
        </div>
        <div class="pt-2">
            <label for="firstName"> First Name </label>
            <input type ="text" name="firstName" id="firstName" placeholder="First Name"><br>
        </div>
    </div>
    <input type="submit" value="submit" class="btn btn-success">
</form>
</body>
</html>
