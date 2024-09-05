package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class show_student_contacts extends AppCompatActivity {

    private RecyclerView mrecycler;
    private List<student_info> slist;
    private EditText srch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_contacts);
        mrecycler=findViewById(R.id.student_contacts_recyclerview);
        srch=findViewById(R.id.search_student_contacts);
        mrecycler.setHasFixedSize(true);
        mrecycler.setLayoutManager(new LinearLayoutManager(this));
        slist=new ArrayList<>();
        setTitle("Student Contacts");
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference chk = FirebaseDatabase.getInstance().getReference("Contacts").child("Student");
        chk.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    student_info strn = ds.getValue(student_info.class);
                    slist.add(strn);
                }
                studentcontactlist scla = new studentcontactlist(show_student_contacts.this, slist);
                mrecycler.setAdapter(scla);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        srch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s=charSequence.toString();
                List<student_info>  fltls=new ArrayList<>();

                for(student_info stn : slist){
                    if(stn.getSession().toLowerCase().contains(s.toLowerCase()))
                        fltls.add(stn);
                }
                studentcontactlist adpt=new studentcontactlist(show_student_contacts.this,fltls);
                mrecycler.setAdapter(adpt);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}