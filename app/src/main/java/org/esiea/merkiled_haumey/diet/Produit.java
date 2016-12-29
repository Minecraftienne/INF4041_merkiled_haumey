package org.esiea.merkiled_haumey.diet;

public class Produit {

    private int id_produit;
    private String score_nutritionnel_produit, nom_produit, energie_produit, matieresGrassesLipides_produit, dontAcidesGrasSatures_produit,
    glucides_produit, dontSucres_produit, proteines_produit, sel_produit, sodium_produit;
    private byte[] image_produit;

    public Produit () {

    }

    public Produit(int id_produit, String nom_produit, byte[] image_produit, String score_nutritionnel_produit, String energie_produit,
                   String matieresGrassesLipides_produit, String dontAcidesGrasSatures_produit, String glucides_produit,
                   String dontSucres_produit, String proteines_produit, String sel_produit, String sodium_produit) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
        this.score_nutritionnel_produit = score_nutritionnel_produit;
        this.energie_produit = energie_produit;
        this.matieresGrassesLipides_produit = matieresGrassesLipides_produit;
        this.dontAcidesGrasSatures_produit = dontAcidesGrasSatures_produit;
        this.glucides_produit = glucides_produit;
        this.dontSucres_produit = dontSucres_produit;
        this.proteines_produit = proteines_produit;
        this.sel_produit = sel_produit;
        this.sodium_produit = sodium_produit;
    }

    public Produit(String nom_produit, byte[] image_produit, String score_nutritionnel_produit, String energie_produit,
                   String matieresGrassesLipides_produit, String dontAcidesGrasSatures_produit, String glucides_produit,
                   String dontSucres_produit, String proteines_produit, String sel_produit, String sodium_produit) {
        this.nom_produit = nom_produit;
        this.image_produit = image_produit;
        this.score_nutritionnel_produit = score_nutritionnel_produit;
        this.energie_produit = energie_produit;
        this.matieresGrassesLipides_produit = matieresGrassesLipides_produit;
        this.dontAcidesGrasSatures_produit = dontAcidesGrasSatures_produit;
        this.glucides_produit = glucides_produit;
        this.dontSucres_produit = dontSucres_produit;
        this.proteines_produit = proteines_produit;
        this.sel_produit = sel_produit;
        this.sodium_produit = sodium_produit;
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

    public String getScore_nutritionnel_produit() {
        return score_nutritionnel_produit;
    }

    public String getEnergie_produit() {
        return energie_produit;
    }

    public String getMatieresGrassesLipides_produit() {
        return matieresGrassesLipides_produit;
    }

    public String getDontAcidesGrasSatures_produit() {
        return dontAcidesGrasSatures_produit;
    }

    public String getGlucides_produit() {
        return glucides_produit;
    }

    public String getDontSucres_produit() {
        return dontSucres_produit;
    }

    public String getProteines_produit() {
        return proteines_produit;
    }

    public String getSel_produit() {
        return sel_produit;
    }

    public String getSodium_produit() {
        return sodium_produit;
    }
}