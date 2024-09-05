package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class teacher_imageadapter extends RecyclerView.Adapter<teacher_imageadapter.timageholder> {
    private Context context;
    private List<storing_user_info> ls;


     public teacher_imageadapter(Context context, List<storing_user_info> ls) {
        this.context = context;
        this.ls = ls;
    }


    @NonNull
    @Override
    public timageholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.teacher_contacts_items, parent, false);
        return new timageholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull timageholder holder, int position) {
        storing_user_info str = ls.get(position);
        holder.tname.setText(str.getTname());
        holder.tdsg.setText(str.getTdsgn());
        Glide.with(context)
                .load(str.getTimguri())
                .fitCenter()
                .centerCrop()
                .into(holder.timg);
    }



    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class timageholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tname;
        public TextView tdsg;
        public ImageView timg;

         public timageholder(@NonNull View itemView) {
            super(itemView);
            timg = itemView.findViewById(R.id.tcontact_imageid);
            tname = itemView.findViewById(R.id.tcontactname);
            tdsg = itemView.findViewById(R.id.tcontactdesignation);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < ls.size()) {
            storing_user_info selecteditem = ls.get(pos);
//                Toast.makeText(context, " "+selecteditem.getTdsgn(), Toast.LENGTH_SHORT).show();
            Intent chk = new Intent(context, individual_teacher.class);
            chk.putExtra("data",  selecteditem.getTphone());
            itemView.getContext().startActivity(chk);
            }
        }
    }
}
