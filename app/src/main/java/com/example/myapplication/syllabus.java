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

public class syllabus extends AppCompatActivity {

    private Spinner spnr,spnrsem;
    private Button btn;
    private ListView lstv;
    private String[] year;
    private String[] semester;
    private List<syllabus_info> ls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        spnr=findViewById(R.id.spinner_for_selecting_year_for_syllabus);
        spnrsem=findViewById(R.id.spinner_for_selecting_semester_for_syllabus);
        btn=findViewById(R.id.button_for_showing_syllabus);
        lstv=findViewById(R.id.listview_for_showing_syllabus);

        setTitle("Syllabus");

        year=new String[]{"Select a Year","First Year","Second Year","Third Year","Fourth Year"};
        semester=new String[]{"Select a Semester","First Semester","Second Semester"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, year);
        spnr.setAdapter(adapter);
        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, semester);
        spnrsem.setAdapter(adapter2);
        ls=new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchsyllabus();
            }
        });

    }

    private void fetchsyllabus() {
        String first=spnr.getSelectedItem().toString();
        String second=spnrsem.getSelectedItem().toString();

        DatabaseReference mdataref= FirebaseDatabase.getInstance().getReference("Syllabus").child(first).child(second);

        mdataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ls.clear();
                Toast.makeText(syllabus.this, "coltase", Toast.LENGTH_SHORT).show();
                for (DataSnapshot ds:snapshot.getChildren()){
                    syllabus_info str=ds.getValue(syllabus_info.class);
                    ls.add(str);
                }
                syllabus_adapter sdc=new syllabus_adapter(syllabus.this,ls);
                lstv.setAdapter(sdc);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(syllabus.this, "Check your Internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}