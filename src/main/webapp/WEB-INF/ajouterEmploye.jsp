<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
<title>Insert title here</title>
<script>
        function showAlert(message) {
            alert(message);
        }
    </script>
</head>
<body>
<c:if test="${not empty param.message}">
        <script>
            showAlert("${fn:escapeXml(param.message)}");
        </script>
    </c:if>
<form action="AjouterEmploye" method="post">
    <fieldset>
        <legend>Ajouter Employé</legend>
        <label>Nom:</label>
        <input type="text" name="nom" required><br>

        <label>Prénom:</label>
        <input type="text" name="prenom" required><br>



        <label>Téléphone:</label>
        <input type="text" name="telephone" required><br>

        <label>Rôle:</label>
        <select name="role">
            <option value="serveur">Serveur</option>
            <option value="livreur">Livreur</option>
        </select><br>

        <input type="submit" value="Ajouter">
    </fieldset>
</form>

</body>
</html>
