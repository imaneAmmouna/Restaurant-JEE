<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
<title>Insert title here</title>
</head>
<body>
<h2>Modifier Menu</h2>
    <form action="ModifierMenu" method="post">
        <input type="hidden" name="id_m" value="${menu.menuId}">
        Nom: <input type="text" name="nom" value="${menu.nomMenu}" required><br>
        Description: <textarea name="description" required>${menu.description}</textarea><br>
        Prix: <input type="text" step="0.01" name="prix" value="${menu.prixMenu}" required><br>
        Catégorie:
        <select name="categorieId" required>
            <c:forEach var="categorie" items="${categories}">
                <option value="${categorie.categorieId}" <c:if test="${categorie.categorieId == menu.categorieId}">selected</c:if>>${categorie.nomCateg}</option>
            </c:forEach>
        </select><br>
        <input type="submit" value="Enregistrer">
    </form>

</body>
</html>
