<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All People</title>
</head>
<body>
<form method = "post" action = "navigationServlet">
<table>
<c:forEach items="${requestScope.allPeople}" var="currentPerson">
<tr>
	<td><input type="radio" name="personId" value="${currentPerson.personId}"></td>
	<td>${currentPerson.firstName}</td>
	<td>${currentPerson.lastName}</td>
	<td>${currentPerson.birthDate}</td>	
</tr>

</c:forEach>

</table>
<input type = "submit" value = "edit" name="doThisToPerson">
<input type = "submit" value = "delete" name="doThisToPerson">
<input type = "submit" value = "add" name="doThisToPerson">
</form>
</body>
</html>