package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Slect_Option extends AppCompatActivity {
    private Button adduser,updtimg,updtroutine,delroutine,addnotice,addsylbs,delsylbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(" ");
        setContentView(R.layout.activity_slect_option);
        adduser= findViewById(R.id.add_user);
        updtimg=findViewById(R.id.upload_image);
        updtroutine=findViewById(R.id.routine_update);
        delroutine=findViewById(R.id.delete_routine);
        addnotice=findViewById(R.id.add_notice);
        addsylbs=findViewById(R.id.add_syllabus);
        delsylbs=findViewById(R.id.delete_syllabus);
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Slect_Option.this,choosing_user_to_add.class));
            }
        });
        updtimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imgupload=new Intent(Slect_Option.this,update_and_delete.class);
                imgupload.putExtra("name","Gallery");
                startActivity(imgupload);
            }
        });
        updtroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),routine_update.class));
            }
        });
        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent notice=new Intent(Slect_Option.this,notice_board.class);
                notice.putExtra("name","Notice_board");
                startActivity(notice);
            }
        });
        delroutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    DatabaseReference dltd= FirebaseDatabase.getInstance().getReference("Routine");
//                    dltd.removeValue();
//                    Toast.makeText(Slect_Option.this, "The deletion is completed successfully", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(Slect_Option.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        addsylbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Slect_Option.this,add_syllabus.class));
            }
        });

        delsylbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
//                    DatabaseReference dltslb=FirebaseDatabase.getInstance().getReference("Syllabus");
//                    dltslb.removeValue();
//                    Toast.makeText(Slect_Option.this, "Syllabus is Deleted successfully", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(Slect_Option.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}