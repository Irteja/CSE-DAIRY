package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class student_menu_page extends AppCompatActivity implements View.OnClickListener {
    private CardView gallary, notice_board, routine, alarm, info,sbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CSE DAIRY");
        setContentView(R.layout.activity_student_menu_page);
        gallary = findViewById(R.id.student_gallary);
        notice_board = findViewById(R.id.student_notice_board);
        routine = findViewById(R.id.student_routine);
        alarm = findViewById(R.id.student_alarm);
        info = findViewById(R.id.student_deptinfo);
        sbus=findViewById(R.id.student_syllabus);
        sbus.setOnClickListener(this);
        gallary.setOnClickListener(this);
        notice_board.setOnClickListener(this);
        routine.setOnClickListener(this);
        alarm.setOnClickListener(this);
        info.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater minflt=getMenuInflater();
        minflt.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==R.id.admin_menu)
        {
            startActivity(new Intent(student_menu_page.this,teacher_log_in.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.student_gallary) {
            Intent stgallary = new Intent(this, gallary.class);
            stgallary.putExtra("nametwo", "Gallery");
            startActivity(stgallary);
        }
        if (view.getId() == R.id.student_notice_board) {
            Intent stnotice_brd = new Intent(this, choose_news_or_events.class);

            startActivity(stnotice_brd);
        }
        if (view.getId() == R.id.student_routine) {
            startActivity(new Intent(this, showing_routine.class));
        }
        if (view.getId() == R.id.student_alarm) {
            startActivity(new Intent(student_menu_page.this,Setlarm.class));
        }
        if (view.getId() == R.id.student_deptinfo) {
            startActivity(new Intent(student_menu_page.this,select_contact.class));
        }
        if(view.getId()==R.id.student_syllabus){
            startActivity(new Intent(student_menu_page.this, syllabus.class));
        }

    }



}