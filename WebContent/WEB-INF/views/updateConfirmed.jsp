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
		<h3>Update Complete!</h3>
			<li>Film ID: ${film.id}</li>
			<li>Film Title: ${film.title}</li>
			<li>Film Description: ${film.description}</li>
			<li>Film Rental Duration: ${film.rentalDuration}</li>
			<li>Film Rental Rate: ${film.rentalRate}</li>
			<li>Film Length: ${film.length}</li>
			<li>Film Replacement Cost: ${film.replacementCost}</li>
			<li>Film Rating: ${film.rating}</li>
			<li>Film Special Features: ${film.specialFeatures}</li>
			<li>Film Release Year: ${film.releaseYear}</li>
			<li>Film Language: ${film.languagePlainText}</li>

		</c:when>
		<c:otherwise>
		There was an error updating your file.
		</c:otherwise>
	</c:choose>


</body>
</html>