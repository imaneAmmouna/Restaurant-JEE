<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Vos Commandes</title>
    <style>
    /* Style de base */
    body {
        background-color: #F7EFCA; /* Fond clair */
        color: #784726; /* Texte sombre pour une bonne lisibilité */
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        color: #6a3d2a; /* Couleur du titre */
        margin-top: 30px;
    }

    /* Style du tableau des commandes */
    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
        background-color: #fffcec; /* Fond très clair pour le tableau */
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Ombre légère */
    }

    thead {
        background-color: #de6206; /* Entête de tableau avec couleur chaude */
        color: white;
    }

    th, td {
        padding: 12px 15px;
        text-align: left;
    }

    tbody tr:nth-child(even) {
        background-color: #f9a961; /* Alternance de couleur de ligne */
    }

    tbody tr:hover {
        background-color: #f57618; /* Couleur survolée des lignes */
    }

    /* Style des messages */
    p {
        text-align: center;
        color: #d2ae93; /* Couleur douce pour les messages d'erreur ou d'absence */
        font-size: 16px;
    }

    .message {
        text-align: center;
        font-size: 18px;
        color: #a5765e; /* Couleur douce pour les messages de succès */
    }

    /* Style des boutons */
    button {
        background-color: #a7512a; /* Couleur du bouton avec nuance plus foncée */
        color: white;
        border: none;
        padding: 10px 20px;
        margin-top: 20px;
        cursor: pointer;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    button:hover {
        background-color: #8a705d; /* Couleur au survol du bouton */
    }
</style>

    
</head>

<body>
    <%@ include file="/includes/header_client.jsp" %>

    <h1>Vos Commandes Détails</h1>


    <c:choose>
        <c:when test="${empty sessionScope.client_id}">
            <p>Veuillez vous connecter pour voir vos commandes.</p>
        </c:when>
        <c:otherwise>
            <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    // Connexion à la base de données
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestaurantDB", "root", "mysql40");

                    // Récupérer l'ID du client à partir de la session
                    Integer clientId = (Integer) session.getAttribute("client_id");

                    // Requête avec jointure entre Commande et DetailsCommande
                    String sql = "SELECT c.commande_id AS num_commande, dc.menu_nom, dc.prix_unitaire, " +
                                 "(dc.prix_unitaire * dc.quantite) AS total_plat, c.total_prix, c.status, c.adresse_client " +
                                 "FROM Commande c " +
                                 "JOIN DetailsCommande dc ON c.commande_id = dc.commande_id " +
                                 "WHERE c.client_id = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, clientId);
                    rs = pstmt.executeQuery();

                    // Vérifier si le client a des commandes
                    if (!rs.isBeforeFirst()) {
            %>
                        <p>Aucune commande trouvée pour le moment.</p>
            <%
                    } else {
            %>
                        <!-- Tableau pour afficher les commandes avec détails -->
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Numéro de Commande</th>
                                    <th>Nom du Menu</th>
                                    <th>Prix Unitaire (MAD)</th>
                                    <th>Total du Menu (MAD)</th>
                                    <th>Total de la Commande (MAD)</th>
                                    <th>Statut</th>
                                    <th>Adresse</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    while (rs.next()) {
                                        int commandeId = rs.getInt("num_commande");
                                        String menuNom = rs.getString("menu_nom");
                                        double prixUnitaire = rs.getDouble("prix_unitaire");
                                        double totalPlat = rs.getDouble("total_plat");
                                        double totalPrix = rs.getDouble("total_prix");
                                        String status = rs.getString("status");
                                        String adresse = rs.getString("adresse_client");
                                %>
                                <tr>
                                    <td><%= commandeId %></td>
                                    <td><%= menuNom %></td>
                                    <td><%= prixUnitaire %></td>
                                    <td><%= totalPlat %></td>
                                    <td><%= totalPrix %></td>
                                    <td><%= status %></td>
                                    <td><%= adresse %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
            <%
                    }
                } catch (SQLException e) {
                    out.println("<p>Une erreur s'est produite lors de la récupération des commandes : " + e.getMessage() + "</p>");
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        out.println("<p>Une erreur s'est produite lors de la fermeture des ressources : " + e.getMessage() + "</p>");
                    }
                }
            %>
        </c:otherwise>
    </c:choose>

</body>
</html>
