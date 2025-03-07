<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bean_classes.Commande"%>
<%@ page import="bean_classes.DetailsCommande"%>
<html>
<head>
    <title>Confirmation de la commande</title>
    
    <style>
    	/* Global Styles */
body {
    font-family: Arial, sans-serif;
    background-color: #F7EFCA;  /* Fond clair */
    color: #784726;  /* Couleur principale du texte */
    margin: 0;
    padding: 0;
}

h1 {
    color: #6a3d2a;  /* Titre principal en couleur foncée */
    text-align: center;
    padding: 20px;
    background-color: #f9a961;  /* Couleur de fond du titre */
}

h3, h4 {
    color: #a5765e;  /* Couleur des sous-titres */
    margin-top: 20px;
}

p {
    color: #784726;  /* Texte de message d'erreur */
    text-align: center;
    font-size: 16px;
    padding: 10px;
}

/* Table Styles */
table {
    width: 80%;
    margin: 20px auto;
    border-collapse: collapse;
    background-color: #fffcec;  /* Fond de la table */
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}

th, td {
    padding: 12px 15px;
    text-align: left;
}

th {
    background-color: #de6206;  /* Couleur du fond des en-têtes */
    color: #fffcec;  /* Texte des en-têtes en clair */
    font-size: 18px;
}

td {
    background-color: #f9a961;  /* Fond des cellules */
    color: #784726;  /* Texte dans les cellules */
    font-size: 16px;
}

tr:nth-child(even) td {
    background-color: #a5765e;  /* Ligne paire avec un fond plus sombre */
}

tr:nth-child(odd) td {
    background-color: #f7efca;  /* Ligne impaire avec un fond plus clair */
}

/* Responsive Styles */
@media (max-width: 768px) {
    table {
        width: 100%;
    }

    th, td {
        padding: 10px;
        font-size: 14px;
    }

    h1 {
        font-size: 24px;
    }
}
    	
    </style>
</head>
<body>
    <h1>Votre commande a été passée avec succès !</h1>
    <%
        Commande commande = (Commande) request.getAttribute("commande");
        if (commande != null) {
    %>
        <h3>Numéro de commande : <%= commande.getCommandeId() %></h3>
        <h4>Détails de la commande </h4>
        
        <!-- Tableau des détails de la commande -->
        <table>
            <thead>
                <tr>
                    <th>Nom du Plat</th>
                    <th>Quantité</th>
                    <th>Prix Unitaire (MAD)</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (DetailsCommande detail : commande.getDetailsCommande()) {
                        String nomPlat = detail.getMenuNom();  // Nom du plat
                        int quantite = detail.getQuantite();
                        double prixUnitaire = detail.getPrixUnitaire();
                %>
                <tr>
                    <td><%= nomPlat %></td>
                    <td><%= quantite %></td>
                    <td><%= prixUnitaire %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <h4>Total de la commande : <%= commande.getTotalPrix() %> MAD</h4>
    <%
        } else {
    %>
        <p>La commande n'a pas pu être traitée. Veuillez réessayer.</p>
    <%
        }
    %>
</body>
</html>
