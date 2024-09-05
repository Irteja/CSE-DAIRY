package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class individual_student_contact extends AppCompatActivity {

    private TextView name, roll, reg, session, phone,peaddrs,prsaddrs;
    private ImageView simg;
    private String gt;
    private DatabaseReference mdatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_student_contact);
        name = findViewById(R.id.individual_student_name);
        roll = findViewById(R.id.individual_student_roll);
        reg = findViewById(R.id.individual_student_reg);
        session = findViewById(R.id.individual_student_session);
        phone = findViewById(R.id.individual_student_phone);
        simg=findViewById(R.id.individual_student_image);
        peaddrs=findViewById(R.id.individual_student_permanent_address);
        prsaddrs=findViewById(R.id.individual_student_present_address);
        setTitle("");

        gt = getIntent().getStringExtra("student phone");

        mdatabaseref = FirebaseDatabase.getInstance().getReference("Contacts").child("Student");

        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    student_info str = ds.getValue(student_info.class);
                    if (gt.equals(str.getPhone())) {
                        name.setText(str.getName());
                        roll.setText(str.getRoll());
                        reg.setText(str.getReg());
                        session.setText(str.getSession());
                        phone.setText(str.getPhone());
                        peaddrs.setText(str.getPeaddrs());
                        prsaddrs.setText(str.getPrsaddrs());
                        Glide.with(individual_student_contact.this)
                                .load(str.getSimguri())
                                .fitCenter()
                                .centerCrop()
                                .into(simg);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(individual_student_contact.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

    }
}