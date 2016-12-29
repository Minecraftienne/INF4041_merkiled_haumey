package org.esiea.merkiled_haumey.diet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Onglet3Fragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();
        View view = inflater.inflate(R.layout.fragment_onglet3, container, false);

        final TextView imcNombre = (TextView) view.findViewById(R.id.imcNombre);
        final TextView imcResultat = (TextView) view.findViewById(R.id.imcResultat);

        final EditText taille = (EditText) view.findViewById(R.id.taille);
        final EditText poids = (EditText) view.findViewById(R.id.poids);
        final Button calculer = (Button) view.findViewById(R.id.calculer);

        calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (TextUtils.isEmpty(taille.getText().toString()))
                    Toast.makeText(context, context.getString(R.string.toast_taille_vide), Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(poids.getText().toString()))
                    Toast.makeText(context, context.getString(R.string.toast_poids_vide), Toast.LENGTH_SHORT).show();
                else {
                    double tailleNombre = Integer.valueOf(taille.getText().toString());
                    double poidsNombre = Integer.valueOf(poids.getText().toString());

                    double tailleCM = tailleNombre / 100;

                    double imc = poidsNombre/(tailleCM*tailleCM);

                    imcNombre.setText(context.getString(R.string.imc) + " : " + String.format(Locale.FRANCE, "%.1f", imc));

                    if (imc < 16.5)
                        imcResultat.setText(R.string.imc1);
                    else if (imc < 18.5)
                        imcResultat.setText(R.string.imc2);
                    else if (imc < 25)
                        imcResultat.setText(R.string.imc3);
                    else if (imc < 30)
                        imcResultat.setText(R.string.imc4);
                    else if (imc < 35)
                        imcResultat.setText(R.string.imc5);
                    else if (imc < 40)
                        imcResultat.setText(R.string.imc6);
                    else
                        imcResultat.setText(R.string.imc7);
                }
            }
        });

        return view;
    }
}
