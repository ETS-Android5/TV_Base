package com.a7apps.tvbase.ui.customlists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.a7apps.tvbase.R;

public class CustomListsFragment extends Fragment {
    private TextView textView;
    private ImageButton btnAddCustomList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_lists, container, false);
        textView = view.findViewById(R.id.txtEmptyCustomLists);
        btnAddCustomList = view.findViewById(R.id.btnAddCustomList);



        return view;
    }
}