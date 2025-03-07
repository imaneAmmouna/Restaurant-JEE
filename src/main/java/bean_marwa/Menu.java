package bean_marwa;

public class Menu {
	private String nomMenu;
    private String description;
    private float prixMenu;
    private int categorieId;
    private int menuId;
    private String nomCategorie;
    
	public String getNomMenu() {
		return nomMenu;
	}
	public void setNomMenu(String nomMenu) {
		this.nomMenu = nomMenu;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrixMenu() {
		return prixMenu;
	}
	public void setPrixMenu(float prixMenu) {
		this.prixMenu = prixMenu;
	}
	public int getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(int categorieId) {
		this.categorieId = categorieId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
}
