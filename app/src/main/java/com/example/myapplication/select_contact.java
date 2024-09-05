package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select_contact extends AppCompatActivity {

    private Button thr,std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contact);
        thr=findViewById(R.id.select_teacher);
        std=findViewById(R.id.select_student);
        setTitle("");

        thr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_contact.this,teacher_contacts.class));
            }
        });
        std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(select_contact.this,show_student_contacts.class));
            }
        });
    }
}