

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bean_marwa.Categorie;
import bean_marwa.CategorieDAO;

/**
 * Servlet implementation class ModifierCategorie
 */
public class ModifierCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categorieId = Integer.parseInt(request.getParameter("id_c"));
        CategorieDAO categorieDAO = new CategorieDAO();

        Categorie categorie = categorieDAO.recupererCategorieParId(categorieId);

        request.setAttribute("categorie", categorie);

        request.getRequestDispatcher("/WEB-INF/modifierCategorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int categorieId = Integer.parseInt(request.getParameter("id_c"));
	        String nom = request.getParameter("nom");

	        Categorie categorie = new Categorie();
	        categorie.setCategorieId(categorieId);
	        categorie.setNomCateg(nom);

	        CategorieDAO categorieDAO = new CategorieDAO();
	        categorieDAO.mettreAJourCategorie(categorie);

	        response.sendRedirect("AffihcherMenus?message=Modification reussie !");
	}


}
