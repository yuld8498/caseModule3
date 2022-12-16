<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07/08/2022
  Time: 12:44 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Management</title>
</head>
<body>
<h1>Create User</h1>
<h2>
<%--    <a href="product?action=users">List All Product</a>--%>
</h2>
<div>
    <form method="post">
        <table border="1" cellpadding="5">
            <caption><h2>Add new Product</h2></caption>
            <tr>
                <th>User Name: </th>
                <td>
                    <input type="text" name="productName" id="productName" size="45" value="${user.getUserName()}"/>
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="text" name="productDescription" id="productDescription" size="45" value="${user.getPassword()}"/>
                </td>
            </tr>
            <tr>
                <th>Full Name: </th>
                <td>
                    <input type="text" name="price" id="price" size="45" value="${user.getFullName()}"/>
                </td>
            </tr>
            <tr>
                <th>Age:</th>
                <td>
                    <input type="number" name="quaility" id="quaility" size="45" value="${user.getAge()}"/>
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="price" id="email" size="45" value="${user.getEmail()}"/>
                </td>
            </tr>
            <tr>
                <th>Phone: </th>
                <td>
                    <input type="text" name="price" id="phone" size="45" value="${user.getPhoneNumber()}"/>
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="price" id="address" size="45" value="${user.getAddress()}"/>
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
