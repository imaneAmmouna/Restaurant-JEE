<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<style>
    * {
        padding: 0;
        margin: 0;
    }

    body {
        font-family: Arial, sans-serif;
    }

    header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        background: #6a3d2a;
        padding: 0 50px;
    }

    .logo {
        max-height: 62px;
        padding-left: 50px;
    }

    .navbar-list {
        list-style: none;
        display: flex;
        margin: 0;
    }

    .navbar-list .navbar-item {
        display: inline-block;
        position: relative;
    }

    .navbar-list .navbar-item a {
        display: block;
        padding: 20px 25px;
        color: #e3ceb5;
        text-decoration: none;
        text-align: center;
        font-size: 20px;
    }

    .navbar-list .navbar-item a:hover {
        color: #f9a961;
        background: #fffcec;
    }

    .navbar-list .navbar-item a.active {
        background: #fffcec;
        color: #f9a961;
    }

    main {
        padding: 20px;
        text-align: center;
    }

    button {
        font-size: 16px;
        margin: 10px;
        padding: 10px 20px;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        background-color: #007BFF;
        color: white;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>
    <header>
        <img src="logo.png" alt="Logo" class="logo">
        <ul class="navbar-list">
            <li class="navbar-item"><a href="ServletServeurConsultation"><b>Consulter les Commandes</b></a></li>
            <li class="navbar-item"><a href="ModifierServeurInfos"><b>Modifier le Mot de Passe</b></a></li>
            <li class="navbar-item"><a href="/Gestion_Cmd_Restau/Page_depart?page=page_depart.jsp"><b>Se déconnecter</b></a></li>
        </ul>
    </header>


    <script>
        // Récupère le chemin d'URL actuel
        const activePage = window.location.pathname;

        // Sélectionne tous les liens de la barre de navigation
        const navLinks = document.querySelectorAll('.navbar-item a');

        // Parcourt tous les liens et ajoute la classe 'active' si le lien correspond à la page actuelle
        navLinks.forEach(link => {
            if (link.href.includes(activePage)) {
                link.classList.add('active');
            }
        });
    </script>

