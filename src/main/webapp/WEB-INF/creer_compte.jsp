<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création de Compte</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 500px;
        }
        
        .form-container form {
    display: flex;
    flex-direction: column;
    align-items: center;
}

        .form-container h1 {
            text-align: center;
            margin-bottom: 20px;
            color:#a86a38;
        }

        .form-container label {
            font-size: 14px;
            margin-bottom: 6px;
            display: block;
            text-align: left;
    		width: 75%;
    		color:#a86a38;
        }

        .form-container input {
            width: 75%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        .form-container button {
            width: 80%;
            padding: 10px;
            background-color: #a86a38;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        .form-container button:hover {
            background-color: #fb9447;
        }

        .form-container a {
            display: block;
            text-align: center;
            margin-top: 10px;
            font-size: 14px;
            text-decoration: none;
            color: #a86a38;
        }

        .form-container a:hover {
            color: #fb9447;
        }
    </style>
</head>
<body>

    <div class="form-container">
        <h1>Créer un Compte</h1>
        <form method="POST">
        	<input type="hidden" name="action" value="creer_compte">
            <label for="nom"><b>Nom :</b></label>
            <input type="text" id="nom" name="nom" required>

            <label for="prenom"><b>Prénom :</b></label>
            <input type="text" id="prenom" name="prenom" required>
            
            <label for="telephone"><b>Téléphone :</b></label>
            <input type="text" id="telephone" name="telephone" required pattern="(\+212\d{9}|\d{10})">

            <label for="email"><b>Email :</b></label>
            <input type="email" id="email" name="email" required pattern="^(?![A-Za-z]+[0-9]+_(serveur|livreur|admin)@gmail\.com$).+$">

            <label for="password"><b>Mot de passe :</b></label>
            <input type="password" id="password" name="password" required pattern="^(?!Ser[A-Za-z0-9!@#$%^&*()_+={}|:;,.<>?/-]+veuR$)(?!Liv[A-Za-z0-9!@#$%^&*()_+={}|:;,.<>?/-]+reuR$).+$">

            <button type="submit"><b>Créer un compte</b></button>
        </form>
        <p>Vous avez déjà un compte ?<a href="Page_depart?page=connexion.jsp"> Connectez-vous</a></p>
    </div>
    
    <!-- alert pour un erreur lors d'ajout d'un compte -->
    <% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <script>
        alert("<%= errorMessage %>");
    </script>
<% 
    }
%>

</body>
</html>
