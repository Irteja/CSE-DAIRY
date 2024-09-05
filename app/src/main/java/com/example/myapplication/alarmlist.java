package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class alarmlist extends ArrayAdapter<storingalarminfo> {

    private Activity Context;
    private List<storingalarminfo> alalist,alalist2;

    public alarmlist(Activity context,List<storingalarminfo> l ) {
        super(context, R.layout.alarm_layout);
        context = Context;
        alalist2=l;
        alalist=l;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = Context.getLayoutInflater();
        View listviewitem = layoutInflater.inflate(R.layout.alarm_layout,null,true);

        TextView tv=listviewitem.findViewById(R.id.alarmcoursename);
        TextView tv2=listviewitem.findViewById(R.id.alarmtime);
        TextView tv3=listviewitem.findViewById(R.id.alarmday);

        storingalarminfo storingalarminfo1 = getItem(position);
        tv.setText(storingalarminfo1.getCourseName());
        tv2.setText(storingalarminfo1.gettime());
        tv3.setText(storingalarminfo1.getDay());

        return listviewitem;
    }
}
