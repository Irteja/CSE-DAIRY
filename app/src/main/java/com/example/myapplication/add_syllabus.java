package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_syllabus extends AppCompatActivity {

    private Spinner spnryr,spnrsem;
    private EditText crsnm,crsdtl;
    private Button btn;
    private String[] year;
    private String[] semester;
    private DatabaseReference mdataref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_syllabus);

        spnryr=findViewById(R.id.spinner_for_select_year_for_adding);
        spnrsem=findViewById(R.id.spinner_for_selecting_for_semester);
        crsnm=findViewById(R.id.syllabus_course_name);
        crsdtl=findViewById(R.id.syllabus_for_course_details);
        btn=findViewById(R.id.syllabus_add_button);
        setTitle("Add Syllabus");
        year=new String[]{"Select a Year","First Year","Second Year","Third Year","Fourth Year"};
        semester=new String[]{"Select a Semester","First Semester","Second Semester"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, year);
        spnryr.setAdapter(adapter);

        ArrayAdapter<String> adapter2= new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, semester);
        spnrsem.setAdapter(adapter2);

        mdataref= FirebaseDatabase.getInstance().getReference("Syllabus");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path=spnryr.getSelectedItem().toString();
                String path2=spnrsem.getSelectedItem().toString();
                String first=crsnm.getText().toString();
                String second=crsdtl.getText().toString();
                if(path.equals("Select a Year"))
                    Toast.makeText(add_syllabus.this, "select a specific year", Toast.LENGTH_SHORT).show();
                else{
                    first+=":";
                    syllabus_info str=new syllabus_info(first,second);
                    mdataref.child(path).child(path2).push().setValue(str);
                    Toast.makeText(add_syllabus.this,"Syllabus Updated successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}