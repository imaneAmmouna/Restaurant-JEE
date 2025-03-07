package bean_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class Bean_creer_compte {
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;
    
    public Bean_creer_compte(){}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void insererClient(String nom, String prenom, String password, String email, String telephone) {
		//chargement du driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Erreur lors du chargement du driver.");
		}
				
		//etablir la connexion
		try {
			Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb","root","mysql40");
			System.out.println("Connexion à la BD bien etablie.");
			
			// Hachage du mot de passe
            String motDePasseHache = BCrypt.hashpw(password, BCrypt.gensalt());
            System.out.println("le pwd est :"+password);		
			//preparation de l'execution
			PreparedStatement preparedstatement = connexion.prepareStatement(
				"insert into Client(nom,prenom,password,email,telephone) values (?,?,?,?,?)"
			);
					
			// execution de la requete
			preparedstatement.setString(1, nom);
			preparedstatement.setString(2, prenom);
			preparedstatement.setString(3, motDePasseHache);
			preparedstatement.setString(4, email);
			preparedstatement.setString(5, telephone);
					
			preparedstatement.executeUpdate();
			System.out.println("Client inséré avec succès.");
					
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur de connexion.");
		}
		
	}
	
	// Méthode pour vérifier les informations d'identification
    public boolean verifierCompteClient(String email) throws Exception {
    	boolean isValid = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
      //chargement du driver
      		try {
      			Class.forName("com.mysql.cj.jdbc.Driver");
      		}catch(ClassNotFoundException e){
      			e.printStackTrace();
      			System.out.println("Erreur lors du chargement du driver.");
      		}
      		
        try {
            // Connexion à la base de données
        	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb","root","mysql40");

            // Requête SQL pour vérifier les informations
            String sql = "SELECT COUNT(*) FROM client WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la vérification des identifiants");
        } finally {
            // Fermeture des ressources
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return isValid;
    }
}
