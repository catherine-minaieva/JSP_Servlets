<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Update Project</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To update Project, provide an ID and new cost: </h3>
<form method="post" action="updateProject">
    <table>
        <tbody>
        <tr>
            <td>
                <p>ID</p>
            </td>
            <td>
                <input type="number" name="id" tabindex="1"></td>
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
                <p>Base Technology</p>
            </td>
            <td>
                <input type="text" name="baseTechnology" tabindex="3"></td>
        </tr>
        <tr>
            <td>
                <p>Creation Date</p>
            </td>
            <td>
                <input type="text" name="creationDate" tabindex="4"></td>
        </tr>
        <tr>
            <td>
                <p>Cost</p>
            </td>
            <td><input type="number" name="cost" tabindex="5"></td>
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