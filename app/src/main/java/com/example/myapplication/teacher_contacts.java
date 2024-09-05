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

public class teacher_contacts extends AppCompatActivity {
    private RecyclerView mrecycler;
    private teacher_imageadapter mimgadapter;
    private DatabaseReference mdataref;
    private List<storing_user_info> muploads;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_contacts);
        mrecycler = findViewById(R.id.teacher_contacts_recyclerview);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(this));
        setTitle("Teacher Contacts");

        muploads = new ArrayList<>();

        mdataref = FirebaseDatabase.getInstance().getReference("Contacts").child("Teacher").child("head");

        mdataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                muploads.clear();
                for (DataSnapshot dts : snapshot.getChildren()) {
                    storing_user_info str = dts.getValue(storing_user_info.class);
                    s = str.getTphone();
                    str.setTdsgn(str.getTdsgn() + " (Head Of the Department)");
                    muploads.add(str);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(teacher_contacts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        mdataref = FirebaseDatabase.getInstance().getReference("Contacts").child("Teacher").child("others");

        mdataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dts : snapshot.getChildren()) {
                    storing_user_info str = dts.getValue(storing_user_info.class);
                    if (!s.equals(str.getTphone()) )
                        muploads.add(str);
                }
                mimgadapter = new teacher_imageadapter(teacher_contacts.this, muploads);
                mrecycler.setAdapter(mimgadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(teacher_contacts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}