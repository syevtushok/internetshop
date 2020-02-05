<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<div class="container">
    <p>All items:</p>
    <table class="table table-bordered table-hover">
        <tr>
            <th>Item Name</th>
            <th>Price</th>
            <th>Delete Item</th>
        </tr>
        <c:forEach var="item" items="${bucket.items}">
            <tr>
                <td>
                    <c:out value="${item.name}"/>
                </td>
                <td>
                    <c:out value="${item.price}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/deleteFromBucket?bucket_id=${bucket.bucketId}&item_id=${item.itemId}">DELETE</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="${pageContext.request.contextPath}/index">Return to Main Page</a></p>
    <p><a href="completeOrder?bucket_id=${bucket.bucketId}">Complete order</a></p>
</div>
</body>
</html>
