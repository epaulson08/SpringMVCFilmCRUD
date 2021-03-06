<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	uri="http://jakarta.apache.org/taglibs/standard/permittedTaglibs"
	prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Film Site</title>
</head>
<body>
	<h1 style="color: pink; background-color: blue">Welcome To Erick's
		Film Site</h1>
	<br>
	<h3>You can: </h3>
	
	<%-- TODO: ADD HREFS --%>
	<a href="searchById.html">Search for film by ID</a>
	<a href="WEB-INF/views/searchByKeyword.jsp">Search for film by keyword</a>
	<a href="WEB-INF/views/addFilm.jsp">Add a film to the database</a>
	<a href="WEB-INF/views/deleteFilm.jsp">Delete a film from the database</a>
	<%--                  --%>

</body>
</html>