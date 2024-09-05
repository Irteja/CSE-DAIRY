package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class routine_update extends AppCompatActivity {
    private Spinner select_day,select_year;
    private EditText course_name, course_schedule, course_teacher_id;
    private Button routineupbtn;
    String[] routineday;
    String[] year;
    DatabaseReference routineup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_update);
        setTitle("UPDATE ROUTINE");
        select_day = findViewById(R.id.routine_update_spinner);
        select_year=findViewById(R.id.routine_update_spinner_two);
        course_name = findViewById(R.id.routine_course_name);
        course_schedule = findViewById(R.id.routine_course_time);
        course_teacher_id = findViewById(R.id.routine_course_teacher);
        routineupbtn = findViewById(R.id.routine_update_button);
        routineday = new String[]{"Select a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        year=new String[]{"Select a Year","First Year","Second Year","Third Year","Fourth Year"};
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, routineday);
        select_day.setAdapter(spinner_adapter);
        ArrayAdapter<String> spinner_adapter2= new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, year);
        select_year.setAdapter(spinner_adapter2);
        routineupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uprutn();
            }
        });
    }

    private void uprutn() {
        routineup = FirebaseDatabase.getInstance().getReference("Routine");
        String s=select_day.getSelectedItem().toString();
        String x=select_year.getSelectedItem().toString();
        String first=course_name.getText().toString();
        String second=course_schedule.getText().toString();
        String third=course_teacher_id.getText().toString();
        if(s.equals("Select a day"))
            Toast.makeText(routine_update.this,   "Select a specific day", Toast.LENGTH_SHORT).show();
        else if(x.equals("Select a Year"))
            Toast.makeText(routine_update.this,   "Select a specific year ", Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(first) || TextUtils.isEmpty(second) || TextUtils.isEmpty(third))
            Toast.makeText(routine_update.this,   "Please fill all the text fields", Toast.LENGTH_SHORT).show();
        else{
            routineup=routineup.child(x).child(s);
            storing_routine_info str=new storing_routine_info(first,second,third);
            routineup.push().setValue(str);
            Toast.makeText(routine_update.this,   "The course is added to the routine", Toast.LENGTH_SHORT).show();
        }

    }
}