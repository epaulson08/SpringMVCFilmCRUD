<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eric(k)'s Amazing FilmApp</title>
</head>
<body>
<h1>Film Finder</h1>
	<form action="findId.do" method="POST">
		<label for="id">Input film ID:</label><input type="text" name="id" />
		<input type="submit" value="Submit" />
	</form>
</body>
</html>