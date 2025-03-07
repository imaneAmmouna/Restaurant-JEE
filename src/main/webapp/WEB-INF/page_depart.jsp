<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
<!-- linking google fonts for icons -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@24,400,0,0&icon_names=arrow_forward" />
<!-- linking swiperJS CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<title>Restaurant Palette des Saveurs</title>
<style>
/* importing google fonts - inter*/
@import url('https://fonts.googleapis.com/css2?family=Inter:opsz,wght@14..32,100..900&display=swap');

* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "inter", sans-serif;
}

/* Style pour le header */
header {
    display: flex;
    justify-content: space-around; /* Espacement égal entre les boutons */
    align-items: center; /* Alignement centré des éléments */
    background-color: #F7EFCA; /* Couleur de fond du header */
}

/* Style des boutons */
header button{
   padding: 10px 20px;
   font-size: 16px;
   border: none;
   border-radius: 5px;
   cursor: pointer;
   background-color: #fb9447;
   color: #fff;
}

header button:hover {
    background-color: #feac53;
}

/* Description du restaurant */
.restaurant-description {
    width: 80%;
    max-width: 800px;
    margin: 40px auto; /* Center the content */
    padding: 20px;
    background-color: #fffcec;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    text-align: center;
}

.restaurant-description h2 {
    font-size: 30px;
    color: #6a3d2a;
    margin-bottom: 15px;
    font-family: 'Lobster', cursive; 
}

.restaurant-description p {
    font-size: 18px;
    color: #8a705d;
    line-height: 1.6;
    margin-bottom: 20px;
    font-family: 'Arial', sans-serif;
    font-style: italic;
}

.swiper-button-prev{
	color:#a7512a;
}

.swiper-button-next{
	color:#a7512a;
}

.body{
	display: flex;
	align-items: center;
	justify-content: center;
}

.card-wrapper{
	max-width: 1100px;
	margin: 0 60px 35px;
	padding: 20px 10px;
	overflow: hidden;
}

.card-list .card-item{
	list-style: none;
}

.card-list .card-item .card-link{
	user-select: none;
	display: block;
	background: #fff;
	padding: 18 px;
	border-radius: 12px;
	text-decoration: none;
	border: 2px solid transparent;
	box-shadow: 0 10px 10px rgba(0, 0, 0, 0.05);
	transition: 0.2s ease;
	max-width: 305px;
	max-height: 27nh0px;
}

.card-list .card-item .card-link:hover {
    border-color: #a7512a;
}

.card-list .card-link .card-image{
	width: 100%;
	height: auto;
	aspect-ratio: 16 /9;
	object-fit: cover;
	border-radius: 10px;
	max-width: 300px;
	max-height: 168px;
}

.card-list .card-link .card-title {
    font-size: 1.19rem;
    color: #feac53;
    font-weight: 600;
}
.description{
	color:#f57618;
}


/* Description about */
.about-description {
    padding: 20px;
    margin: 20px;
    background-color: #f9a961;
    border-radius: 5px;
}

.about-description p {
    font-family: 'Arial', sans-serif;
    font-size: 16px;
    color: #6a3d2a;
    line-height: 1.6;
    text-align: justify;
}

/* Footer */
.footer {
    background-color: #784726;
    color: #fff;
    padding: 20px 0;
    text-align: center;
}

.footer .footer-content {
    max-width: 1000px;
    margin: 0 auto;
}

.footer p {
    margin-bottom: 10px;
    font-size: 14px;
}

.footer .footer-links {
    margin-top: 10px;
}

.footer .footer-links a {
    color: #fff;
    text-decoration: none;
    margin: 0 15px;
    font-size: 14px;
}

.footer .footer-links a:hover {
    text-decoration: underline;
}

.logo {
    max-height: 70px;
}
.titre-categ{
	color:#784726;
}
</style>
</head>
<body>
	<header>
		<img src="logo.png" alt="Logo" class="logo">
		<div class="buttons">
        	<button onclick="window.location.href='/Gestion_Cmd_Restau/Page_depart?page=connexion.jsp'"><b>Connexion</b></button>
        	<button onclick="window.location.href='/Gestion_Cmd_Restau/Page_depart?page=creer_compte.jsp'"><b>Créer un compte</b></button>
    	</div>
    </header>
    
    <!-- Histoire et ambiance -->
    <div class="restaurant-description">
        <h2>Bienvenue au Restaurant "Palette des Saveurs"</h2>
        <p>
            Un lieu où la passion de la cuisine rencontre une ambiance chaleureuse. 
            Nous offrons une expérience culinaire unique, alliant des saveurs traditionnelles et modernes.
        </p>
    </div>
    
    <%
    // Connexion à la base de données
    String url = "jdbc:mysql://localhost:3306/restaurantdb";
    String user = "root";
    String password = "mysql40";

    Connection connection = null;
    PreparedStatement statementCategories = null;
    PreparedStatement statementMenus = null;
    ResultSet resultSetCategories = null;
    ResultSet resultSetMenus = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);

        // Récupérer les catégories
        String queryCategories = "SELECT * FROM Categorie";
        statementCategories = connection.prepareStatement(queryCategories);
        resultSetCategories = statementCategories.executeQuery();

        while (resultSetCategories.next()) {
            String categorieNom = resultSetCategories.getString("nom");
            int categorieId = resultSetCategories.getInt("categorie_id");

            // Récupérer les menus pour la catégorie actuelle
            String queryMenus = "SELECT * FROM Menu WHERE categorie_id = ?";
            statementMenus = connection.prepareStatement(queryMenus);
            statementMenus.setInt(1, categorieId);
            resultSetMenus = statementMenus.executeQuery();
%>
<div class="body">
    <div class="container swiper">
        <h2 class="titre-categ"><%= categorieNom %> :</h2>
        <div class="card-wrapper">
            <ul class="card-list swiper-wrapper">
                <% 
                    // Afficher chaque menu pour cette catégorie
                    while (resultSetMenus.next()) { 
                        String menuNom = resultSetMenus.getString("nom");
                        String description = resultSetMenus.getString("description");
                        double prix = resultSetMenus.getDouble("prix");
                %>
                <li class="card-item swiper-slide">
                    <a href="#" class="card-link">
                        <img alt="card image" src="images/restau7.jpg" class="card-image">
                        <h2 class="card-title"><%= menuNom %> - <%= prix %> MAD</h2>
                        
                    </a>
                </li>
                <% } %>
            </ul>
            <div class="swiper-button-prev"></div>
            <div class="swiper-button-next"></div>
        </div>
    </div>
</div>
<%
            resultSetMenus.close();
            statementMenus.close();
        }
    } catch (Exception e) {
        out.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
    } finally {
        try {
            if (resultSetMenus != null) resultSetMenus.close();
            if (statementMenus != null) statementMenus.close();
            if (resultSetCategories != null) resultSetCategories.close();
            if (statementCategories != null) statementCategories.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            out.println("Erreur lors de la fermeture des ressources : " + e.getMessage());
        }
    }
%>

	
	<!-- Engagement envers la qualité -->
    <div class="about-description">
        <p>
            Nous sommes fiers de servir des plats préparés avec les ingrédients les plus frais et les plus savoureux, 
            sélectionnés avec soin par nos chefs talentueux.
        </p>
    </div>
    
    <footer class="footer">
    <div class="footer-content">
        <p>&copy; 2025 Restaurant Palette des Saveurs. Tous droits réservés.</p>
        <div class="footer-links">
            <a href="#">Mentions légales</a>
            <a href="#">Politique de confidentialité</a>
            <a href="#">Contact</a>
        </div>
    </div>
</footer>
	
	<!-- linking swiperHS script -->
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	
	<script>
		new Swiper('.card-wrapper', {
		  loop: true,
		  spaceBetween: 5,

		  // pagination bullets
		  pagination: {
		    el: '.swiper-pagination',
		    clickable: true,
		    dynamicBullets: true
		  },

		  // Navigation arrows
		  navigation: {
		    nextEl: '.swiper-button-next',
		    prevEl: '.swiper-button-prev',
		  },
		  
		  breakpoints: {
			  0: {
				  slidesPerView: 1
			  },
			  768: {
				  slidesPerView: 2
			  },
			  1024: {
				  slidesPerView: 3
			  }
		  }
		}); 
	</script>
	
</body>
</html>