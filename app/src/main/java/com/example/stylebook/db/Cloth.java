package com.example.stylebook.db;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;

import org.litepal.crud.LitePalSupport;

import java.util.Calendar;
import java.util.Date;

public class Cloth extends LitePalSupport {
    private  String name;
    private int imageId;
    private Color color;
    private String material;
    private int season;
    private Calendar buyDate;
    private String type;
    private int temprature;
    public Cloth(){};
    public Cloth(String name, int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public Cloth(String name, int imageId, Color color,String material,int season,Calendar buyDate,String type,int temprature){
        this.name=name;
        this.imageId=imageId;
        this.color=color;
        this.material=material;
        this.season=season;
        this.buyDate=buyDate;
        this.type=type;
        this.temprature=temprature;
    }
    public int gettemprature() {
        return temprature;
    }

    public void settemprature(int temprature) {
        this.temprature = temprature;
    }


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public Calendar getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Calendar buyDate) {
        this.buyDate = buyDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
