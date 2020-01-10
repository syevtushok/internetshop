<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bucket" scope="request" type="mate.academy.internetshop.model.Bucket"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<p>All items:</p>
<table border="1">
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
                <a href="${pageContext.request.contextPath}/deleteFromBucket?bucket_id=${bucket.bucketId}&item_id=${item.itemId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="index">Main page</a></p>
<p><a href="completeOrder?bucket_id=${bucket.bucketId}">Complete order</a></p>
</body>
</html>
