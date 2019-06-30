package com.dasb.brandonmilambo.recyclerviewtest.model;

import android.support.v4.app.Fragment;

import com.dasb.brandonmilambo.recyclerviewtest.interfaces.UpdateBottomNavListener;

public class BaseNavFragment extends Fragment {
    protected UpdateBottomNavListener updateBottomNavListener;
    public void setUpdateBottomNavListener(UpdateBottomNavListener updateBottomNavListener){
        this.updateBottomNavListener = updateBottomNavListener;
    }
}
