package bean_marwa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean_classes.Livreur;
import bean_classes.Serveur;

public class EmployeDAO {

    public void insererServeur(String nom, String prenom, String password, String email, String telephone) {
        String sql = "INSERT INTO serveur (nom, prenom, password, email, telephone) VALUES (?, ?, ?, ?, ?)";
        executerInsertion(sql, nom, prenom, password, email, telephone);
    }

    public void insererLivreur(String nom, String prenom, String password, String email, String telephone) {
        String sql = "INSERT INTO livreur (nom, prenom, password, email, telephone) VALUES (?, ?, ?, ?, ?)";
        executerInsertion(sql, nom, prenom, password, email, telephone);
    }

    private void executerInsertion(String sql, String nom, String prenom, String password, String email, String telephone) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, password);
            stmt.setString(4, email);
            stmt.setString(5, telephone);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Serveur> recupererServeurs(){
    	List<Serveur> serveurs = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("erreur de chargement du drive");
		}
		Connection conn =null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb","root","mysql40");
		} catch (SQLException e) {
			System.out.println("erreur de connexion");
		}
		Statement stmt = null;
		ResultSet rs =null;
		try {
			String query = "SELECT * FROM serveur";
			 stmt = conn.createStatement();
			 rs= stmt.executeQuery(query);
			
			while (rs.next()) {
				
				Serveur serveur = new Serveur();
                serveur.setServeurId(rs.getInt("serveur_id"));
                serveur.setNom(rs.getString("nom"));
                serveur.setPrenom(rs.getString("prenom"));
                serveur.setEmail(rs.getString("email"));
                serveur.setTelephone(rs.getString("telephone"));
                serveurs.add(serveur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serveurs;
    }
    public List<Livreur> recupererLivreurs() {
        List<Livreur> livreurs = new ArrayList<>();
        String query = "SELECT * FROM livreur";
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("erreur de chargement du drive");
		}
		Connection conn =null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb","root","mysql40");
		} catch (SQLException e) {
			System.out.println("erreur de connexion");
		}
		Statement stmt = null;
		ResultSet rs =null;
        try  {
        	stmt = conn.createStatement();
			rs= stmt.executeQuery(query);
            while (rs.next()) {
                Livreur livreur = new Livreur();
                livreur.setLivreurId(rs.getInt("livreur_id"));
                livreur.setNom(rs.getString("nom"));
                livreur.setPrenom(rs.getString("prenom"));
                livreur.setEmail(rs.getString("email"));
                livreur.setTelephone(rs.getString("telephone"));
                livreurs.add(livreur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livreurs;
    }

    public Serveur recupererServeurParId(int id) {
        Serveur serveur = null;
        String query = "SELECT * FROM serveur WHERE serveur_id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                serveur = new Serveur();
                serveur.setServeurId(rs.getInt("serveur_id"));
                serveur.setNom(rs.getString("nom"));
                serveur.setPrenom(rs.getString("prenom"));
                serveur.setEmail(rs.getString("email"));
                serveur.setTelephone(rs.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serveur;
    }

    public void supprimerServeur(int id) {
        String query = "DELETE FROM serveur WHERE serveur_id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void mettreAJourServeur(int id, String nom, String prenom, String email, String telephone) {
        String query = "UPDATE serveur SET nom=?, prenom=?, email=?, telephone=? WHERE serveur_id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, telephone);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Livreur recupererLivreurParId(int id) {
        Livreur livreur = null;
        String query = "SELECT * FROM livreur WHERE livreur_id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                livreur = new Livreur();
                livreur.setLivreurId(rs.getInt("livreur_id"));
                livreur.setNom(rs.getString("nom"));
                livreur.setPrenom(rs.getString("prenom"));
                livreur.setEmail(rs.getString("email"));
                livreur.setTelephone(rs.getString("telephone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livreur;
    }
    public void supprimerLivreur(int id) {
        String query = "DELETE FROM livreur WHERE livreur_id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void mettreAJourLivreur(int id, String nom, String prenom, String email, String telephone) {
        String query = "UPDATE livreur SET nom=?, prenom=?, email=?, telephone=? WHERE livreur_id=?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, telephone);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateServeur(int id, String nom, String prenom, String email, String hashedPassword) {
        // Connexion et mise à jour
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement("UPDATE serveur SET nom = ?, prenom = ?, email = ?, password = ? WHERE serveur_id = ?")) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateLivreur(int id, String nom, String prenom, String email, String hashedPassword) {
        // Connexion et mise à jour
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
             PreparedStatement stmt = conn.prepareStatement("UPDATE livreur SET nom = ?, prenom = ?, email = ?, password = ? WHERE livreur_id = ?")) {
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, email);
            stmt.setString(4, hashedPassword);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
