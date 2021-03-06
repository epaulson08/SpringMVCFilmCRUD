<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erick's Awesome Film Results</title>
</head>
<body>
	<h3>Here are your results:</h3>

	<c:forEach var="film" items="${films}">
		<br /> Film ID: ${film.id}
	<br /> Film Title: ${film.title}
	<br /> Film Description: ${film.description}
	<%-- TODO fill in other film attributes --%>

		<%-- TODO logic to fill in Actor list (foreach) --%>
		<%-- TODO logic to fill in Categories list (foreach) --%>

		<%-- TODO option to edit film  --%>
		<form action="editFilm.do">
			<button>Edit This Film</button>
			<input type="hidden" name="film" value="${film}">
		</form>
		<%-- TODO option to delete film  --%>

		<br />
	</c:forEach>

</body>
</html>