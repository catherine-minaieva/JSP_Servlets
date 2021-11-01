<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en-us">
<head>
    <title>Create customer</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To create Customer set an ID, name, TaxCode and HeadOffice: </h3>
<form method="post" action="createCustomer">
    <table>
        <tbody>
        <tr>
            <td>
                <p>ID</p>
            </td>
            <td>
                <input type="text" name="id" tabindex="1"></td>
        </tr>
        <tr>
            <td>
                <p>Name</p>
            </td>
            <td>
                <input type="text" name="name" tabindex="2"></td>
        </tr>
        <tr>
            <td>
                <p>TaxCode</p>
            </td>
            <td>
                <input type="text" name="city" tabindex="3">
            </td>
        </tr>
        <tr>
            <td>
                <p>HeadOffice</p>
            </td>
            <td>
                <input type="text" name="industry" tabindex="4">
            </td>
        </tr>
        <tr>
        <tr>
        </tbody>
    </table>
    <button type="submit" class="button">Create</button>
</form>
<c:if test="${not empty errors}">
    <c:forEach items="${errors}" var="error">
        <p style="color: red">${error.field} ${error.error}</p><br>
    </c:forEach>
</c:if>
<c:if test="${not empty message}">
        <p style="color: darkslateblue">${message}</p><br>
</c:if>
</body>
</html>
