

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import bean_classes.Admin;
import bean_classes.Bean_connexion;
import bean_classes.Bean_creer_compte;
import bean_classes.Client;
import bean_classes.Livreur;
import bean_classes.Serveur;

/**
 * Servlet implementation class Page_depart
 */
public class Page_depart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Page_depart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if("connexion.jsp".equals(page)) {
			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request,response);
		}else if("creer_compte.jsp".equals(page)) {
			request.getRequestDispatcher("/WEB-INF/creer_compte.jsp").forward(request,response);
		}else if("acceuil_client.jsp".equals(page)) {
			request.getRequestDispatcher("/WEB-INF/acceuil_client.jsp").forward(request,response);
		}else {
			request.getRequestDispatcher("/WEB-INF/page_depart.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if ("creer_compte".equals(action)) {
			// creation de compte
			String nom = request.getParameter("nom");
        	String prenom = request.getParameter("prenom");
        	String email = request.getParameter("email");
        	String telephone = request.getParameter("telephone");
        	String password = request.getParameter("password");
        
			Bean_creer_compte compte = new Bean_creer_compte();
		
			compte.setNom(nom);
        	compte.setPrenom(prenom);
        	compte.setEmail(email);
        	compte.setTelephone(telephone);
        	compte.setPassword(password);
        
        	try {
        		// Vérification si le compte existe déjà
        	    if (compte.verifierCompteClient(email)) {
        	        request.setAttribute("errorMessage", "Cet email est déjà utilisé. Veuillez en choisir un autre.");
        	        request.getRequestDispatcher("/WEB-INF/creer_compte.jsp").forward(request, response);
        	    } else {
        	        // Si l'email n'existe pas, créer le compte
        	        compte.insererClient(nom, prenom, password, email, telephone);
        	        request.setAttribute("successMessage", "Votre compte a été inscrit avec succès !");
        	        request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        	    }

        	}catch(Exception e) {
        		e.printStackTrace();
        		request.setAttribute("errorMessage", "Erreur! Esaayez une autre fois.");
        		request.getRequestDispatcher("/WEB-INF/creer_compte.jsp").forward(request, response);
        	}
		}else if ("connexion".equals(action)) {
			// connexion
        	String email_cnx = request.getParameter("email");
        	String password_cnx = request.getParameter("password");
        
        	Bean_connexion cnx = new Bean_connexion();
		
        	cnx.setEmail(email_cnx);
        	cnx.setPassword(password_cnx);
        
        	try {
        		request.setAttribute("cnx", cnx);
        		
        		if(cnx.verifierClient(email_cnx,password_cnx)) {
        			// Logique pour rediriger selon l'email
                    if (email_cnx.endsWith("_serveur@gmail.com")) {
                    	
                    	Serveur serveur = cnx.getServeurByEmail(email_cnx);
                        HttpSession session = request.getSession();
                        session.setAttribute("nom", serveur.getNom());
                        session.setAttribute("prenom", serveur.getPrenom());
                        session.setAttribute("serveur_id", serveur.getServeurId());
                        
                        response.sendRedirect("DashbordServeur");
                    } else if (email_cnx.endsWith("_livreur@gmail.com")) {
                    	
                    	Livreur livreur = cnx.getLivreurByEmail(email_cnx);
                        HttpSession session = request.getSession();
                        session.setAttribute("livreur_id", livreur.getLivreurId());
                    	
                        response.sendRedirect("DashbordLivreur");
                    } else if (email_cnx.endsWith("_admin@gmail.com")) {
                    	
                    	Admin admin = cnx.getAdminByEmail(email_cnx);
                        HttpSession session = request.getSession();
                        session.setAttribute("admin_id", admin.getAdminId());
                    	
                        response.sendRedirect("DashbordAdmin");
                    } else {
                    	
                    	// Récupérer les informations du client depuis la base de données
                        Client client = cnx.getClientByEmail(email_cnx); // Implémentez cette méthode dans votre Bean_connexion

                        // Stocker les informations du client dans la session
                        HttpSession session = request.getSession();
                        session.setAttribute("client_id", client.getClientId());
                        session.setAttribute("nom", client.getNom());
                        session.setAttribute("prenom", client.getPrenom());
                        session.setAttribute("email", client.getEmail());
                        session.setAttribute("telephone", client.getTelephone());
                        session.setAttribute("created_at", client.getCreatedAt());
                        
                        // Par défaut, rediriger vers une page générique
                        response.sendRedirect("Page_depart?page=acceuil_client.jsp");
                    }
        		}else {
        			request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        		}
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
		}
        
	}

}
