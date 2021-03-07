<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
UPDATE FILM PAGE

Please type in your updates:
<form action="updateConfirmed.do" method="POST">
<input type="hidden" name="filmId" value="${film.id}">
<ul>
<li>Film Title: ${film.title} &nbsp &nbsp <input type="text" name="title"> </li>
<%--
<li>Film Description: ${film.description}</li>
<li>Film Rental Duration: ${film.rentalDuration}</li>
<li>Film Rental Rate: ${film.rentalRate}</li>
<li>Film Length: ${film.length}</li>
<li>Film Replacement Cost: ${film.replacementCost}</li>
<li>Film Rating: ${film.rating}</li>
<li>Film Special Features: ${film.specialFeatures}</li>
<li>Film Release Year: ${film.releaseYear}</li>
<li>Film Language: ${film.languagePlainText}</li>
--%>
</ul>
</form>
</body>
</html>