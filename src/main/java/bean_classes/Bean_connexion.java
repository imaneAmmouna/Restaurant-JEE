package bean_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;


public class Bean_connexion {
	private String password;
    private String email;
    
    public Bean_connexion() {}

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
	
	public boolean verifierClient(String email, String password) throws Exception {
	    System.out.println("hi " + email + " hello " + password + " by");

	    // Chargement du driver
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("Erreur lors du chargement du driver.");
	        throw new Exception("Erreur lors du chargement du driver.");
	    }

	    boolean isValid = false;

	    // Regex pour différents rôles
	    String emailServerRegex = "^[A-Za-z]+[0-9]+_serveur@gmail\\.com$";
	    //String passwordServerRegex = "^Ser[A-Za-z0-9!@#$%^&*()_+={}|:;,.<>?/-]+veuR$";
	    String emailLivreurRegex = "^[A-Za-z]+[0-9]+_livreur@gmail\\.com$";
	    //String passwordLivreurRegex = "^Liv[A-Za-z0-9!@#$%^&*()_+={}|:;,.<>?/-]+reuR$";
	    String emailAdminRegex = "^[A-Za-z]+[0-9]+_admin@gmail\\.com$";

	    String tableName = null;
	    System.out.println("email pattern"+Pattern.matches(emailServerRegex, email));
	    if (Pattern.matches(emailServerRegex, email)) {
	        tableName = "serveur";
	    } else if (Pattern.matches(emailLivreurRegex, email)) {
	        tableName = "livreur";
	    } else if (Pattern.matches(emailAdminRegex, email)) {
	        tableName = "admin";
	    } else {
	        tableName = "client";
	    }

	    // Vérification des informations d'identification
	    String query = "SELECT * FROM " + tableName + " WHERE email = ?";
	    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, email);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                String dbName = rs.getString("nom");
	                String dbPrenom = rs.getString("prenom");
	                String dbEmail = rs.getString("email");
	                String dbPassword = rs.getString("password");

	                System.out.println("Client trouvé : ");
	                System.out.println("Nom: " + dbName);
	                System.out.println("Prenom: " + dbPrenom);
	                System.out.println("Email: " + dbEmail);
	                System.out.println("Password: " + dbPassword);
	                System.out.println("*********************");

	                // Vérification du mot de passe
	                if (tableName.equals("client")) {
	                    if (email.equals(dbEmail) && BCrypt.checkpw(password, dbPassword))
	                        isValid = true;
	                } else if (tableName.equals("serveur")) {
	                	if (email.equals(dbEmail) && BCrypt.checkpw(password, dbPassword))
	                		isValid = true;
	                } else if (tableName.equals("livreur")) {
	                	if (email.equals(dbEmail) && BCrypt.checkpw(password, dbPassword))
	                		isValid = true;
	                } else if (tableName.equals("admin")) {
	                	if (email.equals(dbEmail) && password.equals(dbPassword))
	                		isValid = true;
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Erreur lors de la vérification des identifiants.");
	    }

	    System.out.println("is" + isValid);
	    return isValid;
	}

	public Client getClientByEmail(String email) throws SQLException {
	    Client client = null;
	    String sql = "SELECT * FROM Client WHERE email = ?";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Récupérer la connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");

	        // Préparer la requête SQL
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, email);

	        // Exécuter la requête et récupérer les résultats
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // Initialiser l'objet client avec les données de la base
	            client = new Client();
	            client.setClientId(rs.getInt("client_id"));
	            client.setNom(rs.getString("nom"));
	            client.setPrenom(rs.getString("prenom"));
	            client.setEmail(rs.getString("email"));
	            client.setTelephone(rs.getString("telephone"));
	            client.setCreatedAt(rs.getTimestamp("created_at"));
	            client.setUpdatedAt(rs.getTimestamp("updated_at"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Erreur lors de la récupération des informations du client.");
	    } finally {
	        // Fermeture des ressources
	        rs.close();
	        stmt.close();
	        conn.close();
	    }
	    return client;
	}
	
	public Serveur getServeurByEmail(String email) throws SQLException {
	    Serveur serveur = null;
	    String sql = "SELECT * FROM serveur WHERE email = ?";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Récupérer la connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");

	        // Préparer la requête SQL
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, email);

	        // Exécuter la requête et récupérer les résultats
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // Initialiser l'objet client avec les données de la base
	        	serveur = new Serveur();
	        	serveur.setServeurId(rs.getInt("serveur_id"));
	        	serveur.setNom(rs.getString("nom"));
	        	serveur.setPrenom(rs.getString("prenom"));
	        	serveur.setEmail(rs.getString("email"));
	        	serveur.setTelephone(rs.getString("telephone"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Erreur lors de la récupération des informations du client.");
	    } finally {
	        // Fermeture des ressources
	        rs.close();
	        stmt.close();
	        conn.close();
	    }
	    return serveur;
	}
	
	public Livreur getLivreurByEmail(String email) throws SQLException {
		Livreur livreur = null;
	    String sql = "SELECT * FROM livreur WHERE email = ?";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Récupérer la connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");

	        // Préparer la requête SQL
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, email);

	        // Exécuter la requête et récupérer les résultats
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // Initialiser l'objet client avec les données de la base
	        	livreur = new Livreur();
	        	livreur.setLivreurId(rs.getInt("livreur_id"));
	        	livreur.setNom(rs.getString("nom"));
	        	livreur.setPrenom(rs.getString("prenom"));
	        	livreur.setEmail(rs.getString("email"));
	        	livreur.setTelephone(rs.getString("telephone"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Erreur lors de la récupération des informations du client.");
	    } finally {
	        // Fermeture des ressources
	        rs.close();
	        stmt.close();
	        conn.close();
	    }
	    return livreur;
	}
	
	public Admin getAdminByEmail(String email) throws SQLException {
		Admin admin = null;
	    String sql = "SELECT * FROM admin WHERE email = ?";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Récupérer la connexion à la base de données
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");

	        // Préparer la requête SQL
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, email);

	        // Exécuter la requête et récupérer les résultats
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            // Initialiser l'objet client avec les données de la base
	        	admin = new Admin();
	        	admin.setAdminId(rs.getInt("admin_id"));
	        	admin.setNom(rs.getString("nom"));
	        	admin.setPrenom(rs.getString("prenom"));
	        	admin.setEmail(rs.getString("email"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("Erreur lors de la récupération des informations du client.");
	    } finally {
	        // Fermeture des ressources
	        rs.close();
	        stmt.close();
	        conn.close();
	    }
	    return admin;
	}


}
