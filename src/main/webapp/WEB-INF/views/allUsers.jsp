<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.internetshop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>AllUsers</title>
</head>
<body>
<h1>Hello, ${greeting}, welcome to users page</h1>
<p>All users:</p>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Delete</th>
    </tr>
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
                <a href="${pageContext.request.contextPath}/servlet/deleteUser?user_id=${user.id}">Delete User</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="index">Main page</a></p>
</body>
</html>
