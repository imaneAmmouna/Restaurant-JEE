package bean_classes;

public class Serveur {
	private int serveurId;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private String telephone;
    
    public Serveur() {}

	public int getServeurId() {
		return serveurId;
	}

	public void setServeurId(int serveurId) {
		this.serveurId = serveurId;
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
