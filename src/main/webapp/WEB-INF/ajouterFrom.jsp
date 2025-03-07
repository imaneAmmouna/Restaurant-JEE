<%@page import="bean_marwa.MenuDAO"%>
<%@page import="bean_marwa.Menu"%>
<%@page import="bean_marwa.CategorieDAO"%>
<%@page import="bean_marwa.Categorie"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/form.css">
 <%@ include file="menu.jsp" %>
</head>
<body>

<form action="AjouterMenu" method="post">
        <fieldset>
            <legend>Ajouter Menu</legend>
            <label>Nom:</label>
            <input type="text" name="nomMenu" required>
            <label>Description:</label>
            <textarea name="description" required></textarea>
            <label>Prix:</label>
            <input type="text" name="prixMenu" required>
            <label>Categorie:</label>
            <select name="categorieId" required>
                <c:forEach var="categorie" items="${categories}">
                    <option value="${categorie.categorieId}">${categorie.nomCateg}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Ajouter">
        </fieldset>
    </form>

</body>
</html>
