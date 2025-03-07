<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Menu avec sous-liens</title>
<style>
    .menu ul {
        list-style-type: none;
        padding: 0;
        margin: 0;
        background-color: #6a3d2a;

    }

    .menu li {
        position: relative;
        display: inline-block;
    }

    .menu a {
        text-decoration: none;
        padding: 20px 40px;
        display: block;
        color: #fffcec;
        background-color: #6a3d2a;
        font-family: Arial, sans-serif;
        font-size: 16px;
    }

    .menu a:hover {
        background-color: #de6206;
    }

    .submenu {
        display: none;
        position: absolute;
        top: 100%;
        left: 0;
        background-color: #a7512a;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        z-index: 1;
        min-width: 160px;
    }
 .submenu li {
        display: block; 
    }
    .submenu a {
        padding: 10px;
        display: block;
        color: #fffcec;
        background-color: #a7512a;
    }

    .submenu a:hover {
        background-color: #feac53;
    }

    .menu li:hover .submenu {
        display: block;
    }

    body {
        font-family: Arial, sans-serif;
        background-color: #F7EFCA;
        margin: 0;
        padding: 0;
    }
</style>

</head>
<body>
    <div class="menu" style="text-align: center;" >
        <ul>
            <li><a href="ModifierAdmin">Profil</a></li>
            <li>
                <a href="">Ajouter</a>
                <ul class="submenu">
                    <li><a href="AjouterEmploye">Employés</a></li>
                    <li><a href="AjouterMenu">Plats</a></li>
                    <li><a href="AjouterCategorie">Catégories</a></li>
                </ul>
            </li>
            <li>
                <a href="">Consulter</a>
                <ul class="submenu">
                    <li><a href="AffihcherMenus">Menu</a></li>
                    <li><a href="AfficherEmployes">Employés</a></li>
                    <li><a href="AfficherCommandes">Commandes</a></li>
                </ul>
            </li>
            <li><a href="/Gestion_Cmd_Restau/Page_depart?page=page_depart.jsp">Déconnexion</a></li>
        </ul>
    </div>
</body>
</html>
