<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Emprunter une revue</title>
</head>
<body>
	<h1>Emprunter une revue</h1>
	<form method="post">
		<label for="cne">CNE :</label>
		<select name="cne" required>
		<c:forEach var="etudiant" items="${etudiantList}">
			<option value="${ etudiant.CNE }">
				${ etudiant.nom }
			</option>
		</c:forEach>
		</select>
		<label for="parution">Parution de la revue :</label>
		<input type="text" name="parution" required><br>
		<label for="codeRes">Code de Resource :</label>
		<input type="text" name="codeRes" required><br>
		<label for="titre">Titre :</label>
		<input type="text" name="titre" required><br>
		<input type="submit" value="emprunter">
	</form>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
</body>
</html>