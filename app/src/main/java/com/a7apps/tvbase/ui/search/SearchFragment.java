package com.a7apps.tvbase.ui.search;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.a7apps.tvbase.R;
import com.a7apps.tvbase.adapter.RVAdap;
import com.a7apps.tvbase.assistant.AssistantMethods;
import com.a7apps.tvbase.assistant.Constants;
import com.a7apps.tvbase.connection.Connect;
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

public class SearchFragment extends Fragment implements Connect {
    private EditText editText;
    private RecyclerView recyclerView;
    private RVAdap RVAdap;
    private ArrayList<String> arrayPosterPath = new ArrayList<>();
    private ArrayList<String> arrayId = new ArrayList<>();
    private ArrayList<String> arrayMediaType = new ArrayList<>();
    private RequestQueue mQueue;
    private AssistantMethods assistant;
    private ProgressBar progress;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editText = view.findViewById(R.id.edtSearch);
        recyclerView = view.findViewById(R.id.rvSearch);
        progress = view.findViewById(R.id.pbSearch);
        mQueue = Volley.newRequestQueue(getContext());
        assistant = new AssistantMethods(getContext());
        editText.requestFocus();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 2){
                    progress.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            arrayPosterPath.clear();
                            secondRequest(Constants.getBaseSearchUrl()+searchQuery(), arrayPosterPath, arrayId);
                            //Aqui ele vai precisar de um arrayTypemedia em vez de Constants.Type_general
                            RVAdap = new RVAdap(getActivity().getApplicationContext(), arrayPosterPath, arrayId, getParentFragmentManager(), Constants.TYPE_GENERAL);
                            RVAdap.notifyDataSetChanged();
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setAdapter(RVAdap);
                                    progress.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                    }).start();
                }
            }
        });

        return view;
    }

    public String searchQuery(){
        String query = editText.getText().toString();
        String formatQuery = query.replaceAll("\\s", "+");
        return formatQuery;
    }

    @Override
    public void primaryRequest(String url, ArrayList<String> posterArray, final ArrayList<String> idArray) {

    }

    @Override
    public void secondRequest(String url, ArrayList<String> posterArray, ArrayList<String> idArray) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
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

    @Override
    public void thirdRequest(String url, final ArrayList<String> posterArray) {

    }

    @Override
    public void fourRequest() {

    }
}