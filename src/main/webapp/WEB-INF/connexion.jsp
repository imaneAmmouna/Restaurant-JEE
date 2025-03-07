<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>

<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        height: 100vh;
        display: flex;
    }

    .left-section {
        background: linear-gradient(to bottom, #784726, #a7512a, #d2ae93);
        color: white;
        width: 40%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        clip-path: polygon(0 0, 100% 0, 80% 100%, 0 100%);
    }

    .left-section img {
        width: auto;
        height: auto;
        margin-bottom: 20px;
    }

    .right-section {
        width: 50%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }

    .login-container {
        width: 90%; /* Reduced width */
        max-width: 350px; /* Further reduce the max width */
        height: 300px; /* Fixed height */
        padding: 20px;
        border: 1px solid transparent; /* Transparent border */
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        background-color: #fff;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }

    .login-container h1 {
        margin-bottom: 20px;
        color: #784726;
        font-size: 28px;
        text-align: center;
    }

    .login-container input[type="text"],
    .login-container input[type="password"] {
        width: 93%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .login-container button {
        background-color: #784726;
        color: white;
        border: none;
        padding: 10px 20px;
        cursor: pointer;
        border-radius: 4px;
        font-size: 16px;
        width: 100%;
    }

    .login-container button:hover {
        background-color: #fb9447;
    }

    .login-container a {
        display: block;
        margin-top: 10px;
        text-decoration: none;
        color: #784726;
        font-size: 14px;
        text-align: center;
    }

    .login-container a:hover {
        color:#fb9447;
    }
</style>
</head>
<body>
    <!-- Section gauche -->
    <div class="left-section">
        <img src="images/nomlogo.png" alt="Logo">
    </div>
    <!-- Section droite -->
    <div class="right-section">
        <div class="login-container">
            <h1>Connexion</h1>
            <form method="post">
            	<input type="hidden" name="action" value="connexion">
                <input type="text" name="email" placeholder="Email" required>
                <input type="password" name="password" placeholder="Mot de passe" required>
                <button type="submit">Se connecter</button>
            </form>
            <a href="Page_depart?page=creer_compte.jsp">Créer un compte</a>
            <a href="Page_depart?page=reset_password.jsp">Mot de passe oublié ?</a>
        </div>
    </div>
    
    <!-- alert pour un compte bien ajouté -->
    <% 
    String successMessage = (String) request.getAttribute("successMessage");
    if (successMessage != null) {
%>
    <script>
        alert("<%= successMessage %>");
    </script>
<% 
    }
%>
</body>
</html>
