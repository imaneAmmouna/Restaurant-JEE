

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import bean_marwa.EmployeDAO;

/**
 * Servlet implementation class AjouterEmploye
 */
public class AjouterEmploye extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterEmploye() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/ajouterEmploye.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        String role = request.getParameter("role");

        String code = genererCodeAleatoire();
        String email = creerEmail(nom, prenom, code, role);
        String password = genererMotDePasse(nom, prenom);
        String hashedPassword = hasherMotDePasse(password);

        EmployeDAO employeDAO = new EmployeDAO();
        if ("serveur".equalsIgnoreCase(role)) {
            employeDAO.insererServeur(nom, prenom, hashedPassword, email, telephone);
        } else if ("livreur".equalsIgnoreCase(role)) {
            employeDAO.insererLivreur(nom, prenom, hashedPassword, email, telephone);
        }

        response.sendRedirect("AjouterEmploye?message=Ajout reussie !");

    }

    private String genererCodeAleatoire() {
        int code = (int)(Math.random() * 9000) + 1000;
        return String.valueOf(code);
    }

    private String creerEmail(String nom, String prenom, String code, String role) {
        return nom.toLowerCase() + prenom.toLowerCase() + code + "_" + role.toLowerCase() + "@gmail.com";
    }

    private String genererMotDePasse(String nom, String prenom) {
        return nom.toLowerCase() + prenom.toLowerCase();
    }

    private String hasherMotDePasse(String motDePasse) {
        return BCrypt.hashpw(motDePasse, BCrypt.gensalt());
    }


}
