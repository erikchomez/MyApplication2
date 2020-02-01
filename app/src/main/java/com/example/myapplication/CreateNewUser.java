package com.example.myapplication;

public class CreateNewUser {
    private String mName;
    private boolean mWorkedOut;
    private int mPasses;

    public CreateNewUser(String name) {
        this.mName = name;
        this.mWorkedOut = false;
        this.mPasses = 2;
    }

    public String getName() {
        return this.mName;
    }
}
