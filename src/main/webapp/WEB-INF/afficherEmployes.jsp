<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="css/table.css">
<title>Liste des employes</title>

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
    <jsp:include page="menu.jsp" />
 <fieldset>
        <legend>Liste des Serveurs</legend>
        <table>
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Téléphone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="serveur" items="${serveurs}">
                    <tr>
                        <td>${serveur.nom}</td>
                        <td>${serveur.prenom}</td>
                        <td>${serveur.email}</td>
                        <td>${serveur.telephone}</td>
                         <td>
                        <form action="ModifierServeur" method="post">
                            <input type="hidden" name="id" value="${serveur.serveurId}">
                            <input type="submit" value="Modifier">
                        </form>
                        <form action="SupprimerServeur" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce serveur ?');">
                            <input type="hidden" name="id" value="${serveur.serveurId}">
                            <input type="submit" value="Supprimer">
                        </form>
                    </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </fieldset>

    <fieldset>
        <legend>Liste des Livreurs</legend>
        <table>
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Téléphone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="livreur" items="${livreurs}">
                    <tr>
                        <td>${livreur.nom}</td>
                        <td>${livreur.prenom}</td>
                        <td>${livreur.email}</td>
                        <td>${livreur.telephone}</td>
                         <td>
                        <form action="ModifierLivreur" method="post">
                            <input type="hidden" name="id_liv" value="${livreur.livreurId}">
                            <input type="submit" value="Modifier">
                        </form>
                        <form action="SupprimerLivreur" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce livreur ?');">
                            <input type="hidden" name="id_liv" value="${livreur.livreurId}">
                            <input type="submit" value="Supprimer">
                        </form>
                    </td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </fieldset>
</body>
</html>
