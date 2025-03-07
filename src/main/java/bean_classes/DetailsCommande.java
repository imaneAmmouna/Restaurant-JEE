package bean_classes;

public class DetailsCommande {
    private int detailsId;
    private int commandeId;
    private int menuId;
    private String menuNom;
    private int quantite;
    private double prixUnitaire;

    // Getters and Setters
    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

	public String getMenuNom() {
		return menuNom;
	}

	public void setMenuNom(String menuNom) {
		this.menuNom = menuNom;
	}
}

