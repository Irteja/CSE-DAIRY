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

public class teacher_log_in extends AppCompatActivity {
  private EditText name,id;
  private Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_log_in);
        setTitle("Admin Log In");
        name=(EditText) findViewById(R.id.tname);
        id=(EditText) findViewById(R.id.tid);
        log=(Button) findViewById(R.id.tlgn);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String s=name.getText().toString();
                    String x=id.getText().toString();
                    if(TextUtils.isEmpty(s) || TextUtils.isEmpty(x)){
                        Toast.makeText(teacher_log_in.this,   "Fill both the text fields", Toast.LENGTH_SHORT).show();
                    }
                    else if(s.equals("admin") && x.equals("pass23"))
                    {
                        Intent i=new Intent(getApplicationContext(),menupage.class);
                        startActivity(i);
                    }
                    else{
                        DatabaseReference dataref= FirebaseDatabase.getInstance().getReference("Users").child("Teacher");
                        Query checkusr=dataref.orderByChild("roll_or_id").equalTo(s);

                        checkusr.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String pass=snapshot.child(s).child("pass_or_reg").getValue(String.class);
                                    if(pass.equals(x)){
//                     Toast.makeText(teacher_log_in.this,"woorking",Toast.LENGTH_SHORT).show();
                                        Intent teachermenu=new Intent(teacher_log_in.this,Teacher_menu_page.class);
                                        teachermenu.putExtra("id",s);
                                        startActivity(teachermenu);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(teacher_log_in.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }


            }
        });
    }
}