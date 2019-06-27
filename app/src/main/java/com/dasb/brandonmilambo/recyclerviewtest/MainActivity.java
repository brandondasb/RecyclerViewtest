package com.dasb.brandonmilambo.recyclerviewtest;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dasb.brandonmilambo.recyclerviewtest.adapters.RecyclerViewAdapter;
import com.dasb.brandonmilambo.recyclerviewtest.interfaces.UpdateBottomNavListener;
import com.dasb.brandonmilambo.recyclerviewtest.model.BottomNavState;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //***vars***

    private FragmentManager  fragmentManager = getSupportFragmentManager();




    // same list as what in the adapter as this  is what will will pass to the adapter
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageURL = new ArrayList<>();

    private UpdateBottomNavListener bottomNavListener = new UpdateBottomNavListener() {
        @Override
        public void updateBottomNav(BottomNavState currentState) {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            switch(currentState){
                case HOME:
                    bottomNavigationView.setSelectedItemId(R.id.nav_home);
                    break;
                case VISIT:
                    bottomNavigationView.setSelectedItemId(R.id.nav_visits);
                    break;
                case INBOX:
                    bottomNavigationView.setSelectedItemId(R.id.nav_inbox);
                    break;
                case PROFILE:
                    bottomNavigationView.setSelectedItemId(R.id.nav_profile);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:Started.");
        initImageBitmaps();// calling list of images

    }
    // list of images to be called in oncreate
    private  void initImageBitmaps(){
        Log.d(TAG,"initImageBtimaps: preparing bitmaps");
        mImageURL.add("https://media.istockphoto.com/photos/brexit-strategy-concept-picture-id815062310");
        mNames.add("test1");

        mImageURL.add("https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?");
        mNames.add("test2");


        initRecyclerView(); // start the recycler once we have the bitmaps
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RecyclerView");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,mNames,mImageURL);
        recyclerView.setAdapter(adapter); //set adapter to the recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
