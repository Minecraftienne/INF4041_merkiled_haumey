package org.esiea.merkiled_haumey.diet;

public class Produit {

    private int id_produit;
    private String nom_produit;
    private byte[] image_produit;

    public Produit () {

    }

    public Produit(int id_produit, String nom_produit, byte[] image_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
    }

    public Produit(String nom_produit, byte[] image_produit) {
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public byte[] getImage_produit() {
        return image_produit;
    }

    public void setImage_produit(byte[] image_produit) {
        this.image_produit = image_produit;
    }
}