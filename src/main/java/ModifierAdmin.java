

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import bean_classes.Admin;
import bean_classes.AdminDAO;

/**
 * Servlet implementation class ModifierAdmin
 */
public class ModifierAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int adminId = (int) session.getAttribute("admin_id");
	    
	    AdminDAO adminDAO = new AdminDAO();
	    Admin admin = adminDAO.recupererAdminParId(adminId);
	    
	    if (admin != null) {
	        request.setAttribute("admin", admin);
	        request.getRequestDispatcher("/WEB-INF/profilAdmin.jsp").forward(request, response);
	    } else {
	        response.sendRedirect("erreur.jsp?message=Admin non trouvé.");
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int adminId = Integer.parseInt(request.getParameter("admin_id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Admin admin = new Admin();
        admin.setAdminId(adminId);
        admin.setNom(nom);
        admin.setPrenom(prenom);
        admin.setEmail(email);
        admin.setPassword(password);

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.mettreAJourAdmin(admin);

        response.sendRedirect("profilAdmin.jsp?message=Mise à jour réussie !");
	}
}
