<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/table.css">
</head>
<%@ include file="menu.jsp" %>
<body>

	  <%
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;

                try {
                    // Connexion � la base de donn�es
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestaurantDB", "root", "mysql40");

                    // Requ�te avec jointure entre Commande et DetailsCommande
                    String sql = "SELECT * FROM Commande";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();

                    // V�rifier si le client a des commandes
                    if (!rs.isBeforeFirst()) {
            %>
                        <p>Aucune commande trouv�e pour le moment.</p>
            <%
                    } else {
            %>
                        <!-- Tableau pour afficher les commandes avec d�tails -->
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Num�ro de Commande</th>
                                    <th>Num�ro du client</th>
                                    <th>Num�ro du livreur</th>
                                    <th>Total de la Commande (MAD)</th>
                                    <th>Statut</th>
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
                                %>
                                <tr>
                                    <td><%= commandeId %></td>
                                    <td><%= clientId %></td>
                                    <td><%= livreurId %></td>
                                    <td><%= totalPrix %></td>
                                    <td><%= status %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
            <%
                    }
                } catch (SQLException e) {
                    out.println("<p>Une erreur s'est produite lors de la r�cup�ration des commandes : " + e.getMessage() + "</p>");
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