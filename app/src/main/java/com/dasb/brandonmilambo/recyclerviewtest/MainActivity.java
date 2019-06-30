package com.dasb.brandonmilambo.recyclerviewtest;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dasb.brandonmilambo.recyclerviewtest.View.HomeFragment;
import com.dasb.brandonmilambo.recyclerviewtest.View.InboxFragment;
import com.dasb.brandonmilambo.recyclerviewtest.View.ProfileFragment;
import com.dasb.brandonmilambo.recyclerviewtest.View.VisitsFragment;
import com.dasb.brandonmilambo.recyclerviewtest.adapters.RecyclerViewAdapter;
import com.dasb.brandonmilambo.recyclerviewtest.interfaces.UpdateBottomNavListener;
import com.dasb.brandonmilambo.recyclerviewtest.model.BaseNavFragment;
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

        //declaring bottom nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);// adding bespoke listener to on item selected
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setUpdateBottomNavListener(bottomNavListener);
        fragmentTransaction.replace(R.id.fragment_container, homeFragment).commit();

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bottom_navigation_bar, menu);
        return true;
        // confirm the purpose what would happening without this method?
    }
    // create a listener for the bottom navigation
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    BaseNavFragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home:
                        if (fragmentManager.findFragmentByTag(HomeFragment.class.getName())!= null){
                            selectedFragment = (HomeFragment) fragmentManager.findFragmentByTag(HomeFragment.class.getName());
                        }
                            break;
                        case R.id.nav_visits:
                            if (fragmentManager.findFragmentByTag(VisitsFragment.class.getName()) != null) {
                                selectedFragment = (VisitsFragment) fragmentManager.findFragmentByTag(VisitsFragment.class.getName());
                            } else {
                                selectedFragment = new VisitsFragment();
                            }
                            break;
                        case R.id.nav_inbox:
                            if (fragmentManager.findFragmentByTag(InboxFragment.class.getName()) != null) {
                                selectedFragment = (InboxFragment) fragmentManager.findFragmentByTag(InboxFragment.class.getName());
                            } else {
                                selectedFragment = new InboxFragment();
                            }
                            break;
                        case R.id.nav_profile:
                            if (fragmentManager.findFragmentByTag(ProfileFragment.class.getName()) != null) {
                                selectedFragment = (ProfileFragment) fragmentManager.findFragmentByTag(ProfileFragment.class.getName());
                            } else {
                                selectedFragment = new ProfileFragment();
                            }
                            break;
                        //this creates the fragment on item selected but its not showing yet.

                    }
                    selectedFragment.setUpdateBottomNavListener(bottomNavListener);
                    return  loadFragment(selectedFragment);
                }
            };

    private boolean loadFragment(final BaseNavFragment fragment) {
        if (isNotInBackStack(fragmentManager,fragment))   {
            getSupportFragmentManager()
                   .beginTransaction()
                    .replace(R.id.fragment_container,fragment,fragment.getClass().getName())
                    .addToBackStack(fragment.getClass().getName())
                    .commitAllowingStateLoss();
            } else{
            fragmentManager.popBackStack(fragment.getClass().getName(),0);
        }
        return true;
    }

    public static boolean isNotInBackStack(FragmentManager fragmentManager,Fragment existingFragment)   {
        boolean result = true;
        if (existingFragment!=  null){
            int backStackSize = fragmentManager.getBackStackEntryCount();       //gett the size of current backstack
            for (int i = 0;i<backStackSize;i++)  {
                FragmentManager.BackStackEntry backStackEntry = fragmentManager.getBackStackEntryAt(i);
                if (existingFragment.getClass().getName().equals(backStackEntry.getName())){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount()>1){
            fragmentManager.popBackStack(HomeFragment.class.getName(),0);
           }else{
            this.finish();
        }
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
