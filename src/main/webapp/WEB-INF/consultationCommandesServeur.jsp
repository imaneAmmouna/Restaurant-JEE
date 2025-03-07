<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #fffcec;
            color: #6a3d2a;
            margin: 0;
            padding: 20px;
        }

        h1, p {
            color: #784726;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: #f7efca;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #d2ae93;
        }

        th {
            background-color: #de6206;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #fffcec;
        }

        tr:hover {
            background-color: #f9a961;
            color: #fff;
        }

        p {
            padding: 10px;
            border: 1px solid #a7512a;
            background-color: #feac53;
            border-radius: 5px;
        }

        p.error {
            background-color: #fb9447;
            color: white;
        }

        /* Responsive design */
        @media (max-width: 768px) {
            table {
                font-size: 14px;
            }

            th, td {
                padding: 8px;
            }
        }
        
        /* Style pour les boutons */
button {
    background-color: #de6206; /* Couleur de fond primaire */
    color: white; /* Couleur du texte */
    border: 1px solid #a5765e; /* Bordure avec une couleur subtile de la palette */
    padding: 10px 20px; /* Espacement interne */
    font-size: 16px;
    cursor: pointer; /* Curseur en forme de main */
    border-radius: 5px; /* Coins arrondis */
    transition: background-color 0.3s, transform 0.3s; /* Transition fluide pour les effets au survol */
}

/* Effet au survol du bouton */
button:hover {
    background-color: #f9a961; /* Couleur de fond au survol */
    transform: scale(1.05); /* Agrandir légèrement le bouton */
}

/* Style pour les boutons "Enregistrer" */
button.btn-modifier {
    background-color: #784726; /* Couleur de fond secondaire pour un bouton d'action */
    border-color: #a7512a; /* Bordure avec une couleur subtile */
}

button.btn-modifier:hover {
    background-color: #feac53; /* Couleur au survol pour "Enregistrer" */
}
        
    </style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/includes/headerServeur.jsp" %>
	<%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    // Connexion à la base de données
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestaurantDB", "root", "mysql40");

                    // Requête avec jointure entre Commande et DetailsCommande
                    String sql = "SELECT c.commande_id, c.client_id, c.livreur_id, c.total_prix, c.status, dc.menu_nom, dc.quantite, dc.prix_unitaire " +
                            "FROM Commande c " +
                            "JOIN DetailsCommande dc ON c.commande_id = dc.commande_id " +
                            "WHERE c.status = 'en attente'";
                    pstmt = conn.prepareStatement(sql);
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
            <th>Numéro du client</th>
            <th>Numéro du livreur</th>
            <th>Statut</th>
            <th>Nom du Plat</th>
            <th>Quantité</th>
        </tr>
    </thead>
    <tbody>
        <%
            while (rs.next()) {
                int commandeId = rs.getInt("commande_id");
                String clientId = rs.getString("client_id");
                String livreurId = rs.getString("livreur_id");
                double totalPrix = rs.getDouble("total_prix");
                String status = rs.getString("status");
                String menuNom = rs.getString("menu_nom");
                int quantite = rs.getInt("quantite");
                double prixUnitaire = rs.getDouble("prix_unitaire");
        %>
        <tr>
            <td><%= commandeId %></td>
            <td><%= clientId %></td>
            <td><%= livreurId %></td>
            <td><%= status %> 
            	<form action="ServletServeurConsultation" method="POST" style="display: inline;">
        <input type="hidden" name="commande_id" value="<%= commandeId %>">
        <label>
            <input type="radio" name="status" value="en preparation" <%= status.equals("en preparation") ? "checked" : "" %>> En preparation
        </label>
        <label>
            <input type="radio" name="status" value="prete" <%= status.equals("prete") ? "checked" : "" %>> prete
        </label>
        <button type="submit" class="btn-modifier">Modifier</button>
    </form>
            </td>
            <td><%= menuNom %></td>
            <td><%= quantite %></td>
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
</body>
</html>