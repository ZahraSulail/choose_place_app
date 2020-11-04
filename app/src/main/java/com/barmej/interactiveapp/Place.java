package com.barmej.interactiveapp;

public class Place {
    /**
     * Integer variable that hold string resource id of place name
     */
    private int name;

    /**
     * Integer variable that hold drawable resource id of place picture
     */
    private int picture;

    /*
       Constructor to initialize place attributes
       */
    public Place(int name, int picture ){
        this.name = name;
        this.picture = picture;
    }

    /*
     Getter and setter methods
     */
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }



}
