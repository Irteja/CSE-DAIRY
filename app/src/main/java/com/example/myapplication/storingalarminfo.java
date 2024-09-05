package com.example.myapplication;

public class storingalarminfo {
    private String coursename,time,day;
    public storingalarminfo(){}

    public String getCourseName() {return coursename;}
    public String gettime(){ return time;}
    public String getDay() { return day; }

    public storingalarminfo(String coursename,String time,String day){
        this.coursename=coursename;
        this.day=day;
        this.time=time;
    }
}
