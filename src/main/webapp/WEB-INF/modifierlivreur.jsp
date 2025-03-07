<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
<title>Modifier</title>
</head>
<body>
<form action="SauvegarderModificationLivreur" method="post">
        <input type="hidden" name="id" value="${livreur.livreurId}">
        <label>Nom:</label>
        <input type="text" name="nom" value="${livreur.nom}">
        <label>Prénom:</label>
        <input type="text" name="prenom" value="${livreur.prenom}">
        <label>Email:</label>
        <input type="email" name="email" value="${livreur.email}"pattern="\w+\d{4}_livreur@gmail\.com" title="L'email doit être au format nom+prenom+code[4 chiffres]_livreur@gmail.com">
        <label>Téléphone:</label>
        <input type="text" name="telephone" value="${livreur.telephone}">
        <input type="submit" value="Enregistrer">
    </form>
</body>
</html>
