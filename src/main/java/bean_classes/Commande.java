package bean_classes;

import java.util.List;

public class Commande {
    private int commandeId;
    private int clientId;
    private int livreurId;
    private double totalPrix;
    private String status;
    private String adresseClient;
    private List<DetailsCommande> detailsCommande;

    // Getters and Setters
    public int getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(int commandeId) {
        this.commandeId = commandeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLivreurId() {
        return livreurId;
    }

    public void setLivreurId(int livreurId) {
        this.livreurId = livreurId;
    }

    public double getTotalPrix() {
        return totalPrix;
    }

    public void setTotalPrix(double totalPrix) {
        this.totalPrix = totalPrix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public List<DetailsCommande> getDetailsCommande() {
        return detailsCommande;
    }

    public void setDetailsCommande(List<DetailsCommande> detailsCommande) {
        this.detailsCommande = detailsCommande;
    }
}
