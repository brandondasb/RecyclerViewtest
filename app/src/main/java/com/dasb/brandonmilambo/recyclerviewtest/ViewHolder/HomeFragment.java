package com.dasb.brandonmilambo.recyclerviewtest.ViewHolder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasb.brandonmilambo.recyclerviewtest.R;
import com.dasb.brandonmilambo.recyclerviewtest.model.BottomNavState;

public class HomeFragment extends BaseNavFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        updateBottomNavListener.updateBottomeNav(BottomNavState.HOME);

    }
}

