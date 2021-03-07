<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Confirmed</title>
</head>
<body>
	<c:choose>
		<c:when test="${film != null}">
		Updated title to: ${film.title}
		</c:when>
		<c:otherwise>
		There was an error updating your file.
		</c:otherwise>
	</c:choose>


</body>
</html>