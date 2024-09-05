package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class studentcontactlist extends RecyclerView.Adapter<studentcontactlist.simageholder> {
    private Context context;
    private List<student_info> ls;
    String s;



    public studentcontactlist(Context context, List<student_info> ls) {
        this.context = context;
        this.ls = ls;
    }


    @NonNull
    @Override
    public simageholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.teacher_contacts_items, parent, false);
        return new simageholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull simageholder holder, int position) {
        student_info str=ls.get(position);
        holder.sname.setText(str.getName());
        holder.ssession.setText(str.getSession());
        Glide.with(context)
                .load(str.getSimguri())
                .fitCenter()
                .centerCrop()
                .into(holder.simg);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class simageholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView sname,ssession;
        public ImageView simg;

        public simageholder(@NonNull View itemView) {
            super(itemView);
            simg=itemView.findViewById(R.id.tcontact_imageid);
            sname= itemView.findViewById(R.id.tcontactname);
            ssession= itemView.findViewById(R.id.tcontactdesignation);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < ls.size()) {
                student_info selecteditem = ls.get(pos);
                Intent chk = new Intent(context, individual_student_contact.class);
                chk.putExtra("student phone",  selecteditem.getPhone());
                itemView.getContext().startActivity(chk);
            }

        }
    }

}
