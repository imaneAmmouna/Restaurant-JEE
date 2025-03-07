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

<form action="AjouterCategorie" method="post">
<fieldset>
<legend>Ajouter Categorie</legend>
<label>Nom Categorie:</label>
<input type="text" name="nomCat">
<br>
<input type="submit" name="ok" value="Ajouter">
</fieldset>
</form>
<%
if(request.getParameter("ok")!=null){
	Categorie categorie = (Categorie) request.getAttribute("catg");
	CategorieDAO inserer = new CategorieDAO();
	inserer.insererCategorie(categorie.getNomCateg());
}
%>
</body>
</html>
