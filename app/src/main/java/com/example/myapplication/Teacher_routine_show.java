package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Teacher_routine_show extends AppCompatActivity {
    private Spinner tselect_day;
    private Button show;
    String[] tday;
    ListView trtnlist;
    private String id;
    List<storing_routine_info> trlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_routine_show);
        tselect_day=findViewById(R.id.teacher_day);
        show=findViewById(R.id.teacher_button_for_showing_routine);
        trtnlist=findViewById(R.id.teacher_listview_for_showing_routine);
        tday=new String[]{"Select a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, tday);
        tselect_day.setAdapter(spinner_adapter);
        trlist=new ArrayList<>();
        Bundle rid=getIntent().getExtras();
         id= rid.getString("id");
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tshowtroutine();
            }
        });

    }
    private void tshowtroutine(){
        String first=tselect_day.getSelectedItem().toString();
        if(first.equals("Select a day"))
            Toast.makeText(this, "select a specific day", Toast.LENGTH_SHORT).show();
        else{

            DatabaseReference rttdta= FirebaseDatabase.getInstance().getReference("Routine").child(id).child(first);
//            Toast.makeText(Teacher_routine_show.this, "working "+id, Toast.LENGTH_SHORT).show();
            rttdta.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot tsnapshot) {
                    trlist.clear();
                    int cnt=0;
                    for(DataSnapshot trsnapshot:tsnapshot.getChildren()){
                        storing_routine_info tstrinfo=trsnapshot.getValue(storing_routine_info.class);
                    //    Toast.makeText(Teacher_routine_show.this, "working "+tstrinfo.getCourse_name(), Toast.LENGTH_SHORT).show();
                        trlist.add(tstrinfo);
                    }

                    routinelist tadapter=new routinelist(Teacher_routine_show.this,trlist);
                    trtnlist.setAdapter(tadapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Teacher_routine_show.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}