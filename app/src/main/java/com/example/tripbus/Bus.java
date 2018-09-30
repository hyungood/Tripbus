package com.example.tripbus;

public class Bus {
    int id;
    String bus_num;
    String[] stop;
    String[] lat;
    String[] lng;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBus_num() {
        return bus_num;
    }

    public String[] getStop() {
        return this.stop;
    }

    public String[] getLat() {
        return this.lat;
    }

    public String[] getLng() {
        return this.lng;
    }
}
