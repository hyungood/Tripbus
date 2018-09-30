package com.example.tripbus;

public class myplace {
    private int myplaceType;
    private String myplaceName;

    public myplace(int t, String name) {
        myplaceType = t;
        myplaceName = name;
    }

    public int getMyplaceType() {
        return myplaceType;
    }

    public String getMyplaceName() {
        return myplaceName;
    }
}
