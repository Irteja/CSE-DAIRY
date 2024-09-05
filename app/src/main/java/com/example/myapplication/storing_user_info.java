package com.example.myapplication;

public class storing_user_info {
    private String tname,tdsgn,tphone;
    private String timguri;

   storing_user_info(){

   }
    public String getTname() {
        return tname;
    }

    public String getTdsgn() {
        return tdsgn;
    }

    public String getTphone() {
        return tphone;
    }

    public String getTimguri() {
        return timguri;
    }

    storing_user_info(String tname, String tdsgn, String tphone,String timguri) {
        this.tname=tname;
        this.tdsgn=tdsgn;
        this.tphone=tphone;
        this.timguri=timguri;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setTdsgn(String tdsgn) {
        this.tdsgn = tdsgn;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public void setTimguri(String timguri) {
        this.timguri = timguri;
    }
}
