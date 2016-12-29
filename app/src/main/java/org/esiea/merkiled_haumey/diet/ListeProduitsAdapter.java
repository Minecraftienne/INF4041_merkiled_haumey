package org.esiea.merkiled_haumey.diet;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListeProduitsAdapter extends ArrayAdapter<Produit> {

    private Context context;
    List<Produit> produits = new ArrayList<Produit>();

    public ListeProduitsAdapter (Context context, List<Produit> produits) {
        super(context, R.layout.liste_produits, produits);
        this.context = context;
        this.produits = produits;
    }

    private class ViewHolder {
        TextView prodNomTxt;
        TextView prodScoreNutritionnelTxt;
        TextView prodEnergieTxt;
        TextView prodMatieresGrassesLipidesTxt;
        TextView prodDontAcidesGrasSaturesTxt;
        TextView prodGlucidesTxt;
        TextView prodDontSucresTxt;
        TextView prodProteinesTxt;
        TextView prodSelTxt;
        TextView prodSodiumTxt;
        ImageView prodImageView;
    }

    @Override
    public int getCount() {
        return produits.size();
    }

    @Override
    public Produit getItem(int position) {
        return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.liste_produits, null);
            holder = new ViewHolder();

            holder.prodNomTxt = (TextView) convertView.findViewById(R.id.txt_prod_nom);
            holder.prodScoreNutritionnelTxt = (TextView) convertView.findViewById(R.id.txt_prod_scoreNutritionnel);
            holder.prodEnergieTxt = (TextView) convertView.findViewById(R.id.txt_prod_energie);
            holder.prodMatieresGrassesLipidesTxt = (TextView) convertView.findViewById(R.id.txt_prod_matieresGrassesLipides);
            holder.prodDontAcidesGrasSaturesTxt = (TextView) convertView.findViewById(R.id.txt_prod_dontAcidesGrasSatures);
            holder.prodGlucidesTxt = (TextView) convertView.findViewById(R.id.txt_prod_glucides);
            holder.prodDontSucresTxt = (TextView) convertView.findViewById(R.id.txt_prod_dontSucres);
            holder.prodProteinesTxt = (TextView) convertView.findViewById(R.id.txt_prod_proteines);
            holder.prodSelTxt = (TextView) convertView.findViewById(R.id.txt_prod_sel);
            holder.prodSodiumTxt = (TextView) convertView.findViewById(R.id.txt_prod_sodium);
            holder.prodImageView = (ImageView) convertView.findViewById(R.id.imageProduit);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Produit produit = (Produit) getItem(position);

        holder.prodNomTxt.setText(produit.getNom_produit());
        holder.prodScoreNutritionnelTxt.setText(context.getString(R.string.scoreNutritionnel) + " " + produit.getScore_nutritionnel_produit());
        holder.prodEnergieTxt.setText(context.getString(R.string.energie) + " " + produit.getEnergie_produit());
        holder.prodMatieresGrassesLipidesTxt.setText(context.getString(R.string.matieresGrassesLipides) + " " + produit.getMatieresGrassesLipides_produit());
        holder.prodDontAcidesGrasSaturesTxt.setText(context.getString(R.string.dontAcidesGrasSatures) + " " + produit.getDontAcidesGrasSatures_produit());
        holder.prodGlucidesTxt.setText(context.getString(R.string.glucides) + " " + produit.getGlucides_produit());
        holder.prodDontSucresTxt.setText(context.getString(R.string.dontSucres) + " " + produit.getDontSucres_produit());
        holder.prodProteinesTxt.setText(context.getString(R.string.proteines) + " " + produit.getProteines_produit());
        holder.prodSelTxt.setText(context.getString(R.string.sel) + " " + produit.getSel_produit());
        holder.prodSodiumTxt.setText(context.getString(R.string.sodium) + " " + produit.getSodium_produit());

        if (Integer.parseInt(produit.getScore_nutritionnel_produit()) < 0)
            holder.prodScoreNutritionnelTxt.setTextColor(Color.parseColor("#009688"));
        else if (Integer.parseInt(produit.getScore_nutritionnel_produit()) < 3)
            holder.prodScoreNutritionnelTxt.setTextColor(Color.parseColor("#8BC34A"));
        else if (Integer.parseInt(produit.getScore_nutritionnel_produit()) < 11)
            holder.prodScoreNutritionnelTxt.setTextColor(Color.parseColor("#FFEB3B"));
        else if (Integer.parseInt(produit.getScore_nutritionnel_produit()) < 19)
            holder.prodScoreNutritionnelTxt.setTextColor(Color.parseColor("#FF9800"));
        else
            holder.prodScoreNutritionnelTxt.setTextColor(Color.parseColor("#F44336"));

        Bitmap imageBitmap  = BitmapFactory.decodeByteArray(produit.getImage_produit(), 0, produit.getImage_produit().length);
        holder.prodImageView.setImageBitmap(imageBitmap);

        return convertView;
    }

    @Override
    public void add(Produit produit) {
        produits.add(produit);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Produit produit) {
        produits.remove(produit);
        notifyDataSetChanged();
        super.remove(produit);
    }
}