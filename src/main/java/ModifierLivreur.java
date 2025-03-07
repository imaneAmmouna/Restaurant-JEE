

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bean_classes.Livreur;
import bean_marwa.EmployeDAO;

/**
 * Servlet implementation class ModifierLivreur
 */
public class ModifierLivreur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierLivreur() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id_liv"));
        EmployeDAO employeDAO = new EmployeDAO();
        Livreur livreur = employeDAO.recupererLivreurParId(id);
        request.setAttribute("livreur", livreur);
        request.getRequestDispatcher("/WEB-INF/modifierlivreur.jsp").forward(request, response);
	}


}
