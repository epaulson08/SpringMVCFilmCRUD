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
			<!--<li><br /> Film Categories: ${film.categories}  -->
	
		</ul>
	
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