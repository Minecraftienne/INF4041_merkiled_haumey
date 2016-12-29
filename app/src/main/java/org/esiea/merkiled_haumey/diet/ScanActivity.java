package org.esiea.merkiled_haumey.diet;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class ScanActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 1;
    public final static String EXTRA_MESSAGE = "org.esiea.merkiled_haumey.diet.MESSAGE";
    public static String nom = "";
    public static String scoreNutritionnel = "", energie = "", matieresGrassesLipides = "", dontAcidesGrasSatures = "",
            glucides = "", dontSucres = "", proteines = "", sel = "", sodium = "";

    public Bitmap image;
    public String imageUrl;
    public static byte[] imageByte;
    private String url;

    public static boolean trouver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        trouver = false;

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.setPrompt(getString(R.string.scan_barcode));
        integrator.setResultDisplayDuration(0);
        integrator.setWide(); // sélection en rectangle
        integrator.setCameraId(0); // spécification de la caméra de l'appareil
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // résultat du scan
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {

            String scanContent = scanningResult.getContents(); // numéro de code barre

            url = "http://fr.openfoodfacts.org/api/v0/produit/" + scanContent + ".json";

            JSONParser jsonParser = new JSONParser();

            if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(ScanActivity.this, Manifest.permission.INTERNET)) {
                    Toast.makeText(ScanActivity.this, ScanActivity.this.getString(R.string.toast_permission_internet), Toast.LENGTH_SHORT).show();
                }
                // demande de permission
                ActivityCompat.requestPermissions(ScanActivity.this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_INTERNET);
            }
            new JSONParse().execute();
        }
        else {
            Toast.makeText(getApplicationContext(), ScanActivity.this.getString(R.string.toast_no_scan), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(ScanActivity.this);
            pDialog.setMessage(getString(R.string.recup_infos));
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            // récupération du JSON par l'URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            String nomGenericName = null;
            String nomProductName = null;
            try {
                JSONObject structure = (JSONObject) json.get("product");
                nomGenericName = new String (structure.getString("generic_name_fr").getBytes("ISO-8859-1"), "UTF-8"); // pour que les caractères accentués s'affichent bien
                nomProductName = new String (structure.getString("product_name").getBytes("ISO-8859-1"), "UTF-8");

                JSONObject objet = (JSONObject) structure.getJSONObject("nutriments");

                scoreNutritionnel = new String (objet.getString("nutrition-score-fr").getBytes("ISO-8859-1"), "UTF-8");
                energie = new String (objet.getString("energy").getBytes("ISO-8859-1"), "UTF-8");
                matieresGrassesLipides = new String (objet.getString("fat").getBytes("ISO-8859-1"), "UTF-8");
                dontAcidesGrasSatures = new String (objet.getString("saturated-fat_value").getBytes("ISO-8859-1"), "UTF-8");
                glucides = new String (objet.getString("carbohydrates").getBytes("ISO-8859-1"), "UTF-8");
                dontSucres = new String (objet.getString("sugars").getBytes("ISO-8859-1"), "UTF-8");
                proteines = new String (objet.getString("proteins").getBytes("ISO-8859-1"), "UTF-8");
                sel = new String (objet.getString("salt").getBytes("ISO-8859-1"), "UTF-8");
                sodium = new String (objet.getString("sodium").getBytes("ISO-8859-1"), "UTF-8");

                imageUrl = new String (structure.getString("image_small_url"));
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (imageUrl != null)
                new ImageUrl().execute();

            if (nomGenericName != null && !nomGenericName.isEmpty()) {
                nom = nomGenericName;
                trouver = true;
            }
            else if (nomProductName != null && !nomProductName.isEmpty()) {
                nom = nomProductName;
                trouver = true;
            }
            else {
                Toast.makeText(getApplicationContext(), ScanActivity.this.getString(R.string.toast_no_produit), Toast.LENGTH_SHORT).show();
                trouver = false;
            }

            finish();
        }
    }

    private class ImageUrl extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... arg0) {
            try {
                image = BitmapFactory.decodeStream((InputStream) new URL(imageUrl).getContent());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 0, stream);
                imageByte = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
