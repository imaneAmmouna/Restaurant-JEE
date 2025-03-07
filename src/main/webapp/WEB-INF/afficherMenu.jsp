<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/table.css">
<title>Insert title here</title>
<jsp:include page="menu.jsp" />
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
<fieldset>
    <legend>Liste des Catégories</legend>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="categorie" items="${categories}">
                <tr>
                    <td>${categorie.nomCateg}</td>
                    <td>
                        <form action="ModifierCategorie" method="get">
			                <input type="hidden" name="id_c" value="${categorie.categorieId}">
			                <input type="submit" value="Modifier">
			            </form>
                        <form action="SupprimerCategorie" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer cette catégorie ?');">
                            <input type="hidden" name="id_c" value="${categorie.categorieId}">
                            <input type="submit" value="Supprimer">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</fieldset>

<!-- Table pour les menus -->
<fieldset>
    <legend>Liste des Menus</legend>
    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Catégorie</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="menu" items="${menus}">
                <tr>
                    <td>${menu.nomMenu}</td>
                    <td>${menu.description}</td>
                    <td>${menu.prixMenu}</td>
                    <td>${menu.nomCategorie}</td>
                    <td>
						<form action="ModifierMenu" method="get">
			                <input type="hidden" name="id_m" value="${menu.menuId}">
			                <input type="submit" value="Modifier">
			            </form>
                        <form action="SupprimerMenu" method="post" onsubmit="return confirm('Êtes-vous sûr de vouloir supprimer ce menu ?');">
                            <input type="hidden" name="id" value="${menu.menuId}">
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
