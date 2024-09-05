package com.example.myapplication;

public class storing_routine_info {
    private String course_name,course_time,course_teacher;
    storing_routine_info(){

    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    storing_routine_info(String course_name, String course_time,String course_teacher){
        this.course_name=course_name;
        this.course_teacher=course_teacher;
        this.course_time=course_time;
    }

    public String getCourse_teacher() {
        return course_teacher;
    }

    public void setCourse_teacher(String course_teacher) {
        this.course_teacher = course_teacher;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_time() {
        return course_time;
    }
}
