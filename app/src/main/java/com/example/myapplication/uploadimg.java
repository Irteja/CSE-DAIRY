package com.example.myapplication;

public class uploadimg {
    private String mname;
    private String mimguri;

    public uploadimg(){

    }
    public uploadimg(String name,String imguri){
        if(name.trim().equals(""))
        {
            name="No Name";
        }

        mname=name;
        mimguri=imguri;
    }
    public String getname(){
        return mname;
    }
    public void setname(String name){
        mname=name;
    }
    public String getimguri(){
        return mimguri;
    }
    public void setimguri(String imguri){
        mimguri=imguri;
    }
}
