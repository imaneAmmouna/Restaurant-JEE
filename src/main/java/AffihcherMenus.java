

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bean_marwa.Categorie;
import bean_marwa.CategorieDAO;
import bean_marwa.Menu;
import bean_marwa.MenuDAO;

/**
 * Servlet implementation class AffihcherMenus
 */
public class AffihcherMenus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffihcherMenus() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MenuDAO menuDAO = new MenuDAO();
		CategorieDAO categorieDAO=new CategorieDAO();
		List<Menu> menus =menuDAO.recupererMenusAvecCategorie();
		List<Categorie> categories = categorieDAO.recupererCategorie();
		request.setAttribute("categories", categories);
		request.setAttribute("menus", menus);
		request.getRequestDispatcher("/WEB-INF/afficherMenu.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
