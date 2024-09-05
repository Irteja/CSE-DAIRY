package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choose_news_or_events extends AppCompatActivity {
   private CardView news,events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_news_or_events);
        news=findViewById(R.id.notice_news);
        events=findViewById(R.id.notice_events);

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nws=new Intent(choose_news_or_events.this,gallary.class);
                nws.putExtra("nametwo", "News");
                startActivity(nws);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent evnts=new Intent(choose_news_or_events.this,gallary.class);
            evnts.putExtra("nametwo","Events");
            startActivity(evnts);
            }
        });
    }
}