package bean_marwa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

	public void insererMenu(String nomMenu, String description, float prixMenu, int categorieId) {
	    Connection con = null;
	    PreparedStatement stat = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String query = "INSERT INTO menu (nom, description, prix, categorie_id) VALUES (?, ?, ?, ?)";
	        stat = con.prepareStatement(query);
	        stat.setString(1, nomMenu);
	        stat.setString(2, description);
	        stat.setFloat(3, prixMenu);
	        stat.setInt(4, categorieId);
	        stat.executeUpdate();
	    } catch (ClassNotFoundException e) {
	        System.out.println("Erreur de chargement du driver: " + e);
	    } catch (SQLException e) {
	        System.out.println("Erreur SQL: " + e);
	    } finally {
	        try {
	            if (stat != null) stat.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Erreur lors de la fermeture des ressources: " + e);
	        }
	    }
	}

	
	public List<Menu> recupererMenusAvecCategorie() {
        List<Menu> listeMenus = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
            String sql = "SELECT m.menu_id, m.nom AS nomMenu, m.description, m.prix, m.categorie_id, c.nom AS nomCategorie " +
                         "FROM menu m " +
                         "LEFT JOIN categorie c ON m.categorie_id = c.categorie_id";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Menu menu = new Menu();
                menu.setMenuId(rs.getInt("menu_id"));
                menu.setNomMenu(rs.getString("nomMenu"));
                menu.setDescription(rs.getString("description"));
                menu.setPrixMenu(rs.getFloat("prix"));
                menu.setCategorieId(rs.getInt("categorie_id"));
                menu.setNomCategorie(rs.getString("nomCategorie")); // Ajoute cette méthode dans la classe Menu
                listeMenus.add(menu);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des menus: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return listeMenus;
    }
	
	public void supprimerMenu(int menuId) {
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "DELETE FROM menu WHERE menu_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, menuId);
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("Erreur lors de la suppression du menu: " + e.getMessage());
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	public Menu recupererMenuParId(int menuId) {
	    Menu menu = null;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "SELECT * FROM menu WHERE menu_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, menuId);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            menu = new Menu();
	            menu.setMenuId(rs.getInt("menu_id"));
	            menu.setNomMenu(rs.getString("nom"));
	            menu.setDescription(rs.getString("description"));
	            menu.setPrixMenu(rs.getFloat("prix"));
	            menu.setCategorieId(rs.getInt("categorie_id"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return menu;
	}
	public void mettreAJourMenu(Menu menu) {
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "UPDATE menu SET nom = ?, description = ?, prix = ?, categorie_id = ? WHERE menu_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, menu.getNomMenu());
	        stmt.setString(2, menu.getDescription());
	        stmt.setFloat(3, menu.getPrixMenu());
	        stmt.setInt(4, menu.getCategorieId());
	        stmt.setInt(5, menu.getMenuId());

	        stmt.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	

}
