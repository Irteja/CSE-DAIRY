package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
     TextView txt;
     Button bt,btw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(" ");
        txt=(TextView) findViewById(R.id.qstx);
        bt=(Button) findViewById(R.id.btn);
        btw=(Button) findViewById(R.id.ttt);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Student",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),student_menu_page.class);
                startActivity(i);
            }
        });

        btw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Teacher",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),teacher_log_in.class);
                startActivity(i);
            }
        });
    }
}