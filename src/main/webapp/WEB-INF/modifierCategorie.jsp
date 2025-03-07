<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
<title>Insert title here</title>
</head>
<body>
<h2>Modifier Catégorie</h2>
    <form action="ModifierCategorie" method="post">
        <input type="hidden" name="id_c" value="${categorie.categorieId}">
        Nom: <input type="text" name="nom" value="${categorie.nomCateg}" required><br>
        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
