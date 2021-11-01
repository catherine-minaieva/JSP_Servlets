<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Update Developer</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To update Developer set an ID, name, age and new salary: </h3>
<form method="post" action="updateDeveloper">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Developer ID:</p>
            </td>
            <td><input type="number" name="id"></td>
        </tr>
        <tr>
            <td>
                <p>Name:</p>
            </td>
            <td><input type="number" name="name"></td>
        </tr>
        <tr>
            <td>
                <p>Age:</p>
            </td>
            <td><input type="number" name="age"></td>
        </tr>
        <tr>
            <td>
                <p>New Salary: </p>
            </td>
            <td><input type="number" name="salary"></td>
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