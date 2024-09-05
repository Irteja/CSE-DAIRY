package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;


import java.time.Instant;
import java.util.List;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.imageholder> {
  private Context mcontext;
  private List<uploadimg> muploads;

  public imageAdapter(Context context,List<uploadimg> uploads){
      mcontext=context;
      muploads=uploads;
  }

    @NonNull
    @Override
    public imageholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(mcontext).inflate(R.layout.gallary_items,parent,false);
    return new imageholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull imageholder holder, int position) {
    uploadimg currentup=muploads.get(position);
    holder.imagenam.setText(currentup.getname());
        Glide.with(mcontext)
                .load(currentup.getimguri())
                .fitCenter()
                .centerCrop()
                .into(holder.gallaryimg);
    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class imageholder extends RecyclerView.ViewHolder{
    public TextView imagenam;
    public ImageView gallaryimg;
        public imageholder(@NonNull View itemView) {
            super(itemView);
            imagenam=itemView.findViewById(R.id.gallary_item_text);
            gallaryimg=itemView.findViewById(R.id.gallary_item_images);
        }
    }
}
