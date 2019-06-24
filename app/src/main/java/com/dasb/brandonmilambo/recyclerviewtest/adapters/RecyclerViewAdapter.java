package com.dasb.brandonmilambo.recyclerviewtest.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dasb.brandonmilambo.recyclerviewtest.R;
import com.dasb.brandonmilambo.recyclerviewtest.ViewHolder.HomeViewHolder;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * It adapts individual list items and adapts them to the main
 * <p>
 * we create a view holder class
 */
//extend recyclerViewAdapter class and specify the adapter type  which is the ViewHolder
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";//log
    private Context mContext;
    private ArrayList<String> mImagesNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    //constructor for the recycler view adapter
    public RecyclerViewAdapter(Context context, ArrayList<String> mImagesNames, ArrayList<String> mImages) {
        this.mContext= context;
        this.mImagesNames = mImagesNames;
        this.mImages = mImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);// create a view holder and pass a view to it
        return holder;// return the view holder
        //THis part is recycling the view and putting them in the position they are supposed to be
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //
        /* this will change based on what your layout is and what i want it to look like
         *this method will get called every time a new item is added to the list
         *
         * First we get the images using glide
         *glide with context and use bitmap as we are dealing with bitmap images
         * and load the image images URL based on position
         *  and the target where we are adding those images . we specify the view and the widget by id
         *  */
        Log.d(TAG, "OnBindViewHolder: called");// just logging every time it gets called

        Glide.with(mContext).asBitmap()
                .load(mImages.get(position))
                .into(holder.image);
        //Set the image names .
        holder.imageName.setText(mImagesNames.get(position));

        // add onclick listener to interact with individual elements and print the name inside a toast notification
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: Clicked on:" + mImagesNames.get(position));
                Toast.makeText(mContext,mImagesNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        // this tells the adapter how many list item are in the list
        return mImagesNames.size();
    }

    // creating viewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        //Hold the widget in memory for each list item individually, we declare all widget withing one list item
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            //Attaching widgets to their IDs
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}




