package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListDataActivity extends AppCompatActivity {

    private Button button;
    String course_name,day,time;

    List<storingalarminfo> liststoringalarminfo;
    private ListView listView;
    private databaselite databaselite1;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        listView=(ListView) findViewById(R.id.listview_alarm);
        searchView=(SearchView) findViewById(R.id.search_alarm);
        databaselite1=new databaselite(this);
        SQLiteDatabase sqLiteDatabase = databaselite1.getWritableDatabase();
        loadData();
    }

    public void loadData(){
        liststoringalarminfo = new ArrayList<>();
        ArrayList<String> listdata = new ArrayList<>();
        Cursor cursor = databaselite1.showAllData();

        if(cursor.getCount()==0){
            Toast.makeText(this, "No alarm is set yet", Toast.LENGTH_SHORT).show();
        }
        else {
            liststoringalarminfo.clear();
            while(cursor.moveToNext()){
                storingalarminfo storingalarminfo1 = new storingalarminfo(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                liststoringalarminfo.add(storingalarminfo1);
            }
        }
        alarmlist alarmlist1 = new alarmlist(ListDataActivity.this,liststoringalarminfo);
        listView.setAdapter(alarmlist1);
        listView.setTextFilterEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                showsearchitem(s);
                return false;
            }
        });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    storingalarminfo storingalarminfo1=liststoringalarminfo.get(i);

                }
            });
    }

    private void showsearchitem(String s){
        List<storingalarminfo> storingalarminfos = new ArrayList<>();
        for(storingalarminfo storingalarminfo1:liststoringalarminfo){
            if(storingalarminfo1.getCourseName().trim().toLowerCase().contains(s.trim().toLowerCase())){
                storingalarminfos.add(storingalarminfo1);
            }
        }

        alarmlist alarmlist1 = new alarmlist(this,storingalarminfos);
        listView.setAdapter(alarmlist1);
    }
}