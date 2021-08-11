package com.a7apps.tvbase.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.a7apps.tvbase.ui.series.tabs.PopSeriesFrag;
import com.a7apps.tvbase.ui.series.tabs.WatchedSeriesFrag;
import com.a7apps.tvbase.ui.series.tabs.WatchlistSeriesFrag;

public class TabAdapSeries extends FragmentStateAdapter {
    private static final int NUM_TABS = 3;

    public TabAdapSeries(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0: fragment = new PopSeriesFrag(); break;
            case 1: fragment = new WatchlistSeriesFrag(); break;
            case 2: fragment = new WatchedSeriesFrag(); break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
