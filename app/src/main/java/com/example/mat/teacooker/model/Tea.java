package com.example.mat.teacooker.model;

/**
 * Created by mat on 14/12/17.
 */

public class Tea {
    private String name;
    private int duration; //in milisecond

    public Tea() {
    }

    public Tea(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
