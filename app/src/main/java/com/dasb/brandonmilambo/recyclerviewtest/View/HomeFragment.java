package com.dasb.brandonmilambo.recyclerviewtest.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dasb.brandonmilambo.recyclerviewtest.R;
import com.dasb.brandonmilambo.recyclerviewtest.adapters.RecyclerViewAdapter;
import com.dasb.brandonmilambo.recyclerviewtest.model.BottomNavState;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class HomeFragment extends BaseNavFragment {
    @Nullable
    //VARS
    // same list as what in the adapter as this  is what will will pass to the adapter
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageURL = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView =  v.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),mNames,mImageURL);
        recyclerView.setAdapter(adapter); //set adapter to the recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//
        /** add images to list**/
        initImageBitmaps();// calling list of images
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateBottomNavListener.updateBottomNav(BottomNavState.HOME);

    }

    // list of images to be called in onCreate
    private void initImageBitmaps() {


        initRecyclerView(); // start the recycler once we have the bitmaps

      //  Log.d(TAG, "initImageBtimaps: preparing bitmaps");

        mImageURL.add("https://media.istockphoto.com/photos/brexit-strategy-concept-picture-id815062310");
        mNames.add("test1");

        mImageURL.add("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?");
        mNames.add("test2");

    }

    private void initRecyclerView() {
       // Log.d(TAG, "initRecyclerView: init RecyclerView");

     //   RecyclerView recyclerView = View.findViewById(R.id.recycler_view);
       // RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageURL);
      //  recyclerView.setAdapter(adapter); //set adapter to the recyclerview
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}

