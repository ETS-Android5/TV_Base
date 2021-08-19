package com.a7apps.tvbase.connection;

import android.content.Context;
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
import java.util.ArrayList;

public class Connection implements Connect {
    private Context context;
    private RequestQueue mQueue;
    private AssistantMethods assistant;

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
    public void thirdRequest(String url, final ArrayList<String> posterArray) {

    }

    @Override
    public void fourRequest() {

    }
}
