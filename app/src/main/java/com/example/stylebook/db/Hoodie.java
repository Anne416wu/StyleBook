package com.example.stylebook.db;

public class Hoodie extends Cloth {
    public Hoodie(String name,int imageId){
        super(name,imageId);
    }

    @Override
    public void setType(String type) {
        super.setType("Hoodie");
    }
}
