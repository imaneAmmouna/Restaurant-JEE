

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean_classes.Commande;
import bean_classes.DetailsCommande;

/**
 * Servlet implementation class GestionCommande
 */
@WebServlet("/GestionCommande")
public class GestionCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupérer les informations du formulaire
	    int clientId = Integer.parseInt(request.getParameter("client_id"));
	    String adresse = request.getParameter("adresse");
	    
	    double totalPrix = 0.0;
	    List<DetailsCommande> detailsList = new ArrayList<>();
	    
	    // Connexion à la base de données
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestaurantDB", "root", "mysql40")) {
	        conn.setAutoCommit(false); // Démarre une transaction

	        // Création de la commande
	        String commandeQuery = "INSERT INTO Commande (client_id, adresse_client, total_prix, status) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement psCommande = conn.prepareStatement(commandeQuery, Statement.RETURN_GENERATED_KEYS)) {
	            psCommande.setInt(1, clientId);
	            psCommande.setString(2, adresse);
	            psCommande.setDouble(3, totalPrix);
	            psCommande.setString(4, "en attente");

	            psCommande.executeUpdate();

	            // Récupérer l'ID de la commande généré
	            ResultSet rsCommande = psCommande.getGeneratedKeys();
	            int commandeId = 0;
	            if (rsCommande.next()) {
	                commandeId = rsCommande.getInt(1);
	            }

	            // Récupérer les plats sélectionnés
	            for (String paramName : request.getParameterMap().keySet()) {
	                if (paramName.startsWith("menu_")) {
	                    int menuId = Integer.parseInt(paramName.split("_")[1]);
	                    int quantite = Integer.parseInt(request.getParameter("quantite_" + menuId));
	                    double prixUnitaire = 0.0;
	                    String menuNom = "";

	                    // Récupérer le prix unitaire et le nom du plat depuis la base de données
	                    String menuQuery = "SELECT prix, nom FROM Menu WHERE menu_id = ?";
	                    try (PreparedStatement psMenu = conn.prepareStatement(menuQuery)) {
	                        psMenu.setInt(1, menuId);
	                        try (ResultSet rsMenu = psMenu.executeQuery()) {
	                            if (rsMenu.next()) {
	                                prixUnitaire = rsMenu.getDouble("prix");
	                                menuNom = rsMenu.getString("nom");  // Récupérer le nom du plat
	                            }
	                        }
	                    } catch (SQLException e) {
	                        e.printStackTrace(); // Gérer l'exception en cas d'erreur
	                    }

	                    // Calculer le total pour cette commande
	                    totalPrix += prixUnitaire * quantite;

	                    // Ajouter les détails de la commande
	                    DetailsCommande details = new DetailsCommande();
	                    details.setCommandeId(commandeId);
	                    details.setMenuId(menuId);
	                    details.setMenuNom(menuNom);  // Affecter le nom du plat
	                    details.setQuantite(quantite);
	                    details.setPrixUnitaire(prixUnitaire);
	                    detailsList.add(details);

	                    // Insérer dans DetailsCommande
	                    String detailsQuery = "INSERT INTO DetailsCommande (commande_id, menu_id, quantite, prix_unitaire, menu_nom) VALUES (?, ?, ?, ?, ?)";
	                    try (PreparedStatement psDetails = conn.prepareStatement(detailsQuery)) {
	                        psDetails.setInt(1, commandeId);
	                        psDetails.setInt(2, menuId);
	                        psDetails.setInt(3, quantite);
	                        psDetails.setDouble(4, prixUnitaire);
	                        psDetails.setString(5, menuNom); 
	                        psDetails.executeUpdate();
	                    }
	                }
	            }

	            // Mettre à jour le total de la commande
	            String updateCommandeQuery = "UPDATE Commande SET total_prix = ? WHERE commande_id = ?";
	            try (PreparedStatement psUpdateCommande = conn.prepareStatement(updateCommandeQuery)) {
	                psUpdateCommande.setDouble(1, totalPrix);
	                psUpdateCommande.setInt(2, commandeId);
	                psUpdateCommande.executeUpdate();
	            }

	            // Commit transaction
	            conn.commit();

	            // Créer l'objet Commande et l'ajouter à la requête
	            Commande commande = new Commande();
	            commande.setCommandeId(commandeId);
	            commande.setClientId(clientId);
	            commande.setAdresseClient(adresse);
	            commande.setTotalPrix(totalPrix);
	            commande.setDetailsCommande(detailsList);

	            request.setAttribute("commande", commande);

	            // Rediriger vers la page de confirmation ou la page des détails de la commande
	            request.getRequestDispatcher("/confirmationCommande.jsp").forward(request, response);

	        } catch (SQLException e) {
	            e.printStackTrace(); // Gérer l'exception en cas d'erreur
	            try {
	                conn.rollback(); // Annuler la transaction en cas d'erreur
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Gérer l'exception en cas d'erreur de connexion
	    }
	}

}
