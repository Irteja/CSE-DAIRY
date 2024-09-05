package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Teacher_menu_page extends AppCompatActivity implements View.OnClickListener {
    private CardView gallary,notice_board,routine,alarm,info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CSE DAIRY");
        setContentView(R.layout.activity_teacher_menu_page);
        gallary=findViewById(R.id.tch_gallary);
        notice_board=findViewById(R.id.tch_notice_board);
        routine=findViewById(R.id.tch_routine);
        alarm=findViewById(R.id.tch_alarm);
        info=findViewById(R.id.tch_deptinfo);
        gallary.setOnClickListener(this);
        notice_board.setOnClickListener(this);
        routine.setOnClickListener(this);
        alarm.setOnClickListener(this);
        info.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tch_gallary)
        {
            Intent tgallary=new Intent(this, gallary.class);
            tgallary.putExtra("nametwo","Gallery");
            startActivity(tgallary);
        }
        if(view.getId()==R.id.tch_notice_board)
        {
            Intent tnotice_brd=new Intent(this, com.example.myapplication.gallary.class);
            tnotice_brd.putExtra("nametwo","Notice_board");
            startActivity(tnotice_brd);
        }
        if(view.getId()==R.id.tch_routine)
        {
            Bundle tid=getIntent().getExtras();
            String px=tid.getString("id");
            Intent routine=new Intent(Teacher_menu_page.this,Teacher_routine_show.class);
            routine.putExtra("id",px);
            startActivity(routine);
        }
        if(view.getId()==R.id.tch_alarm)
        {

        }
        if(view.getId()==R.id.tch_deptinfo)
        {

        }
    }
}