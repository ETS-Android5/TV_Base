package com.a7apps.tvbase.connection;

import android.content.Context;
import android.widget.Toast;
import com.a7apps.tvbase.assistant.AssistantMethods;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Connection implements Connect {
    private Context context;
    private RequestQueue mQueue;
    private AssistantMethods assistant;
    //private String urlId = "https://api.themoviedb.org/3/movie/11873?api_key=8c192694ea899ac35ead1ae82c4d2cda";

    public Connection(Context context) {
        this.context = context;
        mQueue = Volley.newRequestQueue(context);
        assistant = new AssistantMethods(context);
    }

    @Override
    public void primaryRequest(String url, final ArrayList<String> posterArray, final ArrayList<String> idArray) {
        for (int i = 1; i < 4; i++){
            String numPage = String.valueOf(i);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url+numPage, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("results");

                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject res = jsonArray.getJSONObject(i);

                                    String posterPath = res.getString("poster_path");
                                    String id = res.getString("id");

                                    posterArray.add(posterPath);
                                    idArray.add(id);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    assistant.printConnectionError(error);
                }
            });

            mQueue.add(request);
        }
    }

    @Override
    public void secondRequest(String url, ArrayList<String> posterArray, ArrayList<String> idArray) {

    }

    @Override
    public void thirdRequest(String url, final ArrayList<String> posterArray, final ArrayList<String> idArray) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String posterPath = response.getString("poster_path");
                            String strId = response.getString("id");
                            posterArray.add(posterPath);
                            idArray.add(strId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                assistant.printConnectionError(error);
            }
        });
        mQueue.add(request);
    }

    @Override
    public void fourRequest() {

    }

    public String getDados(String urlId){
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(urlId);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String linha;

            while ((linha = bufferedReader.readLine()) != null){
                stringBuilder.append(linha+"\n");
            }
            return stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void jsonDados(String urlId, ArrayList<String> posterArray, ArrayList<String> idArray){
        try {
            JSONObject jsonObject = new JSONObject(getDados(urlId));
            String poster = jsonObject.getString("poster_path");
            String id = jsonObject.getString("id");
            posterArray.add(poster);
            idArray.add(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
