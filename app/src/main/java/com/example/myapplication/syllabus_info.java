package com.example.myapplication;

public class syllabus_info {

    private String crsname,crsdtl;

    syllabus_info(){

    }
    syllabus_info(String crsname,String crsdtl){
        this.crsname=crsname;
        this.crsdtl=crsdtl;
    }

    public void setCrsname(String crsname) {
        this.crsname = crsname;
    }

    public void setCrsdtl(String crsdtl) {
        this.crsdtl = crsdtl;
    }

    public String getCrsname() {
        return crsname;
    }

    public String getCrsdtl() {
        return crsdtl;
    }
}
