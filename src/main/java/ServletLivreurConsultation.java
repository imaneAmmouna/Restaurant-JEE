

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletLivreurConsultation
 */
@WebServlet("/ServletLivreurConsultation")
public class ServletLivreurConsultation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLivreurConsultation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/consultationCommandesLivreur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandeId = request.getParameter("commande_id");
        String nouveauStatus = request.getParameter("status");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestaurantDB", "root", "mysql40");

            // Requête de mise à jour du statut de la commande
            String sql = "UPDATE Commande SET status = ? WHERE commande_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nouveauStatus);
            pstmt.setInt(2, Integer.parseInt(commandeId));

            // Exécution de la requête
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Message de succès pour afficher dans la JSP (optionnel)
                request.setAttribute("message", "Le statut de la commande a été mis à jour avec succès !");
            } else {
                // Message d'erreur pour afficher dans la JSP (optionnel)
                request.setAttribute("error", "Aucune commande trouvée avec l'ID fourni.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la mise à jour de la commande : " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirection vers la page JSP avec les commandes
        request.getRequestDispatcher("/WEB-INF/consultationCommandesLivreur.jsp").forward(request, response);
    }

}
