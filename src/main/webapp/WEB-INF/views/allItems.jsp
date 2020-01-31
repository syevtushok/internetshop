<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
</head>
<body>
<div class="container">
    <h3>Welcome to All Items page!</h3>
    <p>All Items :</p>

    <table class="table table-bordered table-hover">
        <tr>
            <th>ID</th>
            <th>Item Name</th>
            <th>Price</th>
            <th>Add to bucket</th>
            <th>Delete Item</th>
        </tr>
        <c:forEach var="item" items="${items}">
            <tr>
                <td>
                    <c:out value="${item.itemId}"/>
                </td>
                <td>
                    <c:out value="${item.name}"/>
                </td>
                <td>
                    <c:out value="${item.price}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/addItemToBucket?item_id=${item.itemId}">Add in
                        bucket</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/deleteItem?item_id=${item.itemId}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="servlet/bucket">Bucket</a></p>
    <p><a href="index">Main page</a></p>
</div>
</body>
</html>
