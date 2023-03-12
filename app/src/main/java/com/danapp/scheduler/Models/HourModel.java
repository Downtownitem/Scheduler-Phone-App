package com.danapp.scheduler.Models;

public class HourModel {

    String time;
    String day;
    String place;

    public HourModel(String time, String day, String place) {
        this.time = time;
        this.day = day;
        this.place = place;
    }

    public HourModel() {
        this.time = "";
        this.day = "";
        this.place = "";
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "HourModel{" +
                "time='" + time + '\'' +
                ", day='" + day + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}
