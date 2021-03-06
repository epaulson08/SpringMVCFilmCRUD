<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ErickApp - Results</title>
</head>
<body>

	<c:choose> 
		<c:when test="${! empty film }"> 
	
			<h3>Here is the information you requested:</h3>
			<br /> 
			<h4>Result:</h4>
	
		<ul>
			<br /> 

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

			<li>Film Categories: <c:forEach var="cat" items="${film.categories}">
			${cat.name} &nbsp &nbsp &nbsp
			</c:forEach>

			<%-- TODO: logic for commas after all actors but last --%>
			<li>Film Actors: <c:forEach var="actor" items="${film.actorsList}">
			${actor.firstName} ${actor.lastName} &nbsp &nbsp &nbsp 
			</c:forEach>
	
		</ul>
		
		<form action="updateFilm.do">
			<button>Edit This Film</button>
			<input type="hidden" name="film" value="${film}">
		</form>
		
		<form action="deleteFilm.do">
			<button>Delete This Film</button>
			<input type="hidden" name="film" value="${film}">
		</form>
		
	
		</c:when>
		<c:otherwise>
			<h3>No film found</h3>
		</c:otherwise>

	</c:choose>	
	
	
	<%-- TODO fill in other film attributes --%>
	
	<%-- TODO logic to fill in Actor list (foreach) --%>
	<%-- TODO locig to fill in Categories list (foreach) --%>

<%-- TODO option to edit film  --%>
<%-- TODO option to delete film  --%>

</body>
</html>