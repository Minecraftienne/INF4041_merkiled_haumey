package org.esiea.merkiled_haumey.diet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Onglet2Fragment extends Fragment {

    ListView liste;
    Context context;
    boolean scan;
    byte[] image_produit, image_defaut;
    String nom_produit, score_nutritionnel_produit, energie_produit, matieresGrassesLipides_produit, dontAcidesGrasSatures_produit,
            glucides_produit, dontSucres_produit, proteines_produit, sel_produit, sodium_produit;
    Produit p;
    ArrayList<Produit> array_list = new ArrayList<Produit>();
    ListeProduitsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();
        View view = inflater.inflate(R.layout.fragment_onglet2, container, false);

        liste = (ListView) view.findViewById(R.id.listView);

        adapter = new ListeProduitsAdapter(context, array_list);
        liste.setAdapter(adapter);

        Button scanner = (Button) view.findViewById(R.id.scanner);

        scan = false;

        // image par défaut
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, stream);
        image_defaut = stream.toByteArray();

        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (isNetworkAvaliable(context)) {
                    Intent intent = new Intent(context, ScanActivity.class);
                    scan = true;
                    startActivity(intent);
                }
                else
                    Toast.makeText(context, context.getString(R.string.toast_internet), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @SuppressWarnings("deprecation")
    public static boolean isNetworkAvaliable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ((connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (scan && ScanActivity.trouver) {

            image_produit = ScanActivity.imageByte;
            nom_produit = ScanActivity.nom;
            score_nutritionnel_produit = ScanActivity.scoreNutritionnel;
            energie_produit = ScanActivity.energie;
            matieresGrassesLipides_produit = ScanActivity.matieresGrassesLipides;
            dontAcidesGrasSatures_produit = ScanActivity.dontAcidesGrasSatures;
            glucides_produit = ScanActivity.glucides;
            dontSucres_produit = ScanActivity.dontSucres;
            proteines_produit = ScanActivity.proteines;
            sel_produit = ScanActivity.sel;
            sodium_produit = ScanActivity.sodium;

            if (image_produit != null)
                p = new Produit(nom_produit, image_produit, score_nutritionnel_produit, energie_produit,
                        matieresGrassesLipides_produit, dontAcidesGrasSatures_produit, glucides_produit,
                        dontSucres_produit, proteines_produit, sel_produit, sodium_produit);
            else
                p = new Produit(nom_produit, image_defaut, score_nutritionnel_produit, energie_produit,
                        matieresGrassesLipides_produit, dontAcidesGrasSatures_produit, glucides_produit,
                        dontSucres_produit, proteines_produit, sel_produit, sodium_produit);

            adapter.add(p);

            scan = false;
        }
    }
}
