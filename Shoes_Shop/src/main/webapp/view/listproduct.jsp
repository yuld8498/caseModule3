<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="product?action=create">Add New Product</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Product</h2></caption>
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Product Description</th>
            <th>Price</th>
            <th>Quaility</th>
            <th>Type</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="product" items="${listProduct}">
            <tr>
                <td style="display: block"><c:out value="${product.productID}"/></td>
                <td><c:out value="${product.productName}"/></td>
                <td><c:out value="${product.productDescription}"/></td>
                <td><c:out value="${product.price}"/></td>
                <td><c:out value="${product.quaility}"/></td>
                <td style="display:none;"><c:out value="${product.typeID}"/></td>
                <td><c:out value="${product.typeName}"/></td>
                <td>
                    <a href="/product?action=update&productID=${product.productID}">Edit</a>
                    <a href="/product?action=delete&productID=${product.productID}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${currentPage!=1}">
        <td><a href="product?page=${currentPage-1}">Previous</a></td>
    </c:if>
    <table border="1px" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPage}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq 1}">
                        <td><a href="product?page=${i}">${i}</a></td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="product?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <c:if test="${currentPage<noOfPage}">
        <td><a href="product?page=${currentPage+1}">Next</a></td>
    </c:if>
</div>
</body>
</html>