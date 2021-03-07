<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	UPDATE FILM PAGE Please type in your updates:
	<form action="updateConfirmed.do" method="POST">
		<input type="hidden" name="filmId" value="${film.id}">
		<%-- TODO: update this languagePlainText--%>
		<input type="hidden" name="languagePlainText" value="${film.languagePlainText}">
		
		
		<ul>
			<li>Film Title: ${film.title} &nbsp &nbsp <input type="text"
				name="title">
			</li>
			<li>Film Description: ${film.description} &nbsp &nbsp <input type="text"
				name="description">
			</li>
			<li>Film Rental Duration: ${film.rentalDuration} &nbsp &nbsp <input type="text"
				name="rentalDuration">
			</li>
			<li>Film Rental Rate: ${film.rentalRate} &nbsp &nbsp <input type="text"
				name="rentalRate">
			</li>
			<li>Film Length: ${film.length} &nbsp &nbsp <input type="text"
				name="length">
			</li>
			<li>Film Replacement Cost: ${film.replacementCost} &nbsp &nbsp <input type="text"
				name="replacementCost">
			</li>
			<li>Film Rating: ${film.rating} &nbsp &nbsp <input type="text"
				name="rating">
			</li>
			<li>Film Special Features: ${film.specialFeatures} &nbsp &nbsp <input type="text"
				name="specialFeatures">
			</li>
			<li>Film Release Year: ${film.releaseYear} &nbsp &nbsp <input type="text"
				name="releaseYear">
			</li>
			<%-- TODO: make this work --%>
			<%-- 
			<li>Film Language: ${film.languagePlainText} &nbsp &nbsp <input type="text"
				name="languagePlainText">
			</li>
			 --%>
		</ul>
		<input type="submit" value="Submit Updates">
	</form>
</body>
</html>