<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.internetshop.model.Order>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<div class="container">
    <p>All orders</p>

    <table class="table table-bordered">
        <tr>
            <th>Order ID</th>
            <th>Items</th>
            <th>Delete Order</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.orderId}"/>
                </td>
                <td>
                    <table class="table table-bordered">
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                        </tr>
                        <c:forEach var="item" items="${order.items}">
                            <tr>
                                <td>
                                    <c:out value="${item.name}"/>
                                </td>
                                <td>
                                    <c:out value="${item.price}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/servlet/deleteUserOrder?order_id=${order.orderId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="${pageContext.request.contextPath}/allItems">Return to Items</a></p>
    <p><a href="${pageContext.request.contextPath}/index">Return to Main Page</a></p>
</div>
</body>
</html>
