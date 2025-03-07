
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/includes/headerServeur.jsp" %>
 <script>
        function showAlert(message) {
            alert(message);
        }
    </script>
<form action="" method="post">
<input type="text" name="nom" value="${utilisateur.nom}" placeholder="Nom" required>
    <input type="text" name="prenom" value="${utilisateur.prenom}" placeholder="Prénom" required>
    <input type="text" name="telephone" value="${utilisateur.telephone}" placeholder="telephone" required>
    <c:if test="${!empty sessionScope.serveur_id }">
    <input type="email" name="email" value="${utilisateur.email}" pattern="\w+\d{4}_serveur@gmail\.com" title="L'email doit être au format nom+prenom+code[4 chiffres]_serveur@gmail.com" placeholder="Email" required>
    </c:if>
    <c:if test="${!empty sessionScope.livreur_id }">
    <input type="email" name="email" value="${utilisateur.email}" pattern="\w+\d{4}_livreur@gmail\.com" title="L'email doit être au format nom+prenom+code[4 chiffres]_livreur@gmail.com" placeholder="Email" required>
    </c:if>
    <input type="text" name="password" placeholder="Nouveau mot de passe" value="${utilisateur.password}" required>
    <button type="submit">Modifier</button>
</form>
</body>
</html>


