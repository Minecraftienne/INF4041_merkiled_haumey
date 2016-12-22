package org.esiea.merkiled_haumey.diet;

import java.text.SimpleDateFormat;
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
            holder.prodImageView = (ImageView) convertView.findViewById(R.id.imageProduit);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Produit produit = (Produit) getItem(position);

        holder.prodNomTxt.setText(produit.getNom_produit());

        Bitmap imageBitmap  = BitmapFactory.decodeByteArray(produit.getImage_produit(), 0, produit.getImage_produit().length);
        holder.prodImageView.setImageBitmap(imageBitmap);


              //  holder.prodDateTxt.setTextColor(Color.parseColor("#FF5722"));
              //  holder.prodDateTxt.setTextColor(Color.RED);
             //   holder.prodDateTxt.setTextColor(Color.parseColor("#4CAF50"));

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