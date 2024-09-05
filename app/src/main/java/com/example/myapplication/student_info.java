package com.example.myapplication;

public class student_info {
    private String name, roll,reg,session,phone,peaddrs,prsaddrs,simguri;

    student_info(){

    }

    public void setPeaddrs(String peaddrs) {
        this.peaddrs = peaddrs;
    }

    public void setPrsaddrs(String prsaddrs) {
        this.prsaddrs = prsaddrs;
    }

    public void setSimguri(String simguri) {
        this.simguri = simguri;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getReg() {
        return reg;
    }

    public String getSession() {
        return session;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    student_info(String name, String roll, String reg, String session, String phone,String peaddrs,String prsaddrs,String simguri) {
        this.name = name;
        this.roll = roll;
        this.reg=reg;
        this.session=session;
        this.phone=phone;
        this.peaddrs=peaddrs;
        this.prsaddrs=prsaddrs;
        this.simguri=simguri;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public String getPeaddrs() {
        return peaddrs;
    }

    public String getPrsaddrs() {
        return prsaddrs;
    }

    public String getSimguri() {
        return simguri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
