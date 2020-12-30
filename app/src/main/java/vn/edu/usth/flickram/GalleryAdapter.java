package vn.edu.usth.flickram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatImageView;


import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.CustomViewHolder> {
    private final List<String> mUrlList;
    private final Activity mActivity;

    public GalleryAdapter(Activity activity, List<String> urllist){
        mActivity = activity;
        mUrlList = urllist;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        final AppCompatImageView imageResource;
        CustomViewHolder (View itemView){
            super(itemView);
            this.imageResource = (AppCompatImageView) itemView.findViewById(R.id.image_resource);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_list_row, parent, false);
        return new GalleryAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position){
        Glide
                .with(mActivity)
                .load(mUrlList.get(position))
                .override(200,200)
                .centerCrop()
                .into(holder.imageResource);

        final int itemPosition = holder.getAdapterPosition();
        holder.imageResource.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(mActivity, mUrlList.get(itemPosition),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return mUrlList.size();
    }
}
