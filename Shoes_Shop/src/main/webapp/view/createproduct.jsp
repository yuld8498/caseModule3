<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/08/2022
  Time: 12:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<h1>Create Product</h1>
<h2>
    <a href="product?action=users">List All Product</a>
</h2>
<div>
    <form method="post">
        <table border="1" cellpadding="5">
            <caption><h2>Add new Product</h2></caption>
            <tr>
                <th>product Name: </th>
                <td>
                    <input type="text" name="productName" id="productName" size="45" value="${product.getProductName()}"/>
                </td>
            </tr>
            <tr>
                <th>Product Description: </th>
                <td>
                    <input type="text" name="productDescription" id="productDescription" size="45" value="${product.getProductDescription()}"/>
                </td>
            </tr>
            <tr>
                <th>Price: </th>
                <td>
                    <input type="text" name="price" id="price" size="45" value="${product.getPrice()}"/>
                </td>
            </tr>
            <tr>
                <th>Quaility:</th>
                <td>
                    <input type="number" name="quaility" id="quaility" size="45" value="${product.getQuaility()}"/>
                </td>
            </tr>
            <tr>
                <th>Type:</th>
                <td>
                    <select name="typeID">
                        <c:forEach items="${applicationScope.listType}" var="type">
                            <option value="${type.getTypeID()}">${type.getTypeName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="save">
                </td>
            </tr>
        </table>
    </form>
</div>
<div>
    ${errors}
</div>
</body>
</html>
