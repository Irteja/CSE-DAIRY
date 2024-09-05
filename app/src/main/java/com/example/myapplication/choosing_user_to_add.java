package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choosing_user_to_add extends AppCompatActivity {
    private Button teacheroption,studentoption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_user_to_add);
        setTitle(" ");
        teacheroption=findViewById(R.id.option_teacher);
        studentoption=findViewById(R.id.option_student);

        teacheroption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(choosing_user_to_add.this,add_teacher_user.class));
            }
        });
        studentoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(choosing_user_to_add.this,ADD_USER.class));
            }
        });
    }
}