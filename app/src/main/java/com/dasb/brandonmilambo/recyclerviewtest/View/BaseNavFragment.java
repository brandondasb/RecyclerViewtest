package com.dasb.brandonmilambo.recyclerviewtest.View;

import android.support.v4.app.Fragment;

import com.dasb.brandonmilambo.recyclerviewtest.interfaces.UpdateBottomNavListener;

public abstract class BaseNavFragment extends Fragment {
    protected UpdateBottomNavListener updateBottomNavListener;

    public void setUpdateBottomNavListener(UpdateBottomNavListener updateBottomNavListener){
        this.updateBottomNavListener = updateBottomNavListener;
    }
}
