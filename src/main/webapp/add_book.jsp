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
    <title>Add Book</title>
</head>
<body class="p-3">
<jsp:include page="navbar.jsp" /><br><br>
<h1> Enter the new book details. </h1>
<form action="BookData" method="Post" class="p-3 border border-dark rounded">
    <div class="form-group pt-2">
        <label for="title"> Title </label>
        <input type ="text" name="title" id="title" class="form-control" placeholder="Title">
    </div>
    <div class="form-group pt-2">
        <label for="isbn"> ISBN </label>
        <input type ="text" name="isbn" id="isbn" class="form-control" placeholder="ISBN">
    </div>
    <div class="form-row pt-2">
        <div class="col pt-2">
            <label for="lastName"> Author Last Name </label>
            <input type ="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name">
        </div>
        <div class="col pt-2">
            <label for="firstName"> Author First Name </label>
            <input type ="text" name="firstName" id="firstName" class="form-control" placeholder="First Name">
        </div>
    </div>

    <div class="form-group pt-2">
        <label for="edition"> Edition Number </label>
        <input type="text" name="edition" id="edition" class="form-control" placeholder="Edition">
    </div>
    <div class="form-group pt-2">
        <label for="copyright"> Copyright </label>
        <input type="text" name="copyright" id="copyright" class="form-control" placeholder="1786">
    </div>
    <div class="pt-3">
        <input type="submit" value="submit" class="btn btn-success">
    </div>

</form>

</body>
</html>
