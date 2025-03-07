

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bean_classes.Livreur;
import bean_classes.Serveur;
import bean_marwa.EmployeDAO;

/**
 * Servlet implementation class AfficherEmployes
 */
public class AfficherEmployes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherEmployes() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmployeDAO employeDAO = new EmployeDAO();

        List<Serveur> serveurs = employeDAO.recupererServeurs();
        List<Livreur> livreurs = employeDAO.recupererLivreurs();

        request.setAttribute("serveurs", serveurs);
        request.setAttribute("livreurs", livreurs);

        request.getRequestDispatcher("/WEB-INF/afficherEmployes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
