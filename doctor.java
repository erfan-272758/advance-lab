package com.islam;

public class doctor {
    private String name;
    private String speciality;
    private int access;

    public doctor(String name, String speciality, int access) {
        this.name = name;
        this.speciality = speciality;
        this.access = access;
    }


    public String getName() {
        return name;
    }


    public int getAccess() {
        return access;
    }
    public void show(){
        System.out.println("Name: "+name
        +" Speciality: "+ speciality + " Access: " + access);
    }
}
