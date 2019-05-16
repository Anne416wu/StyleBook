package com.example.stylebook.db;

public class Cloth {
    private String name;
    private int imageId;
    public Cloth(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
