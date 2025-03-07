<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Bienvenue dans notre restaurant</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        /* Palette de couleurs */
        :root {
            --main-color: #6a3d2a;       /* Rouge-brun */
            --accent-color: #feac53;     /* Orang� dor� */
            --secondary-color: #a7512a;  /* Terre cuite */
            --background-color: #fffcec; /* Cr�me claire */
            --hover-color: #f57618;      /* Orange vif */
            --highlight-color: #a86a38;  /* Brun dor� */
            --soft-color: #d2ae93;       /* Beige doux */
            --footer-color: #784726;     /* Marron fonc� */
        }

        body {
            background-color: var(--background-color);
            color: var(--main-color);
        }

        h1 {
            text-align: center;
            font-size: 45px;
            color: var(--secondary-color);
            margin-top: 50px;
            font-family: 'Lobster', cursive;
            text-transform: uppercase;
        }

  .welcome-container {
    padding: 30px auto;  /* R�duire le padding pour moins d'espace int�rieur */
    background-color: var(--soft-color);
    border-radius: 15px;
    box-shadow: 0 12px 25px rgba(0, 0, 0, 0.1);
    max-width: 1350px;  /* Augmenter la largeur pour rendre la container plus large */
    margin: 20px auto;
    text-align: center;
}


        .welcome-container p {
            font-size: 20px;
            color: var(--main-color);
            font-style: italic;
            line-height: 1.6;
            margin-bottom: 25px;
        }

        .photo-gallery {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 40px;
            flex-wrap: wrap;
        }

        .photo-gallery img {
            width: 50%;
            max-width: 150px;
            height: 200px;
            object-fit: cover;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.15);
        }


        .footer {
            background-color: var(--footer-color);
            color: #fffcec;
            padding: 25px 0;
            text-align: center;
            position: fixed;
            bottom: 0;
            width: 100%;
            font-size: 14px;
        }

        .footer a {
            color: #fffcec;
            text-decoration: none;
            margin: 0 15px;
        }

        .footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%@ include file="/includes/header_client.jsp" %>

    <!-- Contenu principal de la page -->
    <div class="welcome-container">
        <h1>Bienvenue dans notre restaurant!</h1>
        <p>Merci de nous avoir choisis pour vivre une exp�rience culinaire inoubliable. Nous sommes ravis de vous offrir un voyage gastronomique o� chaque plat est une oeuvre d'art.</p>
        <p>Changez d'air en visitant notre restaurant, l'endroit parfait pour vous d�tendre, vous d�fouler et d�couvrir notre savoureuse s�lection de plats, directement depuis notre site.</p>
</div>
        <!-- Galerie de photos du restaurant -->
        <div class="photo-gallery">
            <img src="images/restau1.jpg" alt="Restaurant int�rieur 1">
            <img src="images/restau2.jpg" alt="Restaurant int�rieur 2">
            <img src="images/restau3.jpg" alt="Restaurant int�rieur 3">
            <img src="images/restau4.jpg" alt="Restaurant int�rieur 4">
            <img src="images/restau5.jpg" alt="Restaurant int�rieur 5">
            <img src="images/restau6.jpg" alt="Restaurant int�rieur 6">
            <img src="images/restau7.jpg" alt="Restaurant int�rieur 7">
        </div>

<p><br></p>
    <div class="footer">
        <p>� 2025 Restaurant - Tous droits r�serv�s</p>
        <p><a href="/contact.jsp">Contact</a> | <a href="/about.jsp">� propos</a></p>
    </div>
</body>
</html>
