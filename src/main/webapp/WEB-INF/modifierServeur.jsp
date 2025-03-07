<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
<title>Insert title here</title>
</head>
<body>
<body>
    <form action="SauvegarderModificationServeur" method="post">
        <input type="hidden" name="id" value="${serveur.serveurId}">
        <label>Nom:</label>
        <input type="text" name="nom" value="${serveur.nom}">
        <label>Prénom:</label>
        <input type="text" name="prenom" value="${serveur.prenom}">
        <label>Email:</label>
        <input type="email" name="email" value="${serveur.email}" pattern="\w+\d{4}_serveur@gmail\.com" title="L'email doit être au format nom+prenom+code[4 chiffres]_serveur@gmail.com">
        <label>Téléphone:</label>
        <input type="text" name="telephone" value="${serveur.telephone}">
        <input type="submit" value="Enregistrer">
    </form>
</body>


</body>
</html>
