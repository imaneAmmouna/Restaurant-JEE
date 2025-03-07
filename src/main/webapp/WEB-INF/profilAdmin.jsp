<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
<title>Profile Admin</title>
</head>
<body>


<fieldset>
    <h2>Modifier les Informations de l'Administrateur</h2>
    <form action="ModifierAdmin" method="post">
        <input type="hidden" name="admin_id" value="${admin.adminId}">
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${admin.nom}">
        <br>
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" value="${admin.prenom}">
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${admin.email}" pattern="\w+\d{4}_admin@gmail\.com" title="L'email doit être au format nom+prenom+code[4 chiffres]_admin@gmail.com">
        <br>
        <label for="password">Mot de passe:</label>
        <input type="password" id="password" name="password" value="${admin.password}">
        <br>
        <input type="submit" value="Mettre à jour">
    </form>
</fieldset>

</body>
</html>
