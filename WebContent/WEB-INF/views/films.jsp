<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Search Results</title>
</head>
<body>

	<h3>These films matched your search:</h3>
	<c:forEach var="film" items="${films}">
		<ul>
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
			<li>Film Categories: <c:forEach var="cat"
					items="${film.categories}">
			${cat.name} &nbsp &nbsp &nbsp
			</c:forEach> <%-- TODO: logic for commas after all actors but last --%>
			<li>Film Actors: <c:forEach var="actor"
					items="${film.actorsList}">
			${actor.firstName} ${actor.lastName} &nbsp &nbsp &nbsp 
			</c:forEach>
		</ul>


		<form action="updateFilm.do" , method="GET">
			<button>Edit This Film</button>
			<input type="hidden" name="filmId" value="${film.id}">
		</form>

		<form action="deleteFilm.do" method="POST">
			<button>Delete This Film</button>
			<input type="hidden" name="filmId" value="${film.id}">
		</form>

		<br />
	</c:forEach>
	<br />
	<form action="home.do">
		<button>Return Home</button>
	</form>


</body>
</html>