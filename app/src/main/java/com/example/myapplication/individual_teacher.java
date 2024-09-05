package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class individual_teacher extends AppCompatActivity {

    private TextView nm,dsg,phn;
    private ImageView imgv;
    private DatabaseReference mdataref;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_teacher);
        nm=findViewById(R.id.individual_teacher_name);
        dsg=findViewById(R.id.individual_teacher_dsgn);
        phn=findViewById(R.id.individual_teacher_phone);
        imgv=findViewById(R.id.individual_teacher_image_id);
        setTitle("");

        mdataref= FirebaseDatabase.getInstance().getReference("Contacts").child("Teacher").child("others");

        String tphn = getIntent().getStringExtra("data");
        storing_user_info lstr=new storing_user_info();
        mdataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dts: snapshot.getChildren()){
                    storing_user_info str=dts.getValue(storing_user_info.class);
                    if(tphn.equals(str.getTphone())){
                        s=(String) str.getTname();
                        nm.setText(s);
//                        Toast.makeText(individual_teacher.this, " irteja "+s, Toast.LENGTH_SHORT).show();
                        dsg.setText(str.getTdsgn());
                        phn.setText(str.getTphone());
                        Glide.with(individual_teacher.this)
                                .load(str.getTimguri())
                                .fitCenter()
                                .centerCrop()
                                .into(imgv);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(individual_teacher.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
  //      Toast.makeText(individual_teacher.this, " "+s, Toast.LENGTH_SHORT).show();

//        nm.setText(s);





    }
}