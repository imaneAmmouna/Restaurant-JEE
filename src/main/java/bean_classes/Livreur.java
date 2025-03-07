package bean_classes;

public class Livreur {
	private int livreurId;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;
    
    public Livreur() {}
    
	public int getLivreurId() {
		return livreurId;
	}
	public void setLivreurId(int livreurId) {
		this.livreurId = livreurId;
	}
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
}
