<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Film</title>
</head>
<body>
	DELETE FILM PAGE
	<br /> You have deleted ${film.title}.

	<form action="home.do">
	<button>Go Home</button>
	</form>
<%--
		<button name="confirm" value="confirmDelete">Yes,
			DELETE</button>
	<!--  	<input type="hidden" name="film" value="${film}"> -->
		<button name="confirm" value="cancelDelete">CANCEL</button>
	</form>

	<%--
	<c:choose>
	<c:when test = "${confirm = "confirmDelete"}">
	
	// Delete the film
		DELETED ${film.name}
	</c:when>
	
	<c:otherwise>
	Delete canceled. <a href="home.do">Return to homepage</a>
	</c:otherwise>
	
	</c:choose>
	 --%>
</body>
</html>