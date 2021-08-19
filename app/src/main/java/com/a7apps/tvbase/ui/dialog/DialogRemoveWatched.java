package com.a7apps.tvbase.ui.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.a7apps.tvbase.R;
import org.jetbrains.annotations.NotNull;

public class DialogRemoveWatched extends DialogFragment {
    private Context context;
    private Button btnRemWatched;
    private String strId;

    public DialogRemoveWatched(Context context) {
        this.context = context;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_remove_watched,null);

        btnRemWatched = view.findViewById(R.id.btnRemWatched);

        btnRemWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //por ora esse dialog não faz nada.
            }
        });

        return view;
    }
}
