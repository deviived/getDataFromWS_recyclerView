package news.airweb.fr.testandroidairweb;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import news.airweb.fr.testandroidairweb.controller.MainActivity;
import news.airweb.fr.testandroidairweb.model.News;

public class AirwebNewsWebService {

    private final String NewsJson = "https://airweb-demo.airweb.fr/psg/psg.json";

    private Gson gson;
    private String result = "";
    JSONArray jsonArray;
    String jsonString;
    List<News> test1;

    public AirwebNewsWebService() {
        gson = new Gson();
    }

    private InputStream sendRequest(URL url) throws Exception {

        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("httpOK");
                return urlConnection.getInputStream();
            }

        }catch (UnknownHostException e) {
        System.out.println("Check Internet Connection!!!");}
        catch (MalformedURLException e) {
            System.out.println("Check URL!!!");
        }
        catch (Exception e) {
            result += e.toString();
            System.out.println(result);
            throw new Exception("");
        }
        return null;
    }

    public List<News> getPoints() {

        try {
            System.out.println("beforeRequest");
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(NewsJson));

            // Vérification de l'inputStream
            if(inputStream != null) {

                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);
                System.out.println("inputStreamReader");

                // Récupère le JSON en string
                BufferedReader br = new BufferedReader(reader);
                String read = "";
                StringBuilder sb = new StringBuilder(read);
                while((read = br.readLine()) != null) {
                    sb.append(read);
                }
                String response = sb.toString();

                JSONObject jsonObject = new JSONObject(response);
                System.out.println("jsonObject : "+jsonObject);
                System.out.println("jsonObject1 : "+jsonObject.get("news"));
                jsonArray = jsonObject.getJSONArray("news");
                jsonString = jsonArray.toString();
                System.out.println("jsonArray : "+jsonArray);
                System.out.println("jsonString : "+jsonString);

                test1 = gson.fromJson(jsonString, new TypeToken<List<News>>(){}.getType());
                System.out.println("test1 : "+test1);
                // Retourne la liste désérialisée par le moteur GSON
                return test1;

            }

        } catch (Exception e) {
            System.out.println("catch1");
            Log.e("WebService", "Impossible de rapatrier les données :(");
        }
        System.out.println("endoftry1");
        return test1;
    }
}
