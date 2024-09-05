package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Student_log_in extends AppCompatActivity {
   EditText roll,reg;
   Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_in);
        setTitle("Student Log in");
        roll=(EditText) findViewById(R.id.sturoll);
        reg=(EditText) findViewById(R.id.stureg);
        log=(Button) findViewById(R.id.stulgn);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=roll.getText().toString();
                String x=reg.getText().toString();
                if(TextUtils.isEmpty(s) || TextUtils.isEmpty(x)){
                    Toast.makeText(Student_log_in.this,   "Fill both the text fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    DatabaseReference dataref= FirebaseDatabase.getInstance().getReference("Users").child("Student");
                    Query checkusr=dataref.orderByChild("roll_or_id").equalTo(s);

                    checkusr.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                String pass=snapshot.child(s).child("pass_or_reg").getValue(String.class);
                                if(pass.equals(x)){
                                    Intent studentmenu=new Intent(Student_log_in.this,student_menu_page.class);
                                    startActivity(studentmenu);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Student_log_in.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}