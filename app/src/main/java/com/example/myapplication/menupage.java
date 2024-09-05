package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class menupage extends AppCompatActivity implements View.OnClickListener {
   private CardView gallary,notice_board,routine,alarm,info,setting;
   private ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupage);
        setTitle("CSE DAIRY");
        gallary=findViewById(R.id.gallary);
        notice_board=findViewById(R.id.notice_board);
        routine=findViewById(R.id.routine);
        alarm=findViewById(R.id.alarm);
        info=findViewById(R.id.deptinfo);
        setting=findViewById(R.id.setting);

//        imageSlider=findViewById(R.id.image_slider_admin);
//
//        ArrayList<SlideModel> imglist=new ArrayList<>();
//
//        imglist.add(new SlideModel("https://bit.ly/2YoJ77H","first image", ScaleTypes.CENTER_INSIDE));
//        imglist.add(new SlideModel("https://bit.ly/2BteuF2","second image", ScaleTypes.CENTER_INSIDE));
//        imglist.add(new SlideModel("https://bit.ly/3fLJf72","third image", ScaleTypes.CENTER_INSIDE));
//
//        imageSlider.startSliding(5000);
//       imageSlider.setImageList(imglist);


        gallary.setOnClickListener(this);
        notice_board.setOnClickListener(this);
        routine.setOnClickListener(this);
        alarm.setOnClickListener(this);
        info.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.gallary)
        {
          Intent gallary=new Intent(this, gallary.class);
          gallary.putExtra("nametwo","Gallery");
          startActivity(gallary);
        }
        if(view.getId()==R.id.notice_board)
        {
          Intent notice_brd=new Intent(menupage.this, com.example.myapplication.gallary.class);
          notice_brd.putExtra("nametwo","Notice_board");
          startActivity(notice_brd);
        }
        if(view.getId()==R.id.routine)
        {
        startActivity(new Intent(this,showing_routine.class));
        }
        if(view.getId()==R.id.alarm)
        {
//            startActivity(new Intent(menupage.this,showing_routine.class));
        }
        if(view.getId()==R.id.deptinfo)
        {

        }
        if(view.getId()==R.id.setting)
        {
            startActivity(new Intent(getApplicationContext(),Slect_Option.class));
        }



    }
}