<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.internetshop.model.Item>"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Items</title>
</head>
<body>
Welcome to All Items page!<br>
All Items :
<table border="1">
    <tr>
        <th>ID</th>
        <th>Item Name</th>
        <th>Price</th>
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
</body>
</html>
