<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Livreur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #F7EFCA; /* Couleur de fond */
            color: #6a3d2a; /* Couleur du texte */
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #de6206;
            text-align: center;
            margin-top: 50px;
            font-size: 36px;
            font-weight: bold;
        }

        header {
            background-color: #a7512a; /* Couleur de fond de l'en-tête */
            padding: 20px;
            text-align: center;
        }

        header a {
            text-decoration: none;
            color: #fffcec;
            font-size: 18px;
            margin: 0 15px;
        }

        header a:hover {
            color: #feac53; /* Couleur au survol */
        }

        .container {
            padding: 30px;
            background-color: #fffcec;
            border-radius: 8px;
            margin-top: 20px;
        }

        .btn {
            background-color: #fb9447; /* Couleur des boutons */
            color: #fffcec;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .btn:hover {
            background-color: #f57618;
        }

        .footer {
            background-color: #784726;
            color: #fffcec;
            text-align: center;
            padding: 20px;
            position: absolute;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>
<%@ include file="/includes/headerLivreur.jsp" %>
    <div class="container">
        <h1>Bienvenue, ${sessionScope.prenom} ${sessionScope.nom}</h1>
        <p>Vous êtes connecté en tant que livreur. Vous pouvez gérer vos commandes ici.</p>
    </div>

    <footer class="footer">
        <p>&copy; 2025 Gestion des Commandes Restaurant</p>
    </footer>
</body>
</html>
