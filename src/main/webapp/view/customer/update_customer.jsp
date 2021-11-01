<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Update Customer</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To update Customer set an ID, new name, tax code and head office: </h3>
<form method="post" action="updateCustomer">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Customer ID:</p>
            </td>
            <td><input type="number" name="id"></td>
        </tr>
        <tr>
            <td>
                <p>New Name: </p>
            </td>
            <td><input type="text" name="name"></td>
            <td>
                <p>New Tax Code: </p>
            </td>
            <td><input type="text" name="taxCode"></td>
            <td>
                <p>New headOffice: </p>
            </td>
            <td><input type="text" name="headOffice"></td>
        </tr>
        </tbody>
    </table>
    <button type="submit" class="button">Update</button>
</form>
<c:if test="${not empty message}">
    <p style="color: darkslateblue">${message}</p><br>
</c:if>
</body>
</html>