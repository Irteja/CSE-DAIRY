package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class syllabus_adapter extends ArrayAdapter<syllabus_info> {

    private Activity context;
    private List<syllabus_info> ls;
    public syllabus_adapter(Activity context,List<syllabus_info>ls){
        super(context,R.layout.list_layout,ls);
        this.context=context;
        this.ls=ls;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater slubinfl=context.getLayoutInflater();
        View syllabusview=slubinfl.inflate(R.layout.list_layout,null,false);
        TextView crsname=syllabusview.findViewById(R.id.textView_course_name);
        TextView crsdtl=syllabusview.findViewById(R.id.textView_course_time);

        syllabus_info str=ls.get(position);
        crsname.setText(str.getCrsname());
        crsdtl.setText(str.getCrsdtl());

        return  syllabusview;
    }
}
