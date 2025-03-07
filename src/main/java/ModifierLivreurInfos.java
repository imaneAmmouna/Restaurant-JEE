

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import bean_classes.Livreur;
import bean_classes.Serveur;
import bean_marwa.EmployeDAO;

/**
 * Servlet implementation class ModifierLivreurInfos
 */
@WebServlet("/ModifierLivreurInfos")
public class ModifierLivreurInfos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierLivreurInfos() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Integer serveurId = (Integer) session.getAttribute("serveur_id");
        Integer livreurId = (Integer) session.getAttribute("livreur_id");
        
        if (serveurId != null) {
            EmployeDAO serveurDAO = new EmployeDAO();
            Serveur serveur = serveurDAO.recupererServeurParId(serveurId);
            request.setAttribute("utilisateur", serveur);
            request.setAttribute("role", "serveur");
        } else if (livreurId != null) {
        	EmployeDAO livreurDAO = new EmployeDAO();
            Livreur livreur = livreurDAO.recupererLivreurParId(livreurId);
            request.setAttribute("utilisateur", livreur);
            request.setAttribute("role", "livreur");
        }

        request.getRequestDispatcher("/WEB-INF/modifierInformations.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Integer serveurId = (Integer) session.getAttribute("serveur_id");
        Integer livreurId = (Integer) session.getAttribute("livreur_id");

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        if (serveurId != null) {
        	EmployeDAO serveurDAO = new EmployeDAO();
            serveurDAO.updateServeur(serveurId, nom, prenom, email, hashedPassword);
            System.out.println("serveur modifie");
            response.sendRedirect("ModifierServeurInfos?message=Modifications réussies !");
        } else if (livreurId != null) {
        	EmployeDAO livreurDAO = new EmployeDAO();
            livreurDAO.updateLivreur(livreurId, nom, prenom, email, hashedPassword);
            System.out.println("livreur modifie");
            response.sendRedirect("ModifierServeurInfos?message=Modifications réussies !");
        }
    }

}
