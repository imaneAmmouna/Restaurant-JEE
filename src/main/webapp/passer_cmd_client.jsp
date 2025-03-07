<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Passer une commande</title>
    <style>
        body {
            background-color: #F7EFCA;
            color: #784726;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            color: #6a3d2a;
            margin-top: 20px;
        }

        h3 {
            color: #de6206;
            cursor: pointer;
            margin: 10px 0;
        }

        /* Classes spécifiques pour les menus et les plats */
        .menu-list {
            list-style: none;
            padding: 0;
        }

        .menu-item {
            background-color: #fffcec;
            padding: 10px;
            margin: 5px 0;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .menu-item input[type="checkbox"] {
            margin-right: 10px;
        }

        .menu-item input[type="number"] {
            width: 50px;
            padding: 5px;
            margin-left: 10px;
            border: 1px solid #a5765e;
            border-radius: 3px;
        }

        form {
            width: 80%;
            max-width: 900px;
            margin: 20px auto;
            background-color: #fffcec;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        button {
            background-color: #fb9447;
            border: none;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #f57618;
        }

        button:active {
            background-color: #d2ae93;
        }

        #category {
            margin: 10px 0;
        }

        .category-menu {
            display: none;
            margin-left: 20px;
        }

        .category-menu .menu-list {
            padding-left: 20px;
        }

        .category-menu .menu-item {
            background-color: #f9a961;
        }

        .toggle-category {
            color: #a7512a;
            cursor: pointer;
            font-weight: bold;
        }

        .toggle-category:hover {
            color: #6a3d2a;
        }

        .address-container {
            margin-top: 20px;
        }

        .address-container input {
            width: 100%;
            padding: 10px;
            border: 1px solid #a5765e;
            border-radius: 5px;
            font-size: 16px;
        }
    </style>
</head>
<body>
	<%@ include file="/includes/header_client.jsp" %>

    <form action="GestionCommande" method="POST">
        <h2>Passer une commande</h2>

        <!-- Récupération des catégories -->
        <% 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Erreur lors du chargement du driver.");
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
            Statement stmt = conn.createStatement();
            ResultSet categories = stmt.executeQuery("SELECT * FROM Categorie");
            
            while (categories.next()) {
                int categorieId = categories.getInt("categorie_id");
                String categorieNom = categories.getString("nom");
        %>
		<!-- Informations client -->
    	<input type="hidden" id="client_id" name="client_id" value="${sessionScope.client_id }" required /><br/>
    
        <!-- Afficher le nom de la catégorie -->
        <h3 class="toggle-category" onclick="toggleCategory('<%= categorieId %>')"><%= categorieNom %></h3>

        <!-- Récupération des plats pour cette catégorie -->
        <div id="category-<%= categorieId %>" class="category-menu">
            <ul class="menu-list">
                <%
                    PreparedStatement menuStmt = conn.prepareStatement("SELECT * FROM Menu WHERE categorie_id = ?");
                    menuStmt.setInt(1, categorieId);
                    ResultSet plats = menuStmt.executeQuery();

                    while (plats.next()) {
                        int menuId = plats.getInt("menu_id");
                        String menuNom = plats.getString("nom");
                        double prix = plats.getDouble("prix");
                %>
                <!-- Afficher chaque plat avec une checkbox et un champ quantité -->
                <li class="menu-item">
                    <input type="checkbox" name="menu_<%= menuId %>" value="<%= menuId %>">
                    <label for="menu_<%= menuId %>"><%= menuNom %> - <%= prix %> MAD</label>
                    <input type="number" name="quantite_<%= menuId %>" placeholder="Quantité" min="1">
                </li>
                <% } %>
            </ul>
        </div>

        <% } 
            } catch(SQLException e){
                e.printStackTrace();
            }
        %>

        <!-- Adresse -->
        <div class="address-container">
            <label for="adresse">Adresse de livraison :</label>
            <input type="text" name="adresse" required>
        </div>

        <!-- Bouton de commande -->
        <div class="button-container">
            <button type="submit">Commander</button>
        </div>
    </form>

    <script>
        function toggleCategory(categoryId) {
            var categoryMenu = document.getElementById('category-' + categoryId);
            if (categoryMenu.style.display === "none" || categoryMenu.style.display === "") {
                categoryMenu.style.display = "block";
            } else {
                categoryMenu.style.display = "none";
            }
        }
    </script>

</body>
</html>
