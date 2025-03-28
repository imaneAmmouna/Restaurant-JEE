package bean_classes;

public class Categorie {
    private int id;
    private String nom;

    // Constructeur
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
