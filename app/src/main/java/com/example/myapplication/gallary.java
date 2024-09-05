package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gallary extends AppCompatActivity {
private RecyclerView mrecycler;
private imageAdapter mimgadapter;

private DatabaseReference mdataref;
private List<uploadimg> muploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary);
        mrecycler=findViewById(R.id.gallary_recycleview);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(this));

        muploads=new ArrayList<>();

        Bundle bde=getIntent().getExtras();
        String path= bde.getString("nametwo");
        if(path.equals("Gallery"))
        {
            setTitle("Gallery");
            mdataref= FirebaseDatabase.getInstance().getReference(path);
        }
        else
        {
            setTitle(path);
            mdataref= FirebaseDatabase.getInstance().getReference("Notice").child(path);
        }



        mdataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot pos : snapshot.getChildren()){
                uploadimg upld=pos.getValue(uploadimg.class);
                muploads.add(upld);
            }
            Collections.reverse(muploads);
            mimgadapter=new imageAdapter(gallary.this,muploads);
            mrecycler.setAdapter(mimgadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(gallary.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}