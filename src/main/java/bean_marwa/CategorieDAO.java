package bean_marwa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CategorieDAO {
	public void insererCategorie(String nomCat) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("erreur lors de recuperation du Drive"+e);
		}
		Connection con=null;
		try {
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb","root","mysql40");
		} catch (SQLException e) {
			System.out.println("erreur lors de connexion");
		}
		PreparedStatement stat=null;
		try {
			stat= con.prepareStatement("insert into categorie (nom) values (?)");
			stat.setString(1, nomCat);
			
			stat.executeUpdate();
			con.close();
		} catch (Exception e) {
		    System.out.println("Erreur lors de l'insertion de la catégorie: " + e.getMessage());
		}finally {
			
			try {
				if(con != null) {
				 con.close();}
				if(stat != null) {
					stat.close();
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Categorie> recupererCategorie() {
		List<Categorie> listeCategories = new ArrayList<Categorie>();
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
		ResultSet r =null;
		try {
			String sql="select * from categorie";
			 stmt = conn.createStatement();
			 r= stmt.executeQuery(sql);
			
			while (r.next()) {
				
				Categorie ctg = new Categorie();
				ctg.setCategorieId(r.getInt("categorie_id"));
				ctg.setNomCateg(r.getString("nom"));
				listeCategories.add(ctg);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if(r!=null)
				r.close();
				if(stmt!=null)
				stmt.close();
				if(conn!=null)
				conn.close();
				}catch(SQLException ignore) { }
		}
	return listeCategories;
}
	
	public void supprimerCategorie(int categorieId) {
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "DELETE FROM categorie WHERE categorie_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, categorieId);
	        stmt.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("Erreur lors de la suppression de la catégorie: " + e.getMessage());
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	}
	
	public Categorie recupererCategorieParId(int categorieId) {
	    Categorie categorie = null;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "SELECT * FROM categorie WHERE categorie_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, categorieId);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            categorie = new Categorie();
	            categorie.setCategorieId(rs.getInt("categorie_id"));
	            categorie.setNomCateg(rs.getString("nom"));
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
	    return categorie;
	}

	public void mettreAJourCategorie(Categorie categorie) {
	    Connection conn = null;
	    PreparedStatement stmt = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "mysql40");
	        String sql = "UPDATE categorie SET nom = ? WHERE categorie_id = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, categorie.getNomCateg());
	        stmt.setInt(2, categorie.getCategorieId());

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