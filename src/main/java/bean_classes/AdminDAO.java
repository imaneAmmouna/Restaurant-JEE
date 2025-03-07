package bean_classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	public void mettreAJourAdmin(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
            String sql = "UPDATE admin SET nom = ?, prenom = ?, email = ?, password = ? WHERE admin_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, admin.getNom());
            stmt.setString(2, admin.getPrenom());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getPassword());
            stmt.setInt(5, admin.getAdminId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur lors de la mise à jour de l'admin: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public Admin recupererAdminParId(int adminId) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Admin admin = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "SELECT * FROM admin WHERE admin_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, adminId);
	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            admin = new Admin();
	            admin.setAdminId(rs.getInt("admin_id"));
	            admin.setNom(rs.getString("nom"));
	            admin.setPrenom(rs.getString("prenom"));
	            admin.setEmail(rs.getString("email"));
	            admin.setPassword(rs.getString("password"));
	        }
	    } catch (Exception e) {
	        System.out.println("Erreur lors de la récupération de l'admin: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return admin;
	}

}

