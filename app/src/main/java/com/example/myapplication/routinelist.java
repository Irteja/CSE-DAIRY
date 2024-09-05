package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class routinelist extends ArrayAdapter<storing_routine_info> {
    private Activity context;
    private List<storing_routine_info> rtnlist;

    public routinelist(Activity context, List<storing_routine_info>rtnlist){
        super(context,R.layout.routine_list_layout,rtnlist);
        this.context=context;
        this.rtnlist=rtnlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater rtninflt=context.getLayoutInflater();
        View listviewitem=rtninflt.inflate(R.layout.routine_list_layout,null,true);
        TextView crsnam=listviewitem.findViewById(R.id.routine_course_name_two);
        TextView crstime=listviewitem.findViewById(R.id.routine_course_time_two);
        TextView crstchr=listviewitem.findViewById(R.id.routine_course_teacher_two);

        storing_routine_info strrtn=rtnlist.get(position);
        crsnam.setText(strrtn.getCourse_name());
        crstime.setText(strrtn.getCourse_time());
        crstchr.setText(strrtn.getCourse_teacher());

        return listviewitem;
    }
}
