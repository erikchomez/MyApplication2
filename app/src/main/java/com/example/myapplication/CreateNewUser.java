package com.example.myapplication;

import java.util.ArrayList;

public class CreateNewUser {
    private String mName;
    private boolean mWorkedOut;
    private int mPasses;
    private ArrayList<Integer> attendanceArray;

    public CreateNewUser(String name) {
        this.mName = name;
        this.mWorkedOut = false;
        this.mPasses = 2;
        this.attendanceArray = new ArrayList<Integer>();
        for(int i = 0; i < 6; i++){
            this.attendanceArray.add(0);
        }

    }

    public String getName() {
        return this.mName;
    }

    public ArrayList<Integer> getAttendanceArray(){
        return attendanceArray;
    }

    public void setAttendenceArray(int num, int index){
        attendanceArray.set(index, num);
    }
}
