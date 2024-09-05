package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class showing_routine extends AppCompatActivity {
    private Spinner select_day, select_year;
    private Button show;
    private EditText srch;
    String[] days;
    String[] year;
    ListView rtnlist;
    List<storing_routine_info> rlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_routine);
        setTitle("ROUTINES");
        select_year = findViewById(R.id.spinner_for_routine_show);
        select_day = findViewById(R.id.spinner_for_routine_show_two);
        show = findViewById(R.id.button_for_showing_routine);
        srch = findViewById(R.id.search_for_teacher);
        rtnlist = findViewById(R.id.listview_for_showing_routine);
        year = new String[]{"Select a Year", "First Year", "Second Year", "Third Year", "Fourth Year"};
        ArrayAdapter<String> spinner_adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, year);
        select_year.setAdapter(spinner_adapter2);
        days = new String[]{"Select a day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,
                R.id.spinner_textid, days);
        select_day.setAdapter(spinner_adapter);
        rlist = new ArrayList<>();
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showroutine();
                // Toast.makeText(showing_routine.this, select_day.getSelectedItem().toString()+" "+select_year.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showroutine() {
        String first = select_year.getSelectedItem().toString();
        String second = select_day.getSelectedItem().toString();
        if (first.equals("Select a Year"))
            Toast.makeText(this, "select a specific year", Toast.LENGTH_SHORT).show();
        else if (second.equals("Select a day"))
            Toast.makeText(this, "select a specific day", Toast.LENGTH_SHORT).show();
        else {
            DatabaseReference rtrdata = FirebaseDatabase.getInstance().getReference("Routine").child(first).child(second);
            rtrdata.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    rlist.clear();
                    for (DataSnapshot rsnapshot : snapshot.getChildren()) {
                        storing_routine_info strinfo = rsnapshot.getValue(storing_routine_info.class);
                        rlist.add(strinfo);
                    }
                    routinelist adapter = new routinelist(showing_routine.this, rlist);
                    rtnlist.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(showing_routine.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });


            srch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String sch = charSequence.toString();
                    List<storing_routine_info> filterls = new ArrayList<>();

                    for (storing_routine_info str : rlist) {
                        if (str.getCourse_teacher().toLowerCase().contains(sch.toLowerCase()))
                            filterls.add(str);
                    }
                    routinelist adapter2 = new routinelist(showing_routine.this, filterls);
                    rtnlist.setAdapter(adapter2);

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


            rtnlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    storing_routine_info listitem = rlist.get(i);
                    String fg = listitem.getCourse_time();
                    Toast.makeText(showing_routine.this, "the course is " + fg, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}