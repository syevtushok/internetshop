<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>AllUsers</title>
</head>
<body>
<div class="jumbotron jumbotron-fluid">
    <div class="container">
        <h1>Hello, ${greeting}, welcome to users page</h1>
        <p>All users:</p>
        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Delete</th>
            </tr>
            </thead>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <c:out value="${user.surname}"/>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger text-uppercase">
                            <a href="${pageContext.request.contextPath}/servlet/deleteUser?user_id=${user.id}">Delete User</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p><a href="${pageContext.request.contextPath}/index">Return to Main Page</a></p>
    </div>
</div>
</body>
</html>
