

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bean_marwa.Categorie;
import bean_marwa.CategorieDAO;
import bean_marwa.MenuDAO;

/**
 * Servlet implementation class AjouterMenu
 */
public class AjouterMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieDAO categorieDAO = new CategorieDAO();
        List<Categorie> categories = categorieDAO.recupererCategorie();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/ajouterFrom.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String nomMenu = request.getParameter("nomMenu");
	        String description = request.getParameter("description");
	        float prixMenu = Float.parseFloat(request.getParameter("prixMenu"));
	        int categorieId = Integer.parseInt(request.getParameter("categorieId"));

	        MenuDAO menuDAO = new MenuDAO();
	        menuDAO.insererMenu(nomMenu, description, prixMenu, categorieId);

	        response.sendRedirect("AjouterMenu");
		
	}


}
