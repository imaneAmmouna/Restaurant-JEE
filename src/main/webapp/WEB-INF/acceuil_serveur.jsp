<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Serveur</title>
</head>
<body>
<%@ include file="/includes/headerServeur.jsp" %>
<h1>Bienvenue, ${sessionScope.prenom} ${sessionScope.nom}</h1>
</body>
</html>